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

    fun addItem(index: Int = -1, title: String = "", fragment: Fragment) {
        if (index == -1) {
            titles.add(title)
            fragments.add(fragment)
        } else {
            titles.add(index, title)
            fragments.add(index, fragment)
        }
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
