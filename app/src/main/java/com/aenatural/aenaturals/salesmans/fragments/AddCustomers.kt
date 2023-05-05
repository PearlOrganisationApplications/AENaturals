package com.aenatural.aenaturals.salesmans.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.salesmans.Adapters.RequestProductsAdapter
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.salesmans.SalesmanDashboard


class AddCustomers : Fragment() {

/*    lateinit var recyclerViewRequestProducts:RecyclerView
    lateinit var retailerList:ArrayList<RetailerDataModel>
    lateinit var requestproductscheckout:CardView*/

    lateinit var profilepic: LinearLayout
    lateinit var parlor_pic: LinearLayout
    lateinit var customerFormSubmit:CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_request_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initClickListener(view)
        initDataModels(view)
        initRecyclerAdapter(view)
    }

    private fun initClickListener(view: View) {
        var m=0
        var f=0
        profilepic.setOnClickListener {
            m=1
            if(f==1)
            {
                f=0
                parlor_pic.setBackgroundResource(R.drawable.loginedittextbg)
            }
            profilepic.setBackgroundResource(R.drawable.tapcurvedbackground)
        }

        parlor_pic.setOnClickListener {
            f=1
            if(m==1)
            {
                m=0
                profilepic.setBackgroundResource(R.drawable.loginedittextbg)
            }
            parlor_pic.setBackgroundResource(R.drawable.tapcurvedbackground)
        }

        customerFormSubmit.setOnClickListener {
            startActivity(Intent(requireContext(), SalesmanDashboard::class.java))
        }


    }

    private fun initRecyclerAdapter(view: View) {
       /* recyclerViewRequestProducts.adapter = RequestProductsAdapter(retailerList,requireContext())
        recyclerViewRequestProducts.layoutManager = LinearLayoutManager(requireContext())*/

    }

    private fun initDataModels(view: View) {
       /* retailerList= ArrayList()
        for(i in 0..5)
            retailerList.add(RetailerDataModel("Rajesh K","rajeshisamazing@gmail.com","RR Nagar Banglore","+9182384898"))*/

    }

    private fun initViews(view: View) {
/*        recyclerViewRequestProducts = view.findViewById(R.id.recyclerViewRequestProducts)
        requestproductscheckout = view.findViewById(R.id.requestproductscheckout)*/

        profilepic = view.findViewById(R.id.profile_pic)
        parlor_pic = view.findViewById(R.id.parlorpic)
        customerFormSubmit = view.findViewById(R.id.customerFormSubmit)

    }

}