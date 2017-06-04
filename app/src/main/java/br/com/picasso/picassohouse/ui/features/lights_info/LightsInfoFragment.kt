package br.com.picasso.picassohouse.ui.features.lights_info

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ListView
import br.com.picasso.picassohouse.PHApplication
import br.com.picasso.picassohouse.R
import br.com.picasso.picassohouse.models.LightHistory
import br.com.picasso.picassohouse.ui.features.MainActivity
import butterknife.BindView
import butterknife.ButterKnife


class LightsInfoFragment:  Fragment() , LightsInfoContract.View {

    private var parentActivity: MainActivity? = null
        get() = activity as MainActivity


    // --------------------------------------------------------
    // initializer
    // --------------------------------------------------------
    companion object {
        fun newInstance(): LightsInfoFragment {
            val f = LightsInfoFragment()
            val args = Bundle()
            f.arguments = args
            return f
        }
    }

    // --------------------------------------------------------
    // Properties
    // --------------------------------------------------------
    lateinit var presenter : LightsInfoPresenter
    private lateinit var historyAdapter: LightsHistoryAdapter

    // --------------------------------------------------------
    // Views
    // --------------------------------------------------------
    @BindView(R.id.lv_access_history) lateinit var lvAccessHistory: ListView

    // --------------------------------------------------------
    // Lifecycler
    // --------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = (activity.applicationContext as PHApplication).phService
        presenter = LightsInfoPresenter(apiService)
        presenter.attachView(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_lights_info, container, false)
        ButterKnife.bind(this, view)
        setup()
        return view
    }

    private fun setup() {
        parentActivity?.setActionBarTitle(R.string.lights_info)

        historyAdapter = LightsHistoryAdapter(context)
        lvAccessHistory.adapter = historyAdapter
    }

    // --------------------------------------------------------
    // LightsContract.View methods
    // --------------------------------------------------------

    override fun showAccessHistory(history: List<LightHistory>) {
        historyAdapter.setItems(history)
    }
}
