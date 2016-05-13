package mobi.akesson.jdm.ui.game

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import mobi.akesson.jdm.R
import mobi.akesson.jdm.domain.model.Game
import mobi.akesson.jdm.presenter.GamePresenter
import mobi.akesson.jdm.ui.core.fragment.RecyclerFragment
import mobi.akesson.jdm.ui.core.fragment.RecyclerFragmentUI
import mobi.akesson.jdm.ui.core.view.Event
import mobi.akesson.jdm.ui.core.view.GameView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.textResource

class GameFragment : RecyclerFragment(), GameView {

    var adapter: GameAdapter? = null
    var presenter: GamePresenter? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        adapter = GameAdapter()

        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
        recyclerView?.adapter = adapter

        emptyTextView?.textResource = R.string.games_empty

        presenter = GamePresenter()
    }

    override fun onResume() {
        super.onResume()
        presenter?.bind(this)
    }

    override fun onPause() {
        super.onPause()
        presenter?.unbind()
    }

    override fun showList(games: MutableList<Game>?) {
        adapter?.swapData(games)
        viewAnimator?.displayedChild = RecyclerFragmentUI.POSITION_LIST
    }

    override fun showLoading() {
        viewAnimator?.displayedChild = RecyclerFragmentUI.POSITION_LOADING
    }

    override fun showEmpty() {
        viewAnimator?.displayedChild = RecyclerFragmentUI.POSITION_EMPTY
    }

    override fun updateList(event: Event, index: Int, oldIndex: Int) {
        when (event) {
            Event.ADDED -> adapter?.notifyItemInserted(index);
            Event.CHANGED -> adapter?.notifyItemChanged(index);
            Event.REMOVED -> adapter?.notifyItemRemoved(index);
            Event.MOVED -> adapter?.notifyItemMoved(oldIndex, index);
//            else -> IllegalStateException()
        }
    }

}
