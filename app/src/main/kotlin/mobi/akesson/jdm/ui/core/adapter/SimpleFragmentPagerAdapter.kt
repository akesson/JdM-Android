package mobi.akesson.jdm.ui.core.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class SimpleFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val titles: MutableList<String> = mutableListOf()
    private val fragments: MutableList<Fragment> = mutableListOf()

    fun addItem(index: Int = -1, title: String = "", fragment: Fragment) {
        if (index == -1) {
            titles.add(title)
            fragments.add(fragment)
        } else {
            titles.add(index, title)
            fragments.add(index, fragment)
        }
    }

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = titles.size

    override fun getPageTitle(position: Int): CharSequence = titles[position]
}
