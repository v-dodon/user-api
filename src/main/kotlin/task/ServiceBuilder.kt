package task

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object ServiceBuilder {

    private val retrofit = Retrofit.Builder().baseUrl("https://api.stackexchange.com/2.2/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build()

    fun<T> createService(service: Class<T>) : T {
        return retrofit.create(service)
    }
}