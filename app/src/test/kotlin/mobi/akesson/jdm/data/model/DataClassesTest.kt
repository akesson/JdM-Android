package mobi.akesson.jdm.data.model

import org.junit.Assert.assertNotNull
import org.junit.Test

class DataClassesTest {

    @Test
    fun hasNoArgConstructor() = assertNotNull(GameData())
}

class TableTest {

    @Test
    fun hasNoArgConstructor() = assertNotNull(TableData())
}

class PartyTest {

    @Test
    fun hasNoArgConstructor() = assertNotNull(PlayerData())
}
