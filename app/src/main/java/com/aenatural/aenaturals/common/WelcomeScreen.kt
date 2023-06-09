package com.aenatural.aenaturals.common

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.aenatural.aenaturals.R

class WelcomeScreen : AppCompatActivity() {
    private  lateinit var onboardingItemAdapter: OnboardingItemAdapter

    private lateinit var indicatorContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)
        window.statusBarColor = ContextCompat.getColor(this, R.color.darkgreen)
        setOnboardingItem()
        setUpIndicator()
        setCurrenIndicator(0)
    }

    private fun setOnboardingItem(){

        onboardingItemAdapter = OnboardingItemAdapter(
            listOf(
                OnboardingItem(
                    onboardingImage = R.drawable.ic_logo_aen,
                    title = "About Application",
                    discription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua"
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.ic_logo_aen,
                    title = "About Application",
                    discription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua"
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.ic_logo_aen,
                    title = "About Application",
                    discription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua"
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.ic_logo_aen,
                    title = "About Application",
                    discription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua"
                )
            )
        )

        val onboardingViewPager = findViewById<ViewPager2>(R.id.onboardingViewPager)
        onboardingViewPager.adapter = onboardingItemAdapter

        onboardingViewPager.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrenIndicator(position)
            }
        })
        (onboardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER

        findViewById<ImageView>(R.id.image_next).setOnClickListener {
            if (onboardingViewPager.currentItem+1 < onboardingItemAdapter.itemCount){
                onboardingViewPager.currentItem +=1
            }else{
                navigateToDashboarde()
            }
        }

        findViewById<TextView>(R.id.textSkip).setOnClickListener {
            navigateToDashboarde()
        }

        findViewById<TextView>(R.id.start).setOnClickListener {
            navigateToDashboarde()
        }
    }

    private fun navigateToDashboarde() {
        startActivity(Intent(applicationContext, Login::class.java))
        finish()
    }


    private fun setUpIndicator(){
        indicatorContainer =findViewById(R.id.indicatorsContainer)

        val  indicator = arrayOfNulls<ImageView>(onboardingItemAdapter.itemCount)
        val layoutParam: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT)
        layoutParam.setMargins(8, 0, 8, 0)
        for (i in  indicator.indices){
            indicator[i] = ImageView(applicationContext)
            indicator[i]?.let {
                it.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.indicator_inactive_background
                )
                )

                it.layoutParams =layoutParam
                indicatorContainer.addView(it)
            }
        }
    }
    private fun setCurrenIndicator(position: Int){
        val chidCound = indicatorContainer.childCount
        for(i in 0 until chidCound){
            val imageView = indicatorContainer.getChildAt(i) as ImageView
            if(i == position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }
}