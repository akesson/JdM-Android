package mobi.akesson.jdm

import com.firebase.client.Firebase
import android.content.Context

class DataStore(context: Context) {

    init {
        Firebase.setAndroidContext(context)
    }
}