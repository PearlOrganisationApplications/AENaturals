package com.aenatural.aenaturals.salesmans.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.common.Models.SellerDataModel
import com.aenatural.aenaturals.distributors.Adapters.DistributorAllOrderAdapter
import com.aenatural.aenaturals.distributors.fragments.DistributorHomeFrag
import com.aenatural.aenaturals.salesmans.Adapters.PendingOrdersAdapter
import com.aenatural.aenaturals.salesmans.Adapters.PendingPaymentAdapter
import com.aenatural.aenaturals.salesmans.Adapters.ReturnOrderAdapter
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textview.MaterialTextView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DistributorMoreFragment : Fragment() {
    lateinit var salesmanMoreRecyclerView: RecyclerView
    lateinit var pendingPaymentRecycler: RecyclerView
    lateinit var returnOrdersRecycler: RecyclerView
    lateinit var pendingOrderData: ArrayList<SellerDataModel>
    lateinit var pendingPayment: ArrayList<SellerDataModel>
    lateinit var returnOrders: ArrayList<SellerDataModel>
    lateinit var pendingOrderCard: CardView
    lateinit var returnOrderCard: CardView
    lateinit var pendingPaymentCard: CardView
    lateinit var distributor_totalProfitCard: CardView
    lateinit var distributorProfit: LinearLayout
    private lateinit var dist_all_orders_recycler: RecyclerView
    lateinit var return_order_layout: ConstraintLayout
    lateinit var pending_payment_layout: ConstraintLayout
    lateinit var pendingorderslayout: ConstraintLayout
    private var cart_item_list = ArrayList<RetailerDataModel>()
    private lateinit var distributor_allOrder_layout: LinearLayout
    lateinit var distributor_myOrder_button: LinearLayout
    lateinit var dateFilterLayout: LinearLayout
    private lateinit var startDateTextView: TextView
    private lateinit var endDateTextView: TextView
    private lateinit var dateFilterTextView: TextView
    private lateinit var pendingOrderDate: LinearLayout
    private lateinit var pendingPaymentDate: LinearLayout
    private lateinit var returnOrderDate: LinearLayout
    var visible_View = ""

    private val myCalendar = Calendar.getInstance()
    //getting current day,month and year.
    /*val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_distributor_products, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backPress()
        requireActivity().findViewById<LinearLayout>(R.id.headerdistributor).visibility = View.GONE
        initializeViews(view)
        initDataModels(view)
        initrecyclerViews(view)
        initclickListener(view)

        var bundle = arguments
        if (bundle != null) {
            visible_View = bundle.getString("section").toString()
            if(visible_View == "1")
                view3()
            else if(visible_View == "2")
                view1()
            else if(visible_View == "3")
                view2()
            else if(visible_View == "4")
                view4()
            else if(visible_View == "5")
                view5()
        }
    }

    private fun initclickListener(view: View) {
        pendingPaymentCard.setOnClickListener {
            view1()
        }
        returnOrderCard.setOnClickListener {
            view2()
        }
        pendingOrderCard.setOnClickListener {
            view3()
        }
        distributor_totalProfitCard.setOnClickListener {
            view4()

        }
        distributor_myOrder_button.setOnClickListener {
            view5()
        }
        dateFilterLayout.setOnClickListener {


            val datePicker = MaterialDatePicker.Builder.dateRangePicker().build()
            datePicker.show(requireActivity().supportFragmentManager, "DatePicker")


            // Setting up the event for when ok is clicked
         /*   datePicker.addOnPositiveButtonClickListener {
                Toast.makeText(requireContext(), "${datePicker.headerText} is selected", Toast.LENGTH_LONG).show()
            }*/
            /*var endDate="";
            var startDate="";
            var dateRange="";
            */

            datePicker.addOnPositiveButtonClickListener { selection ->
                val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val startDate = format.format(Date(selection.first ?: 0L)).toString()
                val endDate = format.format(Date(selection.second ?: 0L)).toString()
                val dateRange = "$startDate - $endDate"

                /* startDateTextView.text = startDate.toString()
                endDateTextView.text = endDate.toString()*/
                /*startDateTextView.setText(startDate)
                endDateTextView.setText(endDate)*/

                Log.d("DatePicker", "Setting start date: $startDate")
                startDateTextView.text = dateRange.toString()
                Log.d("DatePicker", "Setting end date: $endDate")
                endDateTextView.text = endDate
                Log.d("startDate and endDat5e " ,
                    startDateTextView.toString() + endDateTextView.toString()
                )
                Toast.makeText(requireContext(), "$dateRange is selected", Toast.LENGTH_LONG).show()
            }

            // Setting up the event for when cancelled is clicked
            datePicker.addOnNegativeButtonClickListener {
                Toast.makeText(requireContext(), "${datePicker.headerText} is cancelled", Toast.LENGTH_LONG).show()

            }

            // Setting up the event for when back button is pressed
            datePicker.addOnCancelListener {
                Toast.makeText(requireContext(), "Date Picker Cancelled", Toast.LENGTH_LONG).show()
            }

        }

        pendingOrderDate.setOnClickListener {
            /*val datePickerDialog = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener
            { view, year, monthOfYear, dayOfMonth ->

            }, year, month, day)
            datePickerDialog.show()*/
            showDatePicker()
        }

    }

    private fun view5() {
        pendingorderslayout.visibility = View.GONE
        pending_payment_layout.visibility = View.GONE
        return_order_layout.visibility = View.GONE
        distributorProfit.visibility = View.GONE
        distributor_allOrder_layout.visibility = View.VISIBLE
    }

    private fun view4() {
        pendingorderslayout.visibility = View.GONE
        pending_payment_layout.visibility = View.GONE
        return_order_layout.visibility = View.GONE
        distributorProfit.visibility = View.VISIBLE
        distributor_allOrder_layout.visibility = View.GONE
    }

    private fun view3() {
        pendingorderslayout.visibility = View.VISIBLE
        pending_payment_layout.visibility = View.GONE
        return_order_layout.visibility = View.GONE
        distributorProfit.visibility = View.GONE
        distributor_allOrder_layout.visibility = View.GONE
    }

    private fun view2() {
        pendingorderslayout.visibility = View.GONE
        pending_payment_layout.visibility = View.GONE
        return_order_layout.visibility = View.VISIBLE
        distributorProfit.visibility = View.GONE
        distributor_allOrder_layout.visibility = View.GONE
    }

    private fun view1() {
        pendingorderslayout.visibility = View.GONE
        pending_payment_layout.visibility = View.VISIBLE
        return_order_layout.visibility = View.GONE
        distributorProfit.visibility = View.GONE
        distributor_allOrder_layout.visibility = View.GONE
    }

    private fun initDataModels(view: View) {
        pendingOrderData = ArrayList()
        pendingPayment = ArrayList()
        returnOrders = ArrayList()
        for (i in 0..5) {
            pendingOrderData.add(SellerDataModel("", "", "", "", ""))
            pendingPayment.add(SellerDataModel("", "", "", "", ""))
            returnOrders.add(SellerDataModel("", "", "", "", ""))
        }
    }

    private fun initrecyclerViews(view: View) {
        salesmanMoreRecyclerView.adapter = PendingOrdersAdapter(requireContext(), pendingOrderData)
        salesmanMoreRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        pendingPaymentRecycler.adapter = PendingPaymentAdapter(requireContext(), pendingOrderData)
        pendingPaymentRecycler.layoutManager = LinearLayoutManager(requireContext())

        dist_all_orders_recycler.adapter = DistributorAllOrderAdapter(cart_item_list)
        dist_all_orders_recycler.layoutManager = LinearLayoutManager(requireContext())
        returnOrdersRecycler.adapter = ReturnOrderAdapter(requireContext(), pendingOrderData)
        returnOrdersRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initializeViews(view: View) {
        dist_all_orders_recycler = view.findViewById(R.id.dist_all_orders_recycler)
        salesmanMoreRecyclerView = view.findViewById(R.id.salesmanMoreRecyclerView)
        pendingPaymentRecycler = view.findViewById(R.id.pendingPaymentRecycler)
        returnOrdersRecycler = view.findViewById(R.id.returnOrdersRecycler)
        pendingPaymentCard = view.findViewById(R.id.distributor_pendingPaymentCard)
        returnOrderCard = view.findViewById(R.id.distributor_returnOrderCard)
        pendingOrderCard = view.findViewById(R.id.distributor_pendingOrderCard)
        return_order_layout = view.findViewById(R.id.return_order_layout)
        pending_payment_layout = view.findViewById(R.id.pending_payment_layout)
        pendingorderslayout = view.findViewById(R.id.pendingorderslayout)
        distributor_totalProfitCard = view.findViewById(R.id.distributor_totalProfitCard)
        distributorProfit = view.findViewById(R.id.distributorProfit)
        distributor_allOrder_layout = view.findViewById(R.id.distributor_allOrder_layout)
        distributor_myOrder_button = view.findViewById(R.id.distributor_myOrder_button)
        dateFilterLayout = view.findViewById(R.id.dateFilterLayout)
        startDateTextView = view.findViewById(R.id.startDateTextViewX)
        endDateTextView = view.findViewById(R.id.endDateTextViewX)
        pendingOrderDate = view.findViewById(R.id.pendingOrderDate)
        pendingPaymentDate = view.findViewById(R.id.pendingPaymentDate)
        returnOrderDate = view.findViewById(R.id.returnOrderDate)
        dateFilterTextView = view.findViewById(R.id.dateFilterTextView)

        initData()
    }
    private fun backPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.DashboardFrameLayout,
                        DistributorHomeFrag()
                    ).commit()
                // Handle the back button press
                // Call the desired function or perform any necessary actions
            }
        })
    }


    private fun initData() {
        cart_item_list = ArrayList()
        for (i in 0..10) {
            cart_item_list.add(RetailerDataModel("", "", "", ""))
        }
    }

    private fun showDatePicker() {
        val date = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, day)
            updateLabel()
        }

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            R.style.MyDatePickerDialogTheme, // use your custom theme here
            date,
            myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        )

        // Set the maximum and minimum date for the DatePickerDialog
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.datePicker.minDate = getMinimumDate()

        datePickerDialog.show()
    }
    private fun updateLabel() {
        val myFormat = "yyyy/MM/dd"
//        val formattedDate = myFormat.replace("/", "-")
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        dateFilterTextView.text = dateFormat.format(myCalendar.time)
    }
    private fun getMinimumDate(): Long {
        val minDateCalendar = Calendar.getInstance()
        minDateCalendar.add(Calendar.YEAR, -100) // Set 100 years ago from now
        return minDateCalendar.timeInMillis
    }
}