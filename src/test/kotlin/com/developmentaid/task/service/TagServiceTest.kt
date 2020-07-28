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

class TagServiceTest {

    private var mockWebServer = MockWebServer()
    private lateinit var usersEndpoint: UserEndpoint
    private lateinit var tagService: TagService

    @Before
    fun setup() {
        mockWebServer.start()
        usersEndpoint = Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(UserEndpoint::class.java)
        tagService = TagService(usersEndpoint)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `return only C#, java, docker and net tags`() = runBlocking {
        val response = MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(ResponseUtil.readResponseFromFile("tags.json"))
        mockWebServer.enqueue(response)
        val userIds = listOf(1, 2, 3)
        val tags = listOf("C#", "java", "docker", ".net")
        val userTags = tagService.getUserTags(userIds, tags, "testFilter")
        Assert.assertEquals(4, userTags.size)
    }

    @Test
    fun `return empty list`() = runBlocking {
        val response = MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(ResponseUtil.readResponseFromFile("tags.json"))
        mockWebServer.enqueue(response)

        val userIds = listOf(152)
        val tags = listOf("invalidTag")
        val userTags = tagService.getUserTags(userIds, tags, "testFilter")

        Assert.assertTrue(userTags.isEmpty())
    }
}