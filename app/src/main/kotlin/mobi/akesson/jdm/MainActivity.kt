package mobi.akesson.jdm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    val dataStore : DataStore by lazy {
        DataStore(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textField = findViewById(R.id.firebase_textfield) as EditText
        textField.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                textField.setText("Focus perdu")
            }
        }
    }
}
