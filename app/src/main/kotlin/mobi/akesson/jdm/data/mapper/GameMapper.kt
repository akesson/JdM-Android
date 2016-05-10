package mobi.akesson.jdm.data.mapper

import mobi.akesson.jdm.data.model.GameData
import mobi.akesson.jdm.domain.model.Game
import mobi.akesson.jdm.domain.model.Table

class GameMapper {

    fun toData(game: Game): GameData = GameData(
            name = game.name,
            minPlayers = game.minPlayers,
            maxPlayers = game.maxPlayers,
            tables = game.tables.associate { it.id to true })

    fun toDataMap(game: Game): Map<String, Any> {
        val map: MutableMap<String, Any> = mutableMapOf()
        if (game.name.isNotBlank()) map.put("name", game.name)
        if (game.minPlayers > 0 ) map.put("minPlayers", game.minPlayers)
        if (game.maxPlayers > 0 ) map.put("maxPlayers", game.maxPlayers)
        if (game.tables.isNotEmpty()) map.put("tables", game.tables.associate { it.id to true })
        return map
    }

    fun toDomain(id: String, gameData: GameData): Game = Game(
            id = id,
            name = gameData.name,
            minPlayers = gameData.minPlayers,
            maxPlayers = gameData.maxPlayers,
            tables = gameData.tables.map { Table(id = it.key) })
}
