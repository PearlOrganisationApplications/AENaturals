package com.aenatural.aenaturals.distributors.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.distributors.DistributorDashboard
import com.aenatural.aenaturals.salesmans.fragments.ProductsFragment

class AddSalesman : Fragment() {

    private lateinit var distributor_profile_pic: LinearLayout
    private lateinit var distributor_parlorpic: LinearLayout
    private lateinit var distributorFormSubmit: CardView
    companion object {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_salesman, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backPress()
        requireActivity().findViewById<LinearLayout>(R.id.headerdistributor).visibility =View.GONE
        initViews(view)
        initClickListener(view)
        initDataModels(view)
        initRecyclerAdapter(view)
    }

    private fun backPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.DashboardFrameLayout,
                        DistributorHomeFrag()
                    ).commit()
            }
        })
    }
    private fun initRecyclerAdapter(view: View) {

    }

    private fun initDataModels(view: View) {


    }


    private fun initClickListener(view: View) {
       /* var m = 0
        var f = 0
        distributor_profile_pic.setOnClickListener {
            m = 1
            if(f==1)
            {
                f=0
                distributor_parlorpic.setBackgroundResource(R.drawable.loginedittextbg)
            }
            distributor_profile_pic.setBackgroundResource(R.drawable.tapcurvedbackground)
        }
        distributor_parlorpic.setOnClickListener {
            f=1
            if(m==1)
            {
                m=0
                distributor_profile_pic.setBackgroundResource(R.drawable.loginedittextbg)
            }
            distributor_parlorpic.setBackgroundResource(R.drawable.tapcurvedbackground)
        }

        distributorFormSubmit.setOnClickListener {
            startActivity(Intent(requireContext(), DistributorDashboard::class.java))
        }*/

    }

    private fun initViews(view: View) {
        /*distributor_profile_pic = view.findViewById(R.id.distributor_profile_pic)
        distributor_parlorpic = view.findViewById(R.id.distributor_parlorpic)*/
        distributorFormSubmit = view.findViewById(R.id.distributorFormSubmit)
    }

        override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // Handle the back button press here
//            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.DashboardFrameLayout,
                DistributorHomeFrag()
            ).commit()
            Toast.makeText(requireContext(), "Back button pressed", Toast.LENGTH_LONG).show()
        }
    }

}