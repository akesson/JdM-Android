package mobi.akesson.jdm.ui.core.view

import mobi.akesson.jdm.domain.model.Game

interface GameView {

    fun showList(games: MutableList<Game>?)

    fun showLoading()

    fun showEmpty()

    fun updateList(eventType: EventType, index: Int, oldIndex: Int = -1)
}

enum class EventType {
    ADDED, CHANGED, REMOVED, MOVED
}