package mobi.akesson.jdm.ui.table

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import mobi.akesson.jdm.R
import mobi.akesson.jdm.domain.model.Table
import mobi.akesson.jdm.presenter.TablePresenter
import mobi.akesson.jdm.ui.core.fragment.RecyclerFragment
import mobi.akesson.jdm.ui.core.fragment.RecyclerFragmentUI
import mobi.akesson.jdm.ui.core.view.TableView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.textResource

class TableFragment : RecyclerFragment(), TableView {

    lateinit var presenter: TablePresenter

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
        emptyTextView.textResource = R.string.tables_empty

        presenter = TablePresenter()
    }

    override fun onResume() {
        super.onResume()
        presenter.bind(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.unbind()
    }

    override fun showTables(tables: List<Table>?) {
        viewAnimator.displayedChild = RecyclerFragmentUI.POSITION_LIST
    }

    override fun showLoading() {
        viewAnimator.displayedChild = RecyclerFragmentUI.POSITION_LOADING
    }

    override fun showEmpty() {
        viewAnimator.displayedChild = RecyclerFragmentUI.POSITION_EMPTY
    }
}
