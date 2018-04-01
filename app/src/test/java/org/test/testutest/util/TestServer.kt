package org.test.testutest.util

import com.google.gson.Gson

import okhttp3.HttpUrl
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.test.testutest.data.models.Credentials
import org.test.testutest.data.models.User
import org.test.testutest.data.net.api.ResponseBody
import org.test.testutest.data.net.api.RspLogin

object TestServer {

    val httpUrl: HttpUrl by lazy { prepareServer() }
    private val gson: Gson = Gson()


    private fun prepareServer(): HttpUrl {
        val server: MockWebServer = MockWebServer()

        val dispatcher: Dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse = when (request.path) {
                "/login" -> {
                    val body = gson.fromJson(request.body.readUtf8(), Credentials::class.java)
                    if (body.username == "testUser" && body.password == "12345") {
                        MockResponse().setResponseCode(200)
                                .setBody(gson.toJson(ResponseBody(true, RspLogin("2", "abc"))))
                    } else MockResponse().setResponseCode(200)
                            .setBody(gson.toJson(ResponseBody<RspLogin>(false, error = "Usuario o password incorrectos")))
                }
                "/users/2" -> {
                    val user = User("2", "testUSer", "testRole")
                    MockResponse().setResponseCode(200)
                            .setBody(gson.toJson(ResponseBody(true, user)))
                }
                else -> MockResponse().setResponseCode(404)
            }
        }

        server.setDispatcher(dispatcher)

        return server.url("/")
    }

}