package com.aenatural.aenaturals.salesmans.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.salesmans.AllProductsAdapter
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.common.Models.SellerDataModel
import com.aenatural.aenaturals.salesmans.Adapters.PendingOrderVH
import com.aenatural.aenaturals.salesmans.Adapters.PendingOrdersAdapter
import com.aenatural.aenaturals.salesmans.Adapters.PendingPaymentAdapter

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

    lateinit var return_order_layout:ScrollView
    lateinit var pending_payment_layout:LinearLayout
    lateinit var pendingorderslayout:LinearLayout

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

        initializeViews(view)
        initDataModels(view)
        initrecyclerViews(view)
        initclickListener(view)
    }

    private fun initclickListener(view: View) {
        pendingPaymentCard.setOnClickListener {
            pendingorderslayout.visibility =View.GONE
            pending_payment_layout.visibility =View.VISIBLE
            return_order_layout.visibility =View.GONE

        }
        returnOrderCard.setOnClickListener {
            pendingorderslayout.visibility =View.GONE
            pending_payment_layout.visibility =View.GONE
            return_order_layout.visibility =View.VISIBLE
        }
        pendingOrderCard.setOnClickListener {
            pendingorderslayout.visibility =View.VISIBLE
            pending_payment_layout.visibility =View.GONE
            return_order_layout.visibility =View.GONE
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

        returnOrdersRecycler.adapter = PendingOrdersAdapter(requireContext(),pendingOrderData)
        returnOrdersRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initializeViews(view: View) {
        salesmanMoreRecyclerView = view.findViewById(R.id.salesmanMoreRecyclerView)
        pendingPaymentRecycler = view.findViewById(R.id.pendingPaymentRecycler)
        returnOrdersRecycler = view.findViewById(R.id.returnOrdersRecycler)

        pendingPaymentCard = view.findViewById(R.id.pendingPaymentCard)
        returnOrderCard = view.findViewById(R.id.returnOrderCard)
        pendingOrderCard = view.findViewById(R.id.pendingOrderCard)

        return_order_layout = view.findViewById(R.id.return_order_layout)
        pending_payment_layout = view.findViewById(R.id.pending_payment_layout)
        pendingorderslayout = view.findViewById(R.id.pendingorderslayout)

    }

}