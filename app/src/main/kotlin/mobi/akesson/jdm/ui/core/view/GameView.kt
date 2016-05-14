package mobi.akesson.jdm.ui.core.view

import mobi.akesson.jdm.domain.model.Game
import mobi.akesson.jdm.EventType

interface GameView {

    fun showList(games: MutableList<Game>?)

    fun showLoading()

    fun showEmpty()

    fun updateList(eventType: EventType, index: Int, oldIndex: Int = -1)
}