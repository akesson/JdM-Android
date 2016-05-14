package mobi.akesson.jdm.presenter

import mobi.akesson.jdm.domain.model.Table
import mobi.akesson.jdm.ui.core.view.TableView

class TablePresenter : BasePresenter<MutableList<Table>, TableView>() {

    private var isLoadingData = false

    override fun updateView() {
        if (model?.size == 0) {
            view?.showEmpty()
        } else {
            view?.showTables(model)
        }
    }

    override fun bind(view: TableView) {
        super.bind(view)
        if (model == null && !isLoadingData) {
            view?.showLoading()
            loadData()
        }
    }

    private fun loadData() {
        isLoadingData = true
    }
}