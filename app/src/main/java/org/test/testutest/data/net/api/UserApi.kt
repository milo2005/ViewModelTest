package org.test.testutest.data.net.api

import io.reactivex.Single
import org.test.testutest.data.models.Credentials
import org.test.testutest.data.models.User
import retrofit2.http.*

interface UserApi {

    @POST("login")
    fun login(@Body credential: Credentials): Single<ResponseBody<RspLogin>>

    @GET("users/{id}")
    fun getUser(@Header("Authorization") token: String, @Path("id") id: String): Single<ResponseBody<User>>

}

class RspLogin(val id:String, val token:String)

class ResponseBody<out T>(val success: Boolean, val data: T, val error: String)