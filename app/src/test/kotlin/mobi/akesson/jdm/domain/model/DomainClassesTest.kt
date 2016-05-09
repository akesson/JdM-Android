package mobi.akesson.jdm.domain.model

import org.junit.Test
import org.junit.Assert.*

class DomainClassesTest {

    @Test
    fun hasNoArgConstructor() {
        val game: Game = Game()
        assertNotNull(game)
    }
}

class TableTest {

    @Test
    fun hasNoArgConstructor() {
        val table: Table = Table()
        assertNotNull(table)
    }
}

class PartyTest {

    @Test
    fun hasNoArgConstructor() {
        val player: Player = Player()
        assertNotNull(player)
    }
}
