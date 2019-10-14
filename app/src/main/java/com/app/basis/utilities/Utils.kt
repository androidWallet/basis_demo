package com.app.basis.utilities

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import com.app.basis.data.model.Datum
import com.google.gson.GsonBuilder
import org.json.JSONObject
import java.io.InputStream


object Utils {

    fun loadData(context: Context): List<Datum>? {
        try {
            val builder = GsonBuilder()

            val gson = builder.create()

            val jsonObject = JSONObject(loadJSONFromAsset(context, "card_data.json"))

            val array = jsonObject.getJSONArray("data")
            val data = ArrayList<Datum>()

            for (i in 0 until array.length()) {
                val obj = gson.fromJson(array.getString(i), Datum::class.java)
                data.add(obj)
            }
            return data
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }

    private fun loadJSONFromAsset(context: Context, jsonFileName: String): String? {
        val json: String?
        try {
            val inputStream: InputStream = context.assets.open(jsonFileName)
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    fun getScreenHeight(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        return dm.heightPixels
    }

    fun getScreenWidth(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        return dm.widthPixels
    }


}