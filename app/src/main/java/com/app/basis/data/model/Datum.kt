package com.app.basis.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Datum {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("text")
    @Expose
    var text: String? = null

}