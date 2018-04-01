package org.test.testutest

import android.app.Application
import okhttp3.HttpUrl
import org.test.testutest.data.db.AppDataBase
import org.test.testutest.data.net.AppApi
import org.test.testutest.data.preferences.UserSession

class App:Application(){

    override fun onCreate() {
        super.onCreate()
        AppDataBase.init(this)
        AppApi.init(HttpUrl.parse(getString(R.string.url_base))!!)
        UserSession.init(this)
    }
}