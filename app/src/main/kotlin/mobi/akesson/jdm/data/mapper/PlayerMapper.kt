package mobi.akesson.jdm.data.mapper

import mobi.akesson.jdm.data.model.PlayerData
import mobi.akesson.jdm.domain.model.Player
import mobi.akesson.jdm.domain.model.Table

class PlayerMapper {

    fun toData(player: Player): PlayerData = PlayerData(
            name = player.name,
            tables = player.tables.associate { it.id to true })

    fun toDataMap(player: Player): Map<String, Any> {
        val map: MutableMap<String, Any> = mutableMapOf()
        if (player.name.isNotBlank()) map.put("name", player.name)
        if (player.tables.isNotEmpty()) map.put("tables", player.tables.associate { it.id to true })
        return map
    }

    fun toDomain(id: String, playerData: PlayerData): Player = Player(
            id = id,
            name = playerData.name,
            tables = playerData.tables.map { Table(id = it.key) })
}
