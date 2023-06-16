package com.aenatural.aenaturals.salesmans

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.common.Models.SellerDataModel
import com.aenatural.aenaturals.salesmans.Adapters.PendingPaymentAdapter
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.model.GradientColor
import com.github.mikephil.charting.utils.ColorTemplate
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel

class SaleDetailsActivity : BaseClass() {
    lateinit var pieChart: PieChart

    lateinit var pendingPayment:ArrayList<SellerDataModel>

    lateinit var barChart: BarChart

    lateinit var barData: BarData
lateinit var saleHistoryback:ImageView
    lateinit var barDataSet: BarDataSet

    lateinit var barEntriesList: ArrayList<BarEntry>
    lateinit var orderHistoryRecycler:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
        setchart()
    }

    private fun setchart() {
        // on below line we are calling get bar
        // chart data to add data to our array list
        getBarChartData()

        // on below line we are initializing our bar data set
        barDataSet = BarDataSet(barEntriesList, "Monthly Sales")

        // on below line we are initializing our bar data
        barData = BarData(barDataSet)

        // on below line we are setting data to our bar chart
        barChart.data = barData

        // on below line we are setting colors for our bar chart text
        barDataSet.valueTextColor = Color.BLACK

        // on below line we are setting color for our bar data set
        barDataSet.setColor(resources.getColor(R.color.darkgreen))

        // on below line we are setting text size
        barDataSet.valueTextSize = 16f

        // on below line we are enabling description as false
        barChart.description.isEnabled = false
    }

    override fun setLayoutXml() {
        getLightGreentheme()
        setContentView(R.layout.activity_customer_registration)
    }

    override fun initializeViews() {

        pieChart = findViewById(R.id.salesman_piechart)
        barChart = findViewById(R.id.chart1);
        saleHistoryback = findViewById(R.id.saleHistoryback)
        initDataModels()

        orderHistoryRecycler = findViewById(R.id.saleshistoryRecycler)
        orderHistoryRecycler.adapter = PendingPaymentAdapter(this,pendingPayment)
        orderHistoryRecycler.layoutManager = LinearLayoutManager(this)
        setPieChart()
    }

    private fun initDataModels() {
        pendingPayment = ArrayList()
        for (i in 0..5) {
            pendingPayment.add(SellerDataModel("", "", "", "", ""))
        }
    }

    private fun setPieChart(){

        pieChart.isUseInnerValue = false
        pieChart.innerValueString = "Sales"
        pieChart.innerPaddingColor = R.color.midgreen
        pieChart.isUseInnerPadding= false

        pieChart.addPieSlice(
            PieModel(
                "Salesman 1", 40F,
                Color.parseColor("#004D40")
            )
        )
        pieChart.addPieSlice(
            PieModel(
                "Salesman 2", 30F,
                Color.parseColor("#00BFA5")
            )
        )
        pieChart.addPieSlice(
            PieModel(
                "Salesman 3", 20F,
                Color.parseColor("#1DE9B6")
            )
        )
        pieChart.addPieSlice(
            PieModel(
                "others", 10F,
                Color.parseColor("#64FFDA")
            )
        )
        pieChart.startAnimation();



    }
    override fun initializeClickListners() {
        saleHistoryback.setOnClickListener {
            onBackPressed()
        }


    }



    override fun initializeInputs() {
    }

    override fun initializeLabels() {
    }



    private fun getBarChartData() {
        barEntriesList = ArrayList()


        barEntriesList.add(BarEntry(1f, 1f))
        barEntriesList.add(BarEntry(2f, 2f))
        barEntriesList.add(BarEntry(3f, 3f))
        barEntriesList.add(BarEntry(4f, 2f))
        barEntriesList.add(BarEntry(5f, 5f))
        barEntriesList.add(BarEntry(6f, 3f))
        barEntriesList.add(BarEntry(7f, 5f))

    }
}