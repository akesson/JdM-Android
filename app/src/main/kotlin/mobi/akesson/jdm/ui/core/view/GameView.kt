package mobi.akesson.jdm.ui.core.view

import mobi.akesson.jdm.domain.model.Game

interface GameView {

    fun showGames(games: List<Game>?)

    fun showLoading()

    fun showEmpty()
}
