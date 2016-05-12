package mobi.akesson.jdm.ui.game

import android.R
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import mobi.akesson.jdm.domain.model.Game
import org.jetbrains.anko.find

class GameAdapter : RecyclerView.Adapter<GameViewHolder>() {

    private var dataset: MutableList<Game> = mutableListOf();

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GameViewHolder? {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.simple_list_item_2, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder?, position: Int) {
        holder?.bind(getItem(position))
    }

    fun swapData(games: MutableList<Game>?) {
        if (games != null) {
            dataset = games
            notifyDataSetChanged()
        }
    }

    fun getItem(position: Int): Game = dataset[position]

    override fun getItemCount(): Int = dataset.size
}

class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var text1: TextView? = null
    var text2: TextView? = null

    init {
        text1 = itemView.find<TextView>(R.id.text1)
        text2 = itemView.find<TextView>(R.id.text2)
    }

    fun bind(game: Game) {
        text1?.text = game.name
        text2?.text = "${game.minPlayers} à ${game.maxPlayers} joueurs"
    }
}
