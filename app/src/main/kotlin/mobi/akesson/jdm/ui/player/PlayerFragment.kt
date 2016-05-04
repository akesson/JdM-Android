package mobi.akesson.jdm.ui.player

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mobi.akesson.jdm.R
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.nestedScrollView

class PlayerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return PlayerFragmentUI().createView(AnkoContext.create(context, this))
    }
}

class PlayerFragmentUI : AnkoComponent<PlayerFragment> {

    override fun createView(ui: AnkoContext<PlayerFragment>) = with(ui) {
        nestedScrollView {
            verticalLayout {
                horizontalPadding = dimen(R.dimen.keyline_1_material)
                verticalPadding = dimen(R.dimen.padding_medium)

                editText() {
                    hint = "Nom"
                }
            }
        }
    }
}