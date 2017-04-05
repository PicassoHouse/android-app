package br.com.picasso.picassohouse

import android.app.Application
import br.com.picasso.picassohouse.networking.PicassoHouseAPI
import br.com.picasso.picassohouse.ui.features.MainActivity
import com.squareup.leakcanary.LeakCanary


class PHApplication : Application() {

    private var _phService: PicassoHouseAPI? = null
    val phService: PicassoHouseAPI
        get() {
            if(_phService == null)
                _phService = PicassoHouseAPI.create(this)

            return _phService!!
        }

    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }

}
