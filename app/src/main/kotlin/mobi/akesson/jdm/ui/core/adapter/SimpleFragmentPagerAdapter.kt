package mobi.akesson.jdm.ui.core.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.*

class SimpleFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val titles: ArrayList<String>
    private val fragments: ArrayList<Fragment>

    init {
        titles = ArrayList<String>()
        fragments = ArrayList<Fragment>()
    }

    fun addFragment(title: String, fragment: Fragment) {
        titles.add(title)
        fragments.add(fragment)
    }

    fun addFragment(fragment: Fragment) {
        titles.add("")
        fragments.add(fragment)
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}
