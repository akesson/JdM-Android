package mobi.akesson.jdm.ui.core.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mobi.akesson.jdm.R
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.dimen
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.verticalPadding

open class RecyclerFragment : Fragment() {

    var recyclerView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        recyclerView = RecyclerFragmentUI().createView(AnkoContext.create(context, this))
        return recyclerView
    }
}

class RecyclerFragmentUI : AnkoComponent<RecyclerFragment> {

    override fun createView(ui: AnkoContext<RecyclerFragment>) = with(ui) {
        recyclerView {
            verticalPadding = dimen(R.dimen.padding_small)
            clipToPadding = false
        }
    }
}