package mobi.akesson.jdm.domain.manager

import com.firebase.client.DataSnapshot
import com.firebase.client.Firebase
import com.firebase.client.FirebaseError
import com.firebase.client.ValueEventListener
import mobi.akesson.jdm.data.mapper.PlayerMapper
import mobi.akesson.jdm.data.model.PlayerData
import mobi.akesson.jdm.domain.model.Player
import mobi.akesson.jdm.domain.model.Table
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug

class PlayerManager(
        val playerMapper: PlayerMapper = PlayerMapper(),
        val tableManager: TableManager = TableManager()
) : BaseManager(), AnkoLogger {

    fun create(id: String, player: Player) {
        val playerData = playerMapper.toData(player)
        playersRef.child(id).setValue(playerData, Firebase.CompletionListener { error, ref ->
            if (error == null) {
                debug("Data saved successfully.")
            } else {
                debug("Data could not be saved. " + error.message);
            }
        })
    }

    fun read(onDataChange: (data: List<Player>) -> Unit = { data -> }) {
        playersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                debug("There are " + snapshot.childrenCount + " players");
                onDataChange(snapshot.children
                        .associate { it.key to it.getValue(PlayerData::class.java) }
                        .map { playerMapper.toDomain(it.key, it.value) })
            }

            override fun onCancelled(error: FirebaseError) {
                debug("The read failed: " + error.message)
            }
        })
    }

    fun read(id: String, onDataChange: (data: Player) -> Unit = { data -> }) {
        playersRef.child(id).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val playerData = snapshot.getValue(PlayerData::class.java)
                val playerDomain = playerMapper.toDomain(id, playerData)
                onDataChange(playerDomain)
            }

            override fun onCancelled(error: FirebaseError) {
                debug("The read failed: " + error.message)
            }
        })
    }

    fun update(player: Player) {
        val update = playerMapper.toDataMap(player)
        playersRef.child(player.id).updateChildren(update, Firebase.CompletionListener { error, ref ->
            if (error == null) {
                debug("Data updated successfully.")
            } else {
                debug("Data could not be updated. " + error.message);
            }
        })
    }

    fun linkToTable(playerId: String, tableId: String) {
        update(Player(id = playerId, tables = listOf(Table(id = tableId))))
        tableManager.update(Table(id = tableId, players = listOf(Player(id = playerId))))
    }
}
