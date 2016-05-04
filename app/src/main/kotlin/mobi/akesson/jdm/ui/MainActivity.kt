package mobi.akesson.jdm.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import mobi.akesson.jdm.R
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
    }
}

class MainActivityUI : AnkoComponent<MainActivity> {

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        relativeLayout {
            horizontalPadding = dimen(R.dimen.activity_horizontal_margin)
            verticalPadding = dimen(R.dimen.activity_vertical_margin)

            textView("Hello Jeux!")
        }
    }
}