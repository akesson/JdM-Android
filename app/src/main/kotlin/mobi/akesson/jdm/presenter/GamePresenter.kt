package mobi.akesson.jdm.presenter

import com.firebase.client.ChildEventListener
import mobi.akesson.jdm.domain.manager.GameManager
import mobi.akesson.jdm.domain.model.Game
import mobi.akesson.jdm.ui.core.view.EventType
import mobi.akesson.jdm.ui.core.view.GameView

class GamePresenter(val gameManager: GameManager = GameManager()) : BasePresenter<MutableList<Game>, GameView>() {

    private var isLoadingData = false
    private var listener: ChildEventListener? = null

    override fun updateView() {
        if (model?.size == 0) {
            view?.showEmpty()
        } else {
            view?.showList(model)
        }
    }

    override fun bind(view: GameView) {
        super.bind(view)
        if (model == null && !isLoadingData) {
            view?.showLoading()
            loadData()
        }
    }

    override fun unbind() {
        super.unbind()
        gameManager.removeDataListener(listener)
    }

    private fun loadData() {
        isLoadingData = true
        listener = gameManager.addDataListener(
                onDataAdded = { data, previousDataId ->
                    isLoadingData = false
                    if (data == null) {
                        model = mutableListOf()
                    } else {
                        var index = 0
                        if (previousDataId == null) {
                            model = mutableListOf(data)
                        } else {
                            index = getIndexForId(previousDataId) + 1;
                            model?.add(index, data)
                        }
                        view?.updateList(EventType.ADDED, index)
                    }
                },
                onDataChanged = { data, previousDataId ->
                    val index = getIndexForId(data.id);
                    model?.set(index, data)
                    view?.updateList(EventType.CHANGED, index)
                },
                onDataRemoved = { data ->
                    val index = getIndexForId(data.id);
                    model?.removeAt(index)
                    view?.updateList(EventType.REMOVED, index)
                    updateView()
                },
                onDataMoved = { data, previousDataId ->
                    val oldIndex = getIndexForId(data.id);
                    model?.removeAt(oldIndex)
                    val newIndex = if (previousDataId == null) 0 else (getIndexForId(previousDataId) + 1);
                    model?.add(newIndex, data)
                    view?.updateList(EventType.MOVED, oldIndex, newIndex)
                }
        )
    }

    private fun getIndexForId(id: String): Int {
        var index = 0
        model?.forEach { item ->
            if (item.id.equals(id)) {
                return index;
            } else {
                index++;
            }
        }
        throw IllegalArgumentException("ID not found");
    }
}