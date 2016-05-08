package mobi.akesson.jdm.ui

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import mobi.akesson.jdm.R
import mobi.akesson.jdm.ui.core.adapter.SimpleFragmentPagerAdapter
import mobi.akesson.jdm.ui.game.GameFragment
import mobi.akesson.jdm.ui.game.RegisterGameActivity
import mobi.akesson.jdm.ui.player.PlayerFragment
import mobi.akesson.jdm.ui.table.RegisterTableActivity
import mobi.akesson.jdm.ui.table.TableFragment
import org.jetbrains.anko.onClick
import org.jetbrains.anko.startActivity
import kotlinx.android.synthetic.main.activity_main.main_fab as fab
import kotlinx.android.synthetic.main.activity_main.main_tabs as tabs
import kotlinx.android.synthetic.main.activity_main.main_toolbar as toolbar
import kotlinx.android.synthetic.main.activity_main.main_view_pager as viewPager

class MainActivity : AppCompatActivity() {

    val TABLE_INDEX = 0
    val GAME_INDEX = 1
    val PLAYER_INDEX = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val adapter = SimpleFragmentPagerAdapter(supportFragmentManager)
        adapter.addItem(TABLE_INDEX, "Tables", TableFragment())
        adapter.addItem(GAME_INDEX, "Jeux", GameFragment())
        adapter.addItem(PLAYER_INDEX, "Profil", PlayerFragment())

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    TABLE_INDEX -> onTablePageSelected()
                    GAME_INDEX -> onGamePageSelected()
                    PLAYER_INDEX -> onPlayerPageSelected()
                }
            }
        })

        fab.onClick {
            when (viewPager.currentItem) {
                TABLE_INDEX -> startActivity<RegisterTableActivity>()
                GAME_INDEX -> startActivity<RegisterGameActivity>()
            }
        }
    }

    private fun onTablePageSelected() {
        fab.show()
    }

    private fun onGamePageSelected() {
        fab.show()
    }

    private fun onPlayerPageSelected() {
        fab.hide()
    }
}