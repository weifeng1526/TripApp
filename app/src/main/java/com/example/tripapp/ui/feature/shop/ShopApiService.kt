package com.example.tripapp.ui.feature.shop

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import kotlin.jvm.java

// 測試網站說明：https://reqres.in/
interface ShopApiService {
    /** 取得所有商品資訊 */
    @GET("rest/product/all")
//    suspend fun fetchProducts(): Response<String>
    suspend fun fetchProducts(): List<Product>

    /** 新增訂單至資料庫 */
    @POST("rest/order")
    suspend fun addOrder(@Body orderRequest: OrderRequest): Response<OrderResponse>

    object RetrofitInstance {
        val api: ShopApiService by lazy {
            Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/TripAppEnd/") // Base URL
                .addConverterFactory(GsonConverterFactory.create()) // GSON for JSON conversion
                .build()
                .create(ShopApiService::class.java)
        }
    }
}