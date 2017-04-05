package br.com.picasso.picassohouse.ui.features.lights_info

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import br.com.picasso.picassohouse.PHApplication
import br.com.picasso.picassohouse.R
import br.com.picasso.picassohouse.ui.features.MainActivity
import butterknife.BindView
import butterknife.ButterKnife
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet






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
    // --------------------------------------------------------
    // Views
    // --------------------------------------------------------
    @BindView(R.id.chart) lateinit var chartView: BarChart


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

        //TODO: plot data got from API

        val decSet = BarDataSet(arrayListOf(BarEntry(1f, 31f)), "Jan")
        decSet.color = context.resources.getColor(R.color.colorAccent)
        decSet.label = "Dec"

        val januarySet = BarDataSet(arrayListOf(BarEntry(1f, 32f)), "Jan")
        januarySet.color = context.resources.getColor(R.color.colorAccent)
        januarySet.label = "Jan"

        val februarySet = BarDataSet(arrayListOf(BarEntry(2f, 29f)), "Feb")
        februarySet.color = context.resources.getColor(R.color.colorAccent)
        februarySet.label = "Feb"

        val marchSet = BarDataSet(arrayListOf(BarEntry(3f, 34f)), "Mar")
        marchSet.color = context.resources.getColor(R.color.colorAccent)
        marchSet.label = "Mar"

        val aprilSet = BarDataSet(arrayListOf(BarEntry(4f, 30f)), "Apr")
        aprilSet.color = context.resources.getColor(R.color.colorAccent)
        aprilSet.label = "Apr"

        val dataSets = arrayListOf<IBarDataSet>(januarySet, februarySet, marchSet, aprilSet)

        val data = BarData(dataSets)

        chartView.data = data
        chartView.setFitBars(true)
        chartView.isScaleYEnabled = false
        chartView.isScaleXEnabled = false

        chartView.axisLeft.setDrawLabels(true)
        chartView.axisLeft.setDrawAxisLine(false)
        chartView.axisLeft.setDrawGridLines(true)
        chartView.axisLeft.setDrawZeroLine(true)

        chartView.axisRight.isEnabled = false

        chartView.xAxis.setDrawGridLines(false)
        chartView.xAxis.setDrawAxisLine(false)
        chartView.xAxis.setDrawLimitLinesBehindData(true)
        chartView.xAxis.granularity = 1f
        chartView.xAxis.valueFormatter = IndexAxisValueFormatter(arrayOf("", "Dec", "Jan", "Fev", "Mar", "Apr"))
        chartView.xAxis.setDrawLabels(true)
        chartView.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chartView.legend.isEnabled = false

        chartView.invalidate()
    }

    // --------------------------------------------------------
    // LightsContract.View methods
    // --------------------------------------------------------

}
