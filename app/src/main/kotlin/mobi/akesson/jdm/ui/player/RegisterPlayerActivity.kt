package mobi.akesson.jdm.ui.player

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import mobi.akesson.jdm.R
import org.jetbrains.anko.alert

class RegisterPlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_clear_white_24dp)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_validate, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> consume { cancelRegistration() }
            R.id.action_validate -> consume { validateRegistration() }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        cancelRegistration()
    }

    private fun cancelRegistration() {
        object : DialogFragment() {
            override fun onCreateDialog(savedInstanceState: Bundle?) = alert(R.string.discard_new_player) {
                positiveButton(R.string.erase) { RegisterPlayerActivity@finish() }
                negativeButton { }
            }.builder.create()
        }.show(supportFragmentManager, "cancel")
    }

    private fun validateRegistration() {
        // TODO
    }

    inline fun consume(f: () -> Unit): Boolean {
        f()
        return true
    }
}
