package org.test.testutest.ui

import android.arch.lifecycle.ViewModel
import io.reactivex.Single
import org.test.testutest.data.db.AppDataBase
import org.test.testutest.data.db.dao.UserDao
import org.test.testutest.data.models.Credentials
import org.test.testutest.data.models.User
import org.test.testutest.data.net.AppApi
import org.test.testutest.data.net.api.UserApi
import org.test.testutest.data.preferences.UserSession
import org.test.testutest.util.applySchedulers
import org.test.testutest.util.validateResponse

class MainViewModel : ViewModel() {

    private val dao: UserDao = AppDataBase.db.userDao()
    private val api: UserApi = AppApi.buildApi()

    fun login(username: String, password: String): Single<String> =
            api.login(Credentials(username, password))
                    .validateResponse()
                    .map {
                        UserSession.token = it.token
                        UserSession.id = it.id
                        it.token
                    }
                    .applySchedulers()


    fun getUser(): Single<User> =
            dao.getUser()
                    .switchIfEmpty(getAndSaveUser())
                    .applySchedulers()


    private fun getAndSaveUser(): Single<User> =
            api.getUser(UserSession.token!!, UserSession.id!!)
                    .validateResponse()
                    .flatMap { user ->
                        Single.fromCallable { dao.insert(user) }
                                .map { user }
                    }

}