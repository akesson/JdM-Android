package mobi.akesson.jdm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.firebase.client.Firebase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Firebase.setAndroidContext(this)
    }
}
