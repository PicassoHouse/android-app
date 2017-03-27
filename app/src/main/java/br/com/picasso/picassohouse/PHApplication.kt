package br.com.picasso.picassohouse

import android.app.Application
import com.squareup.leakcanary.LeakCanary


class PHApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }

}
