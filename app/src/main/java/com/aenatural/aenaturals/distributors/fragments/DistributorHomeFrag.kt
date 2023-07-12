package com.aenatural.aenaturals.distributors.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.SalesmanListService
import com.aenatural.aenaturals.apiservices.datamodels.SalesmanDetail
import com.aenatural.aenaturals.apiservices.datamodels.SalesmanListDM
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.common.Login
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.common.Models.SellerDataModel
import com.aenatural.aenaturals.common.RetrofitClient
import com.aenatural.aenaturals.distributors.SellerAdapter
import com.aenatural.aenaturals.salesmans.BottomSectionAdapter
import com.aenatural.aenaturals.salesmans.SecondBottomSectionAdapter
import com.aenatural.aenaturals.salesmans.fragments.DistributorMoreFragment
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class DistributorHomeFrag : Fragment() {
    lateinit var addSellers: LinearLayout
    lateinit var itemRequest: LinearLayout
    lateinit var recyclerView1: RecyclerView
    lateinit var recyclerView2: RecyclerView
    lateinit var pieChart: PieChart
    lateinit var sellerList: ArrayList<SellerDataModel>
    lateinit var itemList: ArrayList<RetailerDataModel>
    lateinit var distributor_pendingOrder: LinearLayout
    lateinit var distributor_pendingPayment: LinearLayout
    lateinit var distributor_totalReturns: LinearLayout
    lateinit var distributor_totalProfit: LinearLayout
    lateinit var distributor_myOrder: LinearLayout
    lateinit var session:Session


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_distributor_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<LinearLayout>(R.id.headerdistributor).visibility =
            View.VISIBLE
        backPress()
        initializeViews(view)
        initializeLabels()
        initializeClickListners()
        session = Session(requireActivity())
        salesmanListApi()
    }

    private fun salesmanListApi() {

        var apiService = RetrofitClient.retrofit.create(SalesmanListService::class.java)
        var call = apiService.getSalesmanList("Bearer "+session.token)
        call.enqueue(object:Callback<SalesmanListDM>{
            override fun onResponse(
                call: Call<SalesmanListDM>,
                response: Response<SalesmanListDM>
            ) {
if(response.isSuccessful){
    var data = response.body()
    if (data != null) {
        if(data.status.equals("false")){
            try{
                Toast.makeText(requireContext(),"Session Expired!! Login Again",Toast.LENGTH_SHORT).show()
                startActivity(Intent(requireContext(),Login::class.java))
            }catch (_:Exception){

            } }
        else{
            salesmanListSlider(data.salesman,data.image_endpoint)
        }
    }

}
            }

            override fun onFailure(call: Call<SalesmanListDM>, t: Throwable) {
            }

        })
    }

    private fun backPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showExitConfirmationDialog()
                // Handle the back button press
                // Call the desired function or perform any necessary actions
            }
        })
    }

    public fun initializeViews(view: View) {
        addSellers = view.findViewById(R.id.addSellers)
        recyclerView1 = view.findViewById(R.id.distributorTopRecycler)
        pieChart = view.findViewById(R.id.distibutorpiechart)
        recyclerView2 = view.findViewById(R.id.distributorMidRecycler)
        itemRequest = view.findViewById(R.id.itemRequest)
        distributor_pendingOrder = view.findViewById(R.id.distributor_pendingOrder)
        distributor_pendingPayment = view.findViewById(R.id.distributor_pendingPayment)
        distributor_totalReturns = view.findViewById(R.id.distributor_totalReturns)
        distributor_totalProfit = view.findViewById(R.id.distributor_totalProfit)
        distributor_myOrder = view.findViewById(R.id.distributor_myOrder)

        setPieChart()

    }

    private fun setPieChart() {

        pieChart.isUseInnerValue = false
        pieChart.innerValueString = "Sales"
        pieChart.innerPaddingColor = R.color.midgreen
        pieChart.isUseInnerPadding = false
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
        pieChart.startAnimation()


    }

    public fun initializeClickListners() {
        var args = Bundle()

        distributor_pendingOrder.setOnClickListener {
            args.putString("section","1");
            var frag = DistributorMoreFragment()
            frag.arguments =args

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.DashboardFrameLayout,
                    frag
                ).commit()
        }
        distributor_pendingPayment.setOnClickListener {
            args.putString("section","2");
            var frag = DistributorMoreFragment()
            frag.arguments =args
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.DashboardFrameLayout,
                    frag
                ).commit()
        }
        distributor_totalReturns.setOnClickListener {
            args.putString("section","3");
            var frag = DistributorMoreFragment()
            frag.arguments =args
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.DashboardFrameLayout,
                    frag
                ).commit()
        }
        distributor_totalProfit.setOnClickListener {
            args.putString("section","4");
            var frag = DistributorMoreFragment()
            frag.arguments =args
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.DashboardFrameLayout,
                    frag
                ).commit()
        }
        distributor_myOrder.setOnClickListener {
            args.putString("section","5");
            var frag = DistributorMoreFragment()
            frag.arguments =args
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.DashboardFrameLayout,
                    frag
                ).commit()
        }
    }

    public fun salesmanListSlider(sellers:List<SalesmanDetail>,endpoint:String){
        recyclerView1.adapter = SellerAdapter(sellers,endpoint)

        try {
            recyclerView1.layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false)
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    if ((recyclerView1.layoutManager as LinearLayoutManager)!!.findFirstCompletelyVisibleItemPosition() < sellerList.size - 1) {
                        recyclerView1.layoutManager!!.smoothScrollToPosition(
                            recyclerView1,
                            RecyclerView.State(),
                            (recyclerView1.layoutManager as LinearLayoutManager)!!.findFirstCompletelyVisibleItemPosition() + 1
                        )
                    } else if ((recyclerView1.layoutManager as LinearLayoutManager)!!.findFirstCompletelyVisibleItemPosition() < sellerList.size - 1) {
                        recyclerView1.layoutManager!!.smoothScrollToPosition(recyclerView1,
                            RecyclerView.State(),
                            0)
                    } else {
                        recyclerView1.smoothScrollToPosition(0);
                    }
                }
            }, 0, 1500)


        }
        catch (_:Exception){
salesmanListSlider(sellers,endpoint)
        }


    }


    public fun initializeLabels() {
        initData()

        recyclerView2.adapter = BottomSectionAdapter(itemList)
        recyclerView2.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL, false)

        recyclerView2.adapter = SecondBottomSectionAdapter(itemList)
        recyclerView2.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)

    }

    private fun initData() {
        sellerList = ArrayList()
        itemList = ArrayList()
        for (i in 0..5)
            sellerList.add(SellerDataModel("Rajesh K",
                "rajeshisamazing@gmail.com",
                "RR Nagar Banglore",
                "+9182384898",
                ""))
        for (i in 0..5)
            itemList.add(RetailerDataModel("Rajesh K",
                "rajeshisamazing@gmail.com",
                "RR Nagar Banglore",
                "+9182384898"))
    }


    private fun showExitConfirmationDialog() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Exit")
        alertDialogBuilder.setMessage("Do you want to exit the app?")
        alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
            // Exit the app
            requireActivity().finish()
        }
        alertDialogBuilder.setNegativeButton("No", null)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}