package mobi.akesson.jdm.ui.game

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.firebase.client.Firebase
import com.firebase.ui.FirebaseRecyclerAdapter
import mobi.akesson.jdm.R
import mobi.akesson.jdm.data.model.GameData
import mobi.akesson.jdm.domain.model.Game
import mobi.akesson.jdm.presenter.GamePresenter
import mobi.akesson.jdm.ui.core.fragment.RecyclerFragment
import mobi.akesson.jdm.ui.core.fragment.RecyclerFragmentUI
import mobi.akesson.jdm.ui.core.view.GameView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.textResource

class GameFragment : RecyclerFragment(), GameView {

//    var adapter: FirebaseRecyclerAdapter? = null
    var presenter: GamePresenter? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
//        adapter = GameAdapter()
        var adapter = object : FirebaseRecyclerAdapter<GameData, GameViewHolder>(GameData::class.java, android.R.layout.simple_list_item_2, GameViewHolder::class.java, Firebase("https://jeuxdemidi.firebaseio.com").child("games")) {
            override fun populateViewHolder(viewHolder: GameViewHolder, game: GameData, position: Int) {
                viewHolder.text1?.text = game.name
                viewHolder.text2?.text = "${game.minPlayers} à ${game.maxPlayers} joueurs"
            }
            //

//            override
//            fun populateViewHolder(ChatMessageViewHolder chatMessageViewHolder, ChatMessage chatMessage, int position) {
//                chatMessageViewHolder.nameText.setText(chatMessage.getName());
//                chatMessageViewHolder.messageText.setText(chatMessage.getMessage());
//            }
        };

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

    override fun showGames(games: List<Game>) {
//        adapter?.swapData(games)
        viewAnimator?.displayedChild = RecyclerFragmentUI.POSITION_LIST
    }

    override fun showLoading() {
        viewAnimator?.displayedChild = RecyclerFragmentUI.POSITION_LOADING
    }

    override fun showEmpty() {
        viewAnimator?.displayedChild = RecyclerFragmentUI.POSITION_EMPTY
    }
}
