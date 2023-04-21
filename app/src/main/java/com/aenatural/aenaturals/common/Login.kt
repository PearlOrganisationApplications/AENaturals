package com.aenatural.aenaturals.common

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.distributors.DistributorDashboard
import com.aenatural.aenaturals.customers.CustomerDashboard
import com.aenatural.aenaturals.salesmans.SalesmanDashboard
import com.aenatural.aenaturals.R


class Login : BaseClass() {

    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var tv_login: TextView
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMidGreentheme()
        setLayoutXml()


        initializeViews()
        initializeClickListners()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_login)
    }

    override fun initializeViews() {
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)

        /*   salesmanButton = findViewById(R.id.salesmanButton)
           retailerButton = findViewById(R.id.retailerButton)
           distributorButton = findViewById(R.id.distributorButton)*/
        tv_login = findViewById(R.id.tv_login)
        textView = findViewById(R.id.textView)


        val text = "<font color=#000000>By clicking \"Login\" above, you agree to our   </font> " +
                "<font color=#0C805F>terms &amp; conditiions</font><font color=#000000> and </font>" +
                "<font color=#0C805F>privacy policy.</font>"
        textView.setText(Html.fromHtml(text))
    }

    override fun initializeClickListners() {
        tv_login.setOnClickListener {
            buttonEffect(tv_login)

            if((emailEditText.text.toString().equals("salesman") && passwordEditText.text.toString().equals("123"))||(emailEditText.text.toString().equals("salesman ") && passwordEditText.text.toString().equals("123"))){
                startActivity(Intent(this, SalesmanDashboard::class.java))
            }else if(emailEditText.text.toString().equals("distributor") && passwordEditText.text.toString().equals("123")){
                startActivity(Intent(this, DistributorDashboard::class.java))
            }else if(emailEditText.text.toString().equals("retailer") && passwordEditText.text.toString().equals("123")){
                startActivity(Intent(this, CustomerDashboard::class.java))
            }else{
                Toast.makeText(applicationContext,"Email or Password is invalid",Toast.LENGTH_SHORT).show()
            }


        }
    }

    override fun initializeInputs() {

    }

    override fun initializeLabels() {

    }


}