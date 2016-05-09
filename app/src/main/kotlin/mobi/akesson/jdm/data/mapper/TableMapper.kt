package mobi.akesson.jdm.data.mapper

import mobi.akesson.jdm.data.model.TableData
import mobi.akesson.jdm.domain.model.Game
import mobi.akesson.jdm.domain.model.Player
import mobi.akesson.jdm.domain.model.Table

class TableMapper {

    fun toData(table: Table): TableData {
        return TableData(game = table.game.id,
                startTime = table.startTime,
                players = table.players.associate { it.id to true })
    }

    fun toDataMap(table: Table): Map<String, Any> {
        val map: MutableMap<String, Any> = mutableMapOf()
        if (table.game.id.isNotBlank()) map.put("game", table.game)
        if (table.startTime > 0 ) map.put("startTime", table.startTime)
        if (table.players.isNotEmpty()) map.put("players", table.players.associate { it.id to true })
        return map
    }

    fun toDomain(id: String, tableData: TableData): Table {
        return Table(id = id,
                game = Game(id = tableData.game),
                startTime = tableData.startTime,
                players = tableData.players.map { Player(id = it.key) })
    }
}
