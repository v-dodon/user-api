package com.developmentaid.task.service

import com.developmentaid.task.endpoints.UserEndpoint
import com.developmentaid.task.util.ResponseUtil
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.net.HttpURLConnection


class UserServiceTest {

    private var mockWebServer = MockWebServer()
    private lateinit var usersEndpoint: UserEndpoint
    private lateinit var userService: UserService

    @Before
    fun setup() {
        mockWebServer.start()
        usersEndpoint = Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(UserEndpoint::class.java)
        userService = UserService(usersEndpoint)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `return 2 valid users`() = runBlocking {
        val response = MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(ResponseUtil.readResponseFromFile("users.json"))
        mockWebServer.enqueue(response)
        val location = listOf<String>("Romania", "Moldova")
        val users = userService.getUsers("reputation", "223", "testFilter", location, 1)
        Assert.assertEquals(2, users.size)
    }


    @Test
    fun `return empty list`() = runBlocking {
        val response = MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(ResponseUtil.readResponseFromFile("users.json"))
        mockWebServer.enqueue(response)
        val location = listOf<String>("Germany")
        val users = userService.getUsers("reputation", "223", "testFilter", location, 1)
        Assert.assertTrue(users.isEmpty())
    }
}