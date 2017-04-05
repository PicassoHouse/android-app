package br.com.picasso.picassohouse.ui.features.lights

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ListView
import br.com.picasso.picassohouse.PHApplication
import br.com.picasso.picassohouse.R
import br.com.picasso.picassohouse.models.Room
import br.com.picasso.picassohouse.ui.features.MainActivity
import butterknife.BindView
import butterknife.ButterKnife


class LightsFragment:  Fragment() , LightsContract.View {

    private var parentActivity: MainActivity? = null
        get() = activity as MainActivity

    // --------------------------------------------------------
    // initializer
    // --------------------------------------------------------
    companion object {
        fun newInstance(): LightsFragment {
            val f = LightsFragment()
            val args = Bundle()
            f.arguments = args
            return f
        }
    }

    // --------------------------------------------------------
    // Properties
    // --------------------------------------------------------
    lateinit var lightsAdapter: LightsAdapter
    lateinit var presenter : LightsPresenter
    // --------------------------------------------------------
    // Views
    // --------------------------------------------------------
    @BindView(R.id.lv_lights) lateinit var lvLights: ListView


    // --------------------------------------------------------
    // Lifecycler
    // --------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = (activity.applicationContext as PHApplication).phService
        presenter = LightsPresenter(apiService)
        presenter.attachView(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_lights, container, false)
        ButterKnife.bind(this, view)
        setup()
        return view
    }

    private fun setup() {
        lightsAdapter = LightsAdapter(context)
        lightsAdapter.setOnSwitchChangeListener(presenter::lightDidChangeStatus)
        lvLights.adapter = lightsAdapter
    }

    // --------------------------------------------------------
    // LightsContract.View methods
    // --------------------------------------------------------

    override fun showRooms(items: List<Room>) {
        lightsAdapter.setItems(items)
    }
}
