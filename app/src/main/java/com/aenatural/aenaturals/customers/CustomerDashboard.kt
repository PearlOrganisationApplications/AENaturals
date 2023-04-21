package com.aenatural.aenaturals.customers

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.SliderModel
import com.aenatural.aenaturals.customers.adapters.SliderAdapter
import com.aenatural.aenaturals.R
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel
import java.util.*
import kotlin.collections.ArrayList


class CustomerDashboard : AppCompatActivity() {

    // Create the object of TextView
    // and PieChart class
    var tv_dashboardtype: TextView? = null;
    var tvR: TextView? = null;
    var tvPython:TextView? =null; // Create the object of TextView
    // and PieChart class
    var tvCPP:TextView? = null;
    var tvJava: // Create the object of TextView
    // and PieChart class
    TextView? = null
    var pieChart: PieChart? = null
    lateinit var rv_slider: RecyclerView
    lateinit var sliderList:ArrayList<SliderModel>
    private val linearSnap = LinearSnapHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_dashboard)


        tv_dashboardtype = findViewById(R.id.tv_dashboardtype);
        rv_slider = findViewById(R.id.rv_slider)
        loadSliderData();
     //   pieChart = findViewById(R.id.piechart);

        //setData();
    }

    private fun loadSliderData() {

       /* sliderList= ArrayList()
        for(i in 0..5)
            sliderList.add(SliderModel("1","https://cdn-ilcil.nitrocdn.com/gFzlebLlQaMswBWjmNqzvqWfTwaIjVkl/assets/images/optimized/rev-8524c2e/wp-content/uploads/2023/03/3-1.webp"))
     */   // this creates a vertical layout Manager
     //   rv_slider.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<SliderModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..5) {
            data.add(SliderModel("1", R.drawable.ic_logo_aen))
        }
        val adapter = SliderAdapter(data)
        linearSnap.attachToRecyclerView(rv_slider)
        Timer().schedule(object : TimerTask() {
            override fun run() {
                if ((rv_slider.layoutManager as LinearLayoutManager)!!.findFirstCompletelyVisibleItemPosition() < adapter!!.itemCount - 1) {
                    rv_slider.layoutManager!!.smoothScrollToPosition(
                        rv_slider,
                        RecyclerView.State(),
                        (rv_slider.layoutManager as LinearLayoutManager)!!.findFirstCompletelyVisibleItemPosition() + 1
                    )
                } else if ((rv_slider.layoutManager as LinearLayoutManager)!!.findFirstCompletelyVisibleItemPosition() < adapter!!.itemCount - 1) {
                    rv_slider.layoutManager!!.smoothScrollToPosition(rv_slider, RecyclerView.State(), 0)
                }else{
                    rv_slider.smoothScrollToPosition(0);
                }
            }
        },0, 2000)
        // This will pass the ArrayList to our Adapter

        // Setting the Adapter with the recyclerview
        rv_slider.adapter = adapter

    }

    @SuppressLint("SetTextI18n")
    private fun setData() {
        // Set the data and color to the pie chart
        pieChart!!.addPieSlice(
            PieModel(
                "R", 40F,
                Color.parseColor("#FFA726")
            )
        )
        pieChart!!.addPieSlice(
            PieModel(
                "Python", 30F,
                Color.parseColor("#66BB6A")
            )
        )
        pieChart!!.addPieSlice(
            PieModel(
                "C++", 5F,
                Color.parseColor("#EF5350")
            )
        )
        pieChart!!.addPieSlice(
            PieModel(
                "Java",25F,
                Color.parseColor("#29B6F6")
            )
        )

        // To animate the pie chart
        pieChart!!.startAnimation()
    }
}