package mobi.akesson.jdm

import android.app.Application
import com.firebase.client.Firebase

class JdmApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Firebase.setAndroidContext(this)
    }
}
