package mobi.akesson.jdm.domain.manager

import com.firebase.client.Firebase

abstract class BaseManager {

    val BASE_URL = "https://jeuxdemidi.firebaseio.com"
    val PATH_GAMES = "games"
    val PATH_TABLES = "tables"
    val PATH_PLAYERS = "players"

    val ref = Firebase(BASE_URL);
    val gamesRef = ref.child(PATH_GAMES);
    val tablesRef = ref.child(PATH_TABLES);
    val playersRef = ref.child(PATH_PLAYERS);
}
