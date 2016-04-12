package mobi.akesson.jdm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.firebase.client.Firebase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Firebase.setAndroidContext(this)

        val textField = findViewById(R.id.firebase_textfield) as EditText

        textField.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                textField.setText("Focus perdu")
            }
        }
    }
}
