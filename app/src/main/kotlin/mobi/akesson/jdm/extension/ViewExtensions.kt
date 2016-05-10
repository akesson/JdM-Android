package mobi.akesson.jdm.extension

import android.support.design.widget.FloatingActionButton
import android.support.v4.view.ViewPager

fun ViewPager.addOnPageChangeListener(
        onPageScrolled: (position: Int, positionOffset: Float, positionOffsetPixels: Int) -> Unit = { position, positionOffset, positionOffsetPixels -> },
        onPageScrollStateChanged: (state: Int) -> Unit = { state -> },
        onPageSelected: (position: Int) -> Unit = { position -> }
) = addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = onPageScrolled(position, positionOffset, positionOffsetPixels)
    override fun onPageScrollStateChanged(state: Int) = onPageScrollStateChanged(state)
    override fun onPageSelected(position: Int) = onPageSelected(position)
})

fun FloatingActionButton.hide(
        onShown: (fab: FloatingActionButton) -> Unit = { fab -> },
        onHidden: (fab: FloatingActionButton) -> Unit = { fab -> }
) = hide(object : FloatingActionButton.OnVisibilityChangedListener() {
    override fun onShown(fab: FloatingActionButton) = onShown(fab)
    override fun onHidden(fab: FloatingActionButton) = onHidden(fab)
})