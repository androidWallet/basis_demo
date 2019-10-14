package com.app.basis.ui.main

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.app.basis.R
import com.app.basis.data.model.Datum
import com.app.basis.utilities.Utils
import com.mindorks.placeholderview.SwipeDecor
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.SwipeViewBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mData: List<Datum>? = null

    private var mCurrentPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load Card Data from the Assets Folder
        mData = Utils.loadData(this@MainActivity)

        setup()
        setupCardContainerView()

        //Load Card Inside Container
        loadCards()

    }

    /**
     * Set up view
     */
    private fun setup() {
        progressBar.max = mData!!.size

        //Next button click listener
        btnNext.setOnClickListener {
            swipeView.doSwipe(true)
        }

        // Previous button click listener
        btnPrev.setOnClickListener {

            if (mCurrentPosition >= 0 && mCurrentPosition < mData!!.size) {
                swipeView.removeAllViews()
                updateProgressBar(mCurrentPosition)
                for (i in mCurrentPosition until mData!!.size) {
                    swipeView.addView(DataCard(this@MainActivity, mData!![i], swipeView))
                }
                mCurrentPosition--
            }

        }

    }

    /**
     * Set up card container view
     */
    private fun setupCardContainerView() {

        val screenWidth = Utils.getScreenWidth(this)
        val screenHeight = Utils.getScreenHeight(this)

        swipeView.getBuilder<SwipePlaceHolderView, SwipeViewBuilder<SwipePlaceHolderView>>()
            .setDisplayViewCount(3)
            .setHeightSwipeDistFactor(10f)
            .setWidthSwipeDistFactor(5f)
            .setSwipeDecor(
                SwipeDecor()
                    .setViewWidth((0.90 * screenWidth).toInt())
                    .setViewHeight((0.75 * screenHeight).toInt())
                    .setPaddingTop(20)
                    .setSwipeRotationAngle(10)
                    .setRelativeScale(0.01f)
            )

        swipeView.addItemRemoveListener { count ->
            mCurrentPosition = (mData!!.size - count) - 1
            updateProgressBar(mData!!.size - count)
            if (count == 0) {
                Handler().postDelayed({
                    resetCurrentPosition()
                    resetProgressBar()
                    loadCards()
                }, 1000)
            }
        }

    }

    /**
     * Load card to the View
     */
    private fun loadCards() {
        mData!!.forEach {
            swipeView.addView(DataCard(this@MainActivity, it, swipeView))
        }
    }

    /**
     * Update Progress Bar
     */
    private fun updateProgressBar(progressValue: Int) {
        progressBar.progress = progressValue
    }

    /**
     * Reset Progress Bar
     */
    private fun resetProgressBar() {
        progressBar.progress = 0
    }

    /**
     * Reset Current Position
     */
    private fun resetCurrentPosition() {
        mCurrentPosition = -1
    }

}
