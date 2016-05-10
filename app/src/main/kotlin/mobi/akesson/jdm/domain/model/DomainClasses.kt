package mobi.akesson.jdm.domain.model

data class Game(
        val id: String = "",
        val name: String = "",
        val minPlayers: Int = 0,
        val maxPlayers: Int = 0,
        val tables: List<Table> = listOf())

data class Table(
        val id: String = "",
        val game: Game = Game(),
        val startTime: Long = 0,
        val players: List<Player> = listOf())

data class Player(
        val id: String = "",
        val name: String = "",
        val tables: List<Table> = listOf())