package mobi.akesson.jdm.domain.manager

import com.firebase.client.Firebase
import mobi.akesson.jdm.BuildConfig
import mobi.akesson.jdm.domain.model.Game
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricGradleTestRunner::class)
@Config(sdk = intArrayOf(21), constants = BuildConfig::class)
class GameManagerRobolectricTest {

    @Before
    fun setUp() {
        Firebase.setAndroidContext(RuntimeEnvironment.application)
    }

    @Test
    fun testCreate() {
        // FIXME: Sadly running this test does not create a game in the database.
        // Robolectric context does not work?
        GameManager().create(Game(name = "Test Game", minPlayers = 1, maxPlayers = 2))
    }
}