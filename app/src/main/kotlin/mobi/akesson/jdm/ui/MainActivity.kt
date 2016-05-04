package mobi.akesson.jdm.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import mobi.akesson.jdm.R
import mobi.akesson.jdm.ui.core.adapter.SimpleFragmentPagerAdapter
import mobi.akesson.jdm.ui.core.fragment.DummyFragment
import kotlinx.android.synthetic.main.activity_main.main_tabs as tabs
import kotlinx.android.synthetic.main.activity_main.main_toolbar as toolbar
import kotlinx.android.synthetic.main.activity_main.main_view_pager as viewPager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val adapter = SimpleFragmentPagerAdapter(supportFragmentManager)
        adapter.addFragment("Tables", DummyFragment())
        adapter.addFragment("Jeux", DummyFragment())
        adapter.addFragment("Profil", DummyFragment())

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }
}