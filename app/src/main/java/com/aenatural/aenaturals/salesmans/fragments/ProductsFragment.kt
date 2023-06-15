package com.aenatural.aenaturals.salesmans.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.salesmans.AllProductsAdapter
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.common.Models.SellerDataModel
import com.aenatural.aenaturals.customers.fragments.CustomerHomeFrag
import com.aenatural.aenaturals.salesmans.Adapters.PendingOrderVH
import com.aenatural.aenaturals.salesmans.Adapters.PendingOrdersAdapter
import com.aenatural.aenaturals.salesmans.Adapters.PendingPaymentAdapter
import com.aenatural.aenaturals.salesmans.Adapters.ReturnOrderAdapter
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ProductsFragment : Fragment() {
    lateinit var salesmanMoreRecyclerView:RecyclerView
    lateinit var pendingPaymentRecycler:RecyclerView
    lateinit var returnOrdersRecycler:RecyclerView
    lateinit var pendingOrderData:ArrayList<SellerDataModel>
    lateinit var pendingPayment:ArrayList<SellerDataModel>
    lateinit var returnOrders:ArrayList<SellerDataModel>
    lateinit var pendingOrderCard:CardView
    lateinit var returnOrderCard:CardView
    lateinit var pendingPaymentCard:CardView
    lateinit var salesman_pendingItem:LinearLayout

    lateinit var return_order_layout:ConstraintLayout
    lateinit var pending_payment_layout:ConstraintLayout
    lateinit var pendingorderslayout:ConstraintLayout
    private lateinit var pendingOrderDate: LinearLayout
    private lateinit var pendingPaymentDate: LinearLayout
    private lateinit var returnOrderDate: LinearLayout
    private lateinit var dateFilterTextView: TextView
    private lateinit var dateFilterTextViewPP: TextView
    private lateinit var dateFilterTextViewRO: TextView

    private val pendingOrderCalendar = Calendar.getInstance()
    private val returnOrderCalendar = Calendar.getInstance()
    private val pendingPaymentCalendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backPress()
        requireActivity().findViewById<LinearLayout>(R.id.include2).visibility =View.GONE
        initializeViews(view)
        initDataModels(view)
        initrecyclerViews(view)
        initclickListener(view)
        val bundle = arguments
        if (bundle != null) {
            val visibleView = bundle.getString("section").toString()
            when (visibleView) {
                "1" -> view1()
                "2" -> view2()
                "3" -> view3()
            }
        }
    }

    private fun initclickListener(view: View) {
        pendingPaymentCard.setOnClickListener {
           view2()
        }
        returnOrderCard.setOnClickListener {
           view3()
        }
        pendingOrderCard.setOnClickListener {
           view1()
        }
        pendingOrderDate.setOnClickListener {
            /*val datePickerDialog = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener
            { view, year, monthOfYear, dayOfMonth ->

            }, year, month, day)
            datePickerDialog.show()*/
            showDatePicker(pendingOrderCalendar,dateFilterTextView)
        }
        pendingPaymentDate.setOnClickListener {
            showDatePicker(pendingPaymentCalendar,dateFilterTextViewPP)
        }
        returnOrderDate.setOnClickListener {
            showDatePicker(returnOrderCalendar,dateFilterTextViewRO)
        }

    }

    private fun initDataModels(view: View) {
        pendingOrderData = ArrayList()
        pendingPayment = ArrayList()
        returnOrders = ArrayList()
    for(i in 0..5){
    pendingOrderData.add(SellerDataModel("","","","",""))
    pendingPayment.add(SellerDataModel("","","","",""))
    returnOrders.add(SellerDataModel("","","","",""))
}
    }

    private fun initrecyclerViews(view: View) {
        salesmanMoreRecyclerView.adapter = PendingOrdersAdapter(requireContext(),pendingOrderData)
        salesmanMoreRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        pendingPaymentRecycler.adapter = PendingPaymentAdapter(requireContext(),pendingOrderData)
        pendingPaymentRecycler.layoutManager = LinearLayoutManager(requireContext())

        returnOrdersRecycler.adapter = ReturnOrderAdapter(requireContext(),pendingOrderData)
        returnOrdersRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initializeViews(view: View) {
        salesmanMoreRecyclerView = view.findViewById(R.id.salesmanMoreRecyclerView)
        pendingPaymentRecycler = view.findViewById(R.id.pendingPaymentRecycler)
        returnOrdersRecycler = view.findViewById(R.id.returnOrdersRecycler)
        salesman_pendingItem = view.findViewById(R.id.salesman_pendingItem)

        pendingPaymentCard = view.findViewById(R.id.pendingPaymentCard)
        returnOrderCard = view.findViewById(R.id.returnOrderCard)
        pendingOrderCard = view.findViewById(R.id.pendingOrderCard)

        return_order_layout = view.findViewById(R.id.return_order_layout)
        pending_payment_layout = view.findViewById(R.id.pending_payment_layout)
        pendingorderslayout = view.findViewById(R.id.pendingorderslayout)

        pendingOrderDate = view.findViewById(R.id.pendingOrderDate)
        pendingPaymentDate = view.findViewById(R.id.pendingPaymentDate)
        returnOrderDate = view.findViewById(R.id.returnOrderDate)
        dateFilterTextView = view.findViewById(R.id.dateFilterTextView)
        dateFilterTextViewPP = view.findViewById(R.id.dateFilterTextViewPP)
        dateFilterTextViewRO = view.findViewById(R.id.dateFilterTextViewRO)

    }
    private fun backPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.salesDashboardFrameLayout,
                        HomeFragment()
                    ).commit()
            }
        })
    }

    /*  fun List<Item>.filterByDate(date: Date): List<Item> {
          return this.filter { item ->
              // Replace `item.date` with the actual date property of your item
              item.date == date
          }
      }*/

    fun view2() {
        pendingorderslayout.visibility =View.GONE
        pending_payment_layout.visibility =View.VISIBLE
        return_order_layout.visibility =View.GONE
    }
    fun view3() {
        pendingorderslayout.visibility =View.GONE
        pending_payment_layout.visibility =View.GONE
        return_order_layout.visibility =View.VISIBLE
    }
    fun view1() {
        pendingorderslayout.visibility =View.VISIBLE
        pending_payment_layout.visibility =View.GONE
        return_order_layout.visibility =View.GONE
    }

    private fun showDatePicker(calendar: Calendar, dateTextView: TextView) {
        val date = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            updateLabel(dateTextView,calendar)
        }

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            R.style.MyDatePickerDialogTheme, // use your custom theme here
            date,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        // Set the maximum and minimum date for the DatePickerDialog
        val maxDateCalendar = Calendar.getInstance()
//        maxDateCalendar.add(Calendar.YEAR, 1) // Add 10 years to the current date
//        datePickerDialog.datePicker.maxDate = maxDateCalendar.timeInMillis
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.datePicker.minDate = getMinimumDate()
        // Set the maximum date for the DatePickerDialog

        datePickerDialog.show()
    }

    private fun updateLabel(dateTextView: TextView, calendar: Calendar) {
        val myFormat = "yyyy/MM/dd"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        dateTextView.text = dateFormat.format(calendar.time)
    }

    private fun getMinimumDate(): Long {
        val minDateCalendar = Calendar.getInstance()
        minDateCalendar.add(Calendar.YEAR, -100) // Set 100 years ago from now
        return minDateCalendar.timeInMillis
    }

}