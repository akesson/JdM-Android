package mobi.akesson.jdm.ui.core.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ViewAnimator
import mobi.akesson.jdm.R
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

open class RecyclerFragment : Fragment() {

    lateinit var viewAnimator: ViewAnimator
    val recyclerView: RecyclerView by lazy { viewAnimator.getChildAt(RecyclerFragmentUI.POSITION_LIST) as RecyclerView }
    val emptyTextView: TextView by lazy { viewAnimator.getChildAt(RecyclerFragmentUI.POSITION_EMPTY) as TextView }
    val loadingTextView: TextView  by lazy { viewAnimator.getChildAt(RecyclerFragmentUI.POSITION_LOADING) as TextView }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewAnimator = RecyclerFragmentUI().createView(AnkoContext.create(context, this))
        return viewAnimator
    }
}

class RecyclerFragmentUI : AnkoComponent<RecyclerFragment> {

    companion object {
        val POSITION_LIST = 0
        val POSITION_LOADING = 1
        val POSITION_EMPTY = 2
    }

    override fun createView(ui: AnkoContext<RecyclerFragment>) = with(ui) {
        viewAnimator {
            recyclerView {
                verticalPadding = dimen(R.dimen.padding_small)
                clipToPadding = false
            }
            textView {
                gravity = Gravity.CENTER
                textResource = R.string.loading
            }
            textView {
                gravity = Gravity.CENTER
                textResource = R.string.empty
            }
        }
    }
}