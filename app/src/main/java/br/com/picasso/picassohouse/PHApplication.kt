package br.com.picasso.picassohouse

import android.app.Application
import br.com.picasso.picassohouse.networking.PicassoHouseAPI
import br.com.picasso.picassohouse.ui.features.MainActivity
import com.squareup.leakcanary.LeakCanary


class PHApplication : Application() {

    var phService: PicassoHouseAPI? = null
        get() {
            if(phService == null)
                phService = PicassoHouseAPI.create(this)

            return phService
        }

    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }

}
