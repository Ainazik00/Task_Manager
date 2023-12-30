package com.example.task_manager.data.local

import android.content.Context

class Pref(context: Context) {

    private val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun getImage(): String? {
        return pref.getString(IMAGE_URL_KEY, null)
    }

    fun saveUserName(name: String) {
        pref.edit().putString(USERNAME_KEY, name).apply()
    }

    fun getName(): String? {
        return pref.getString(USERNAME_KEY, null)
    }

    fun isBoardingShowed(): Boolean {
        return pref.getBoolean(SHOWED_KEY, false)
    }

    fun onBoardingShowed() {
        pref.edit().putBoolean(SHOWED_KEY, true).apply()
    }

    companion object {
        const val PREF_NAME = "pref.name"
        const val SHOWED_KEY = "showed.key"
        const val USERNAME_KEY = "username.key"
        const val IMAGE_URL_KEY = "image_url.key"
        const val GALLERY_REQUEST_CODE = 24
    }

}