package com.aenatural.aenaturals.salesmans.fragments

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.salesmans.SalesmanDashboard


class AddCustomers : Fragment() {

/*    lateinit var recyclerViewRequestProducts:RecyclerView
    lateinit var retailerList:ArrayList<RetailerDataModel>
    lateinit var requestproductscheckout:CardView*/

    lateinit var profilepic: LinearLayout
    lateinit var parlor_pic: LinearLayout
    lateinit var customerFormSubmit: CardView
    lateinit var sm_et1_firstname: EditText
    lateinit var sm_et2_lastname: EditText
    lateinit var sm_et3_mobilenumber: EditText
    lateinit var sm_et4_email: EditText
    lateinit var sm_et5_alernatenumber: EditText
    lateinit var sm_et6_pincode: EditText
    lateinit var sm_et7_tag: EditText
    lateinit var sm_et8_address: EditText
    lateinit var sm_et9_city: EditText
    lateinit var sm_et10_state: EditText
    lateinit var sm_et11_smID: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_request_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backPress()
        requireActivity().findViewById<LinearLayout>(R.id.include2).visibility = View.GONE
        initViews(view)
        initClickListener(view)
        initDataModels(view)
        initRecyclerAdapter(view)
    }

    private fun initClickListener(view: View) {
        var m = 0
        var f = 0
        profilepic.setOnClickListener {
            m = 1
            if (f == 1) {
                f = 0
                parlor_pic.setBackgroundResource(R.drawable.loginedittextbg)
            }
            profilepic.setBackgroundResource(R.drawable.tapcurvedbackground)
        }

        parlor_pic.setOnClickListener {
            f = 1
            if (m == 1) {
                m = 0
                profilepic.setBackgroundResource(R.drawable.loginedittextbg)
            }
            parlor_pic.setBackgroundResource(R.drawable.tapcurvedbackground)
        }

        customerFormSubmit.setOnClickListener {
            startActivity(Intent(requireContext(), SalesmanDashboard::class.java))
        }


    }

    private fun initViews(view: View) {
        profilepic = view.findViewById(R.id.profile_pic)
        parlor_pic = view.findViewById(R.id.parlorpic)
        customerFormSubmit = view.findViewById(R.id.customerFormSubmit)

        sm_et1_firstname = view.findViewById(R.id.sm_et1_firstname)
        sm_et2_lastname = view.findViewById(R.id.sm_et2_lastname)
        sm_et3_mobilenumber = view.findViewById(R.id.sm_et3_mobilenumber)
        sm_et4_email = view.findViewById(R.id.sm_et4_email)
        sm_et5_alernatenumber = view.findViewById(R.id.sm_et5_alernatenumber)
        sm_et6_pincode = view.findViewById(R.id.sm_et6_pincode)
        sm_et7_tag = view.findViewById(R.id.sm_et7_tag)
        sm_et8_address = view.findViewById(R.id.sm_et8_address)
        sm_et9_city = view.findViewById(R.id.sm_et9_city)
        sm_et10_state = view.findViewById(R.id.sm_et10_state)
        sm_et11_smID = view.findViewById(R.id.sm_et11_smID)

        edittextFocusHandlder(view,sm_et1_firstname,sm_et2_lastname)
        edittextFocusHandlder(view,sm_et2_lastname,sm_et3_mobilenumber)
        edittextFocusHandlder(view,sm_et3_mobilenumber,sm_et4_email)
        edittextFocusHandlder(view,sm_et4_email,sm_et5_alernatenumber)
        edittextFocusHandlder(view,sm_et5_alernatenumber,sm_et6_pincode)
        edittextFocusHandlder(view,sm_et6_pincode,sm_et7_tag)
        edittextFocusHandlder(view,sm_et7_tag,sm_et8_address)
        edittextFocusHandlder(view,sm_et8_address,sm_et9_city)
        edittextFocusHandlder(view,sm_et9_city,sm_et10_state)
        edittextFocusHandlder(view,sm_et10_state,sm_et11_smID)
    }

    private fun backPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.salesDashboardFrameLayout,
                            HomeFragment()
                        ).commit()
                }
            })
    }

     private fun edittextFocusHandlder(view: View,e1:EditText,e2:EditText) {

         e1.setOnEditorActionListener { _, actionId, _ ->
             if (actionId == EditorInfo.IME_ACTION_NEXT) {
                 e2.requestFocus()
                 return@setOnEditorActionListener true
             }
             false
         }
     }

    private fun initRecyclerAdapter(view: View) {
        /* recyclerViewRequestProducts.adapter = RequestProductsAdapter(retailerList,requireContext())
         recyclerViewRequestProducts.layoutManager = LinearLayoutManager(requireContext())*/

    }

    private fun initDataModels(view: View) {
    }

}