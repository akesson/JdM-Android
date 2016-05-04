package mobi.akesson.jdm.ui.core.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.nestedScrollView

class DummyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DummyFragmentUI().createView(AnkoContext.create(context, this))
    }
}

class DummyFragmentUI : AnkoComponent<DummyFragment> {

    override fun createView(ui: AnkoContext<DummyFragment>) = with(ui) {
        nestedScrollView {
            linearLayout {
                gravity = Gravity.CENTER

                textView("Dummy") {
                    setAllCaps(true)
                }
            }
        }
    }
}