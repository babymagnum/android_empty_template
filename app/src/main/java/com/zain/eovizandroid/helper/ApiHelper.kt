package com.zain.eovizandroid.helper

import com.zain.eovizandroid.service.ApiService
import com.zain.eovizandroid.model.Post
import com.zain.eovizandroid.model.ResponseResult
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiHelper {
    private val okHttpClient = OkHttpClient().newBuilder().addInterceptor(getInterceptor()).build()
    private lateinit var apiService: ApiService

    init {
        makeService()
    }

    private fun makeService() {
        val retrofit: Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        this.apiService = retrofit.create(ApiService::class.java)
    }

    private fun getInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): ResponseResult<T> {

        return try {
            val myResp = call.invoke()

            if (myResp.isSuccessful) {
                ResponseResult.Success(myResp.body()!!)
            } else {

                /*
                handle standard error codes
                if (myResp.code() == 403){
                    Log.i("responseCode","Authentication failed")
                }
                .
                .
                .
                 */

                ResponseResult.Error(myResp.errorBody()?.string() ?: "Something goes wrong")
            }

        } catch (e: Exception) {
            ResponseResult.Error(e.message ?: "Internet error runs")
        }
    }

    suspend fun getTodoRequest(id: Int): ResponseResult<Post> {
        return safeApiCall(call = { apiService.getTodo(id) })
    }

}