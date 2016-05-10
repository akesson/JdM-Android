package mobi.akesson.jdm.domain.manager

import com.firebase.client.Firebase
import mobi.akesson.jdm.data.mapper.TableMapper
import mobi.akesson.jdm.domain.model.Game
import mobi.akesson.jdm.domain.model.Table
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug

class TableManager(
        val tableMapper: TableMapper = TableMapper(),
        val gameManager: GameManager = GameManager()
) : BaseManager(), AnkoLogger {

    fun create(table: Table): String {
        val tableData = tableMapper.toData(table)
        val newRef = gamesRef.push()
        newRef.setValue(tableData, Firebase.CompletionListener { error, ref ->
            if (error == null) {
                debug("Data saved successfully.")
            } else {
                debug("Data could not be saved. " + error.message);
            }
        })
        gameManager.update(Game(id = table.game.id, tables = listOf(Table(id = newRef.key))))
        return newRef.key
    }

    fun update(table: Table) {
        val update = tableMapper.toDataMap(table)
        playersRef.child(table.id).updateChildren(update, Firebase.CompletionListener { error, ref ->
            if (error == null) {
                debug("Data updated successfully.")
            } else {
                debug("Data could not be updated. " + error.message);
            }
        })
    }
}
