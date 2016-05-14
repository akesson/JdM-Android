package mobi.akesson.jdm.domain.manager

import com.firebase.client.*
import mobi.akesson.jdm.data.mapper.GameMapper
import mobi.akesson.jdm.data.model.GameData
import mobi.akesson.jdm.domain.model.Game
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug

/**
 * Domain class containing the business logic related to the [Game] model.
 */
class GameManager(val gameMapper: GameMapper = GameMapper()) : BaseManager(), AnkoLogger {

    /**
     * Create a new game entry in the remote database and return a generated ID. Asynchronous operation.
     *
     * @param game The game to create.
     * @return The generated ID.
     */
    fun create(game: Game): String {
        val newRef = gamesRef.push()
        val gameData = gameMapper.toData(game)
        newRef.setValue(gameData, Firebase.CompletionListener { error, ref ->
            if (error == null) {
                debug("$game created successfully.")
            } else {
                debug("Data could not be saved: ${error.message}");
            }
        })
        return newRef.key
    }

    /**
     * Read all game entries in the remote database. Asynchronous operation performed only once.
     *
     * @param onResult The callback receiving the data.
     */
    fun read(onResult: (data: List<Game>) -> Unit = { data -> }) {
        gamesRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                debug("There are ${snapshot.childrenCount} games");
                onResult(snapshot.children
                        .associate { it.key to it.getValue(GameData::class.java) }
                        .map { gameMapper.toDomain(it.key, it.value) })
            }

            override fun onCancelled(error: FirebaseError) {
                debug("The read failed: ${error.message}");
            }
        })
    }

    /**
     * Read one game entry with the given [id] in the remote database. Asynchronous operation performed only once.
     *
     * @param id The ID of the game to read.
     * @param onResult The callback receiving the data.
     */
    fun read(id: String, onResult: (data: Game) -> Unit = { data -> }) {
        gamesRef.child(id).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val gameData = snapshot.getValue(GameData::class.java)
                val game = gameMapper.toDomain(id, gameData)
                onResult(game)
            }

            override fun onCancelled(error: FirebaseError) {
                debug("The read failed: ${error.message}");
            }
        })
    }

    /**
     * Update one game entry in the remote database. Existing fields are not overwritten or deleted if unchanged. Asynchronous operation.
     *
     * @param game A game instance containing the updated fields. Fields with default values are considered unchanged.
     */
    fun update(game: Game) {
        val update = gameMapper.toDataMap(game)
        playersRef.child(game.id).updateChildren(update, Firebase.CompletionListener { error, ref ->
            if (error == null) {
                debug("$game updated successfully.")
            } else {
                debug("Data could not be updated: ${error.message}");
            }
        })
    }

    // TODO
    //    fun move(fromPath: Firebase, toPath: Firebase) {
    //        fromPath.addListenerForSingleValueEvent(object : ValueEventListener {
    //            override fun onDataChange(dataSnapshot: DataSnapshot) {
    //                toPath.setValue(dataSnapshot.value, Firebase.CompletionListener { error, ref ->
    //                    if (error == null) {
    //                        fromPath.removeValue()
    //                        //                            debug("moveFirebaseRecord() Great success!");
    //                    } else {
    //                        //                            debug("moveFirebaseRecord() failed. firebaseError = %s", error);
    //                    }
    //                })
    //            }
    //
    //            override fun onCancelled(firebaseError: FirebaseError) {
    //                //                debug("moveFirebaseRecord() failed. firebaseError = %s", firebaseError);
    //            }
    //        });
    //    }

    /**
     * Read all game entries and listen to changes in the remote database.
     *
     * @param onDataAdded The callback triggered first once for each existing entry then every time a new entry is added. Called once with *null* arguments if the database is empty.
     * @param onDataChanged The callback triggered any time an entry is modified.
     * @param onDataRemoved The callback triggered any time an entry is deleted.
     * @param onDataMoved ???
     *
     * @return The listener instance.
     */
    fun addDataListener(onDataAdded: (data: Game?, previousDataId: String?) -> Unit = { data, previousDataId -> },
                        onDataChanged: (data: Game, previousDataId: String?) -> Unit = { data, previousDataId -> },
                        onDataRemoved: (data: Game) -> Unit = { data -> },
                        onDataMoved: (data: Game, previousDataId: String?) -> Unit = { data, previousDataId -> }): ChildEventListener {
        gamesRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.hasChildren()) {
                    onDataAdded(null, null)
                    debug("There are ${snapshot.childrenCount} games");
                }
            }

            override fun onCancelled(error: FirebaseError) {
                debug("The read failed: ${error.message}");
            }
        })
        return gamesRef.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildKey: String?) {
                val game = gameMapper.toDomain(snapshot.key, snapshot.getValue(GameData::class.java))
                onDataAdded(game, previousChildKey)
                debug("$game added successfully.");
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildKey: String?) {
                val game = gameMapper.toDomain(snapshot.key, snapshot.getValue(GameData::class.java))
                onDataChanged(game, previousChildKey)
                debug("$game changed successfully.");
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                val game = gameMapper.toDomain(snapshot.key, snapshot.getValue(GameData::class.java))
                onDataRemoved(game)
                debug("$game removed successfully.");
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildKey: String?) {
                val game = gameMapper.toDomain(snapshot.key, snapshot.getValue(GameData::class.java))
                onDataMoved(game, previousChildKey)
                debug("$game moved successfully.");
            }

            override fun onCancelled(error: FirebaseError) {
                debug("The read failed: ${error.message}");
            }
        })
    }

    /**
     * Stop listening to changes in the remote database by detaching a [listener].
     *
     * @param listener The listener to remove.
     */
    fun removeDataListener(listener: ChildEventListener?) {
        gamesRef.removeEventListener(listener)
    }
}