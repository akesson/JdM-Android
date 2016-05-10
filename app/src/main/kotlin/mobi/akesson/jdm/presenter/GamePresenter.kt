package mobi.akesson.jdm.presenter

import mobi.akesson.jdm.domain.model.Game
import mobi.akesson.jdm.ui.core.view.GameView

class GamePresenter : BasePresenter<MutableList<Game>, GameView>() {

    private var isLoadingData = false

    override fun updateView() {
        if (model?.size == 0) {
            view()?.showEmpty()
        } else {
            view()?.showGames(model)
        }
    }

    override fun bind(view: GameView) {
        super.bind(view)
        if (model == null && !isLoadingData) {
            view()?.showLoading()
            loadData()
        }
    }

    private fun loadData() {
        isLoadingData = true
    }
}