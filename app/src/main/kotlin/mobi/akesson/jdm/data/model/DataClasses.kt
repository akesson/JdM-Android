package mobi.akesson.jdm.data.model

data class GameData(
        val name: String = "",
        val minPlayers: Int = 0,
        val maxPlayers: Int = 0,
        val tables: Map<String, Boolean> = mapOf())

data class TableData(
        val game: String = "",
        val startTime: Long = 0,
        val players: Map<String, Boolean> = mapOf())

data class PlayerData(
        val name: String = "",
        val tables: Map<String, Boolean> = mapOf())