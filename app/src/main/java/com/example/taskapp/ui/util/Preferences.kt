package com.example.taskapp.ui.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.media.Image

class Preferences(private val context: Context) {

    val sharedPref: SharedPreferences = context.getSharedPreferences("preferences", MODE_PRIVATE)


    fun isBoardingShowed(): Boolean {
        return sharedPref.getBoolean("board", false)

    }

    fun setBoardingShowed(isShow: Boolean) {
        sharedPref.edit().putBoolean("board", isShow).apply()


    }

    fun getName(): String? {
        return sharedPref.getString("bord", "")
    }

    fun saveName(name: String) {
        sharedPref.edit().putString("bord", name).apply()
    }

    fun getImageUri ():  String? {
        return sharedPref.getString("img", " ").toString()

    }
    fun saveImageUri(imgUri: String) {

       return sharedPref.edit().putString("img", imgUri).apply()
    }

}