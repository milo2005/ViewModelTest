package org.test.testutest.data.preferences

import android.content.Context
import android.content.SharedPreferences

object UserSesion{

    private lateinit var preferences:SharedPreferences

    var token:String?
        set(value) = preferences.edit().putString("token", value).apply()
        get() = preferences.getString("token",null)

    var id:String?
        set(value) = preferences.edit().putString("id", value).apply()
        get() = preferences.getString("id",null)

    fun init(context: Context){
        preferences = context.getSharedPreferences("test.preferences", Context.MODE_PRIVATE)
    }

}