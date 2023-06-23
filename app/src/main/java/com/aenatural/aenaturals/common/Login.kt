package com.aenatural.aenaturals.common

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.text.Html
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.distributors.DistributorDashboard
import com.aenatural.aenaturals.customers.CustomerDashboard
import com.aenatural.aenaturals.salesmans.SalesmanDashboard
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.myspalon.MSRegisterActivity


class Login : BaseClass() {

    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var tv_login: TextView
    lateinit var textView: TextView
    lateinit var cardView2: CardView
    lateinit var loginll: LinearLayout
    lateinit var signupforparlorll: LinearLayout

    lateinit var session: Session
    val rect = Rect()
    private var keyboardVisible = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        session = Session(this)
        getMidGreentheme()
        setLayoutXml()
        initializeViews()
        initializeClickListners()


    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_login)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    override fun initializeViews() {
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        cardView2 = findViewById(R.id.cardView2)
        loginll = findViewById(R.id.loginll)
        signupforparlorll = findViewById(R.id.signupforparlorll)

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

            if ((emailEditText.text.toString()
                    .equals("salesman") && passwordEditText.text.toString()
                    .equals("123")) || (emailEditText.text.toString()
                    .equals("salesman ") && passwordEditText.text.toString().equals("123"))
            ) {
                session.setLogin(emailEditText.text.toString(), 2)
                startActivity(Intent(this, SalesmanDashboard::class.java))
            } else if (emailEditText.text.toString()
                    .equals("distributor") && passwordEditText.text.toString().equals("123")
            ) {
                session.setLogin(emailEditText.text.toString(), 1)
                startActivity(Intent(this, DistributorDashboard::class.java))
            } else if (emailEditText.text.toString()
                    .equals("customer") && passwordEditText.text.toString().equals("123")
            ) {
                session.setLogin(emailEditText.text.toString(), 3)
                startActivity(Intent(this, CustomerDashboard::class.java))
            } else {
                Toast.makeText(applicationContext,
                    "Email or Password is invalid",
                    Toast.LENGTH_SHORT).show()
            }

        }

        signupforparlorll.setOnClickListener {
            startActivity(Intent(this,MSRegisterActivity::class.java))
        }
//        setupKeyboardVisibilityListener()

    }

    override fun initializeInputs() {

    }

    override fun initializeLabels() {

    }

    fun setupKeyboardVisibilityListener(){

        cardView2.viewTreeObserver.addOnGlobalLayoutListener{

            cardView2.getWindowVisibleDisplayFrame(rect)
            val screenHeight = cardView2.rootView.height

            // Calculate the height difference between the visible window rect and the screen height
            val heightDifference = screenHeight - rect.bottom

            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            val isKeyboardVisible = inputMethodManager.isAcceptingText

            // If the height difference is positive and larger than a threshold (e.g., 200 pixels),
            // consider the keyboard as visible

//            val keyboardThreshold = 200
//            val isKeyboardVisible = heightDifference > keyboardThreshold
//            val isKeyboardVisible = heightDifference > screenHeight * 0.15 // Adjust the threshold as needed

            if (isKeyboardVisible != keyboardVisible) {
                keyboardVisible = isKeyboardVisible
                if (keyboardVisible) {
                    // Keyboard is visible, move your views up here
                    moveViewsUp()
                } else {
                    // Keyboard is hidden, move your views back to their original position here
                    moveViewsDown()
                }
            }
        }
    }

    private fun moveViewsUp() {
        // Calculate the desired translation for your views
        val translationY = -200 // Example translation value

        // Apply the translation to your views
        cardView2.animate().translationY(translationY.toFloat()).start()
//        loginll.animate().translationY(translationY.toFloat()).start()
        // Add more views as needed
    }

    private fun moveViewsDown() {
        val translationY = 200f
        // Reset the translation of your views back to their original position
        cardView2.animate().translationY(translationY).start()
//        loginll.animate().translationY(0f).start()
        // Add more views as needed
    }
}