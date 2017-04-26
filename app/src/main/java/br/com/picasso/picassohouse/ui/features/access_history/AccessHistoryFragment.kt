package br.com.picasso.picassohouse.ui.features.access_history

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SimpleCursorAdapter
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import br.com.picasso.picassohouse.PHApplication
import br.com.picasso.picassohouse.R
import br.com.picasso.picassohouse.models.AccessHistory
import br.com.picasso.picassohouse.models.Room
import br.com.picasso.picassohouse.ui.features.MainActivity
import br.com.picasso.picassohouse.ui.features.lights.AccessHistoryAdapter
import br.com.picasso.picassohouse.ui.features.lights.AccessHistoryPresenter
import br.com.picasso.picassohouse.utils.asString
import butterknife.BindView
import butterknife.ButterKnife


class AccessHistoryFragment :  Fragment() , AccessHistoryContract.View {

    private var parentActivity: MainActivity? = null
        get() = activity as MainActivity

    // --------------------------------------------------------
    // initializer
    // --------------------------------------------------------
    companion object {
        fun newInstance(): AccessHistoryFragment {
            val f = AccessHistoryFragment()
            val args = Bundle()
            f.arguments = args
            return f
        }
    }

    // --------------------------------------------------------
    // Properties
    // --------------------------------------------------------
    lateinit var historyAdapter: AccessHistoryAdapter
    lateinit var presenter : AccessHistoryPresenter
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
        presenter = AccessHistoryPresenter(apiService)
        presenter.attachView(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
        parentActivity?.setActionBarTitle(R.string.access_history)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_access_history, container, false)
        ButterKnife.bind(this, view)
        setup()
        return view
    }

    private fun setup() {
        historyAdapter = AccessHistoryAdapter(context)
        lvAccessHistory.adapter = historyAdapter
    }

    // --------------------------------------------------------
    // LightsContract.View methods
    // --------------------------------------------------------

    override fun showAccessHistory(history: List<AccessHistory>) {
        historyAdapter.setItems(history)
    }

}
