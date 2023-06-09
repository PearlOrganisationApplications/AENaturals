package com.aenatural.aenaturals.customers

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.SliderModel
import com.aenatural.aenaturals.customers.adapters.SliderAdapter
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.common.Login
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.common.Models.SellerDataModel
import com.aenatural.aenaturals.customers.fragments.Cust_performance_frag
import com.aenatural.aenaturals.customers.fragments.CustomerHomeFrag
import com.aenatural.aenaturals.customers.fragments.CustomerOrderFrag
import com.aenatural.aenaturals.customers.fragments.CustomerOrder_HistoryFrag
import com.aenatural.aenaturals.salesmans.fragments.AddCustomers
import com.aenatural.aenaturals.salesmans.fragments.Cart
import com.aenatural.aenaturals.salesmans.fragments.HomeFragment
import com.aenatural.aenaturals.salesmans.fragments.ProductsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel
import java.util.*
import kotlin.collections.ArrayList


class CustomerDashboard : BaseClass() {


    lateinit var custDashboardFrameLayout: FrameLayout
    lateinit var custBottomNav: BottomNavigationView
    lateinit var custProfileIcon: ImageView
    lateinit var customer_cart_icon:LinearLayout
    lateinit var alertDialog:AlertDialog.Builder
    var pref: Session? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
        initializeLabels()
        initializeInputs()
        bottomNavigationControl()
    }

    override fun setLayoutXml() {
        getMidGreentheme()
        setContentView(R.layout.activity_customer_dashboard)
        supportFragmentManager.beginTransaction().replace(R.id.cust_home_frame,
            CustomerHomeFrag()).commit()
    }

    override fun initializeViews() {
        custDashboardFrameLayout = findViewById(R.id.cust_home_frame)
        custBottomNav = findViewById(R.id.custBottomNav)
        custProfileIcon = findViewById(R.id.cust_profileicon)
        customer_cart_icon = findViewById(R.id.customer_cart_icon)

        pref= Session(this);
        alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Alert")
        alertDialog.setMessage("Do you want to Logout?")
        alertDialog.setPositiveButton("Yes"){dialogInterface,_ ->
            run{
                pref?.clearSession();
                startActivity(Intent(this, Login::class.java))
                dialogInterface.dismiss()
            }
        }
        alertDialog.setNegativeButton("No"){dialogInterface,_ ->
            run{
                //startActivity(Intent(this, SalesmanDashboard::class.java))
                dialogInterface.dismiss()
            }
        }
    }

    override fun initializeClickListners() {
        custProfileIcon.setOnClickListener {
          //  startActivity(Intent(this, CustomerProfileActivity::class.java))
            alertDialog.show()
        }
        customer_cart_icon.setOnClickListener {
            startActivity(Intent(this,CustomerCartActivity::class.java))
        }
    }

    override fun initializeInputs() {

    }

    override fun initializeLabels() {

    }



    private fun bottomNavigationControl() {
        custBottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.cust_Home->{
                    supportFragmentManager.beginTransaction().replace(R.id.cust_home_frame,CustomerHomeFrag()).commit()
                }
                R.id.cust_pending->{
                    supportFragmentManager.beginTransaction().replace(R.id.cust_home_frame,
                        CustomerOrder_HistoryFrag()).commit()
                }
                R.id.cust_Profit->{
                    supportFragmentManager.beginTransaction().replace(R.id.cust_home_frame,
                        Cust_performance_frag()).commit()
                }
                R.id.custOrders->{
                    supportFragmentManager.beginTransaction().replace(R.id.cust_home_frame,
                        CustomerOrderFrag()).commit()
                }
            }
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}