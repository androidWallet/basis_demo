package com.app.basis.ui.main

import android.content.Context
import android.widget.TextView
import com.app.basis.R
import com.app.basis.data.model.Datum
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.annotations.View


@Layout(R.layout.card_view)
class DataCard(
    private val mContext: Context,
    private val mDatum: Datum,
    private val mSwipeView: SwipePlaceHolderView
) {

    @View(R.id.contentTxt)
    lateinit var contentTxt: TextView

    @Resolve
    fun onResolved() {
        contentTxt.text = mDatum.text
    }

}