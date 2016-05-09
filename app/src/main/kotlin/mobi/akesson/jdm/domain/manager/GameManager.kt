package mobi.akesson.jdm.domain.manager

import com.firebase.client.DataSnapshot
import com.firebase.client.Firebase
import com.firebase.client.FirebaseError
import com.firebase.client.ValueEventListener
import mobi.akesson.jdm.data.mapper.GameMapper
import mobi.akesson.jdm.data.model.GameData
import mobi.akesson.jdm.domain.model.Game
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug

class GameManager(val gameMapper: GameMapper = GameMapper()) : BaseManager(), AnkoLogger {

    fun create(game: Game): String {
        val gameData = gameMapper.toData(game)
        val newRef = gamesRef.push()
        newRef.setValue(gameData, Firebase.CompletionListener { error, ref ->
            if (error == null) {
                debug("Data saved successfully.")
            } else {
                debug("Data could not be saved. " + error.message);
            }
        })
        return newRef.key
    }

    fun read(onDataChange: (data: List<Game>) -> Unit = { data -> }) {
        gamesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                debug("There are " + snapshot.childrenCount + " games");
                onDataChange(snapshot.children
                        .associate { it.key to it.getValue(GameData::class.java) }
                        .map { gameMapper.toDomain(it.key, it.value) })
            }

            override fun onCancelled(error: FirebaseError) {
                debug("The read failed: " + error.message)
            }
        })
    }

    fun read(id: String, onDataChange: (data: Game) -> Unit = { data -> }) {
        gamesRef.child(id).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val gameData = snapshot.getValue(GameData::class.java)
                val gameDomain = gameMapper.toDomain(id, gameData)
                onDataChange(gameDomain)
            }

            override fun onCancelled(error: FirebaseError) {
                debug("The read failed: " + error.message)
            }
        })
    }

    fun update(game: Game) {
        val update = gameMapper.toDataMap(game)
        playersRef.child(game.id).updateChildren(update, Firebase.CompletionListener { error, ref ->
            if (error == null) {
                debug("Data updated successfully.")
            } else {
                debug("Data could not be updated. " + error.message);
            }
        })
    }
}