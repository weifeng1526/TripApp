package com.example.tripapp.ui.feature.shop

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import kotlin.jvm.java

// 測試網站說明：https://reqres.in/
interface ShopApiService {
    /** 取得所有商品資訊 */
    @GET("rest/product/all")
//    suspend fun fetchProducts(): Response<String>
    suspend fun fetchProducts(): List<Product>

    /** 取得所有訂單資訊 */
    @GET("rest/order/all")
//    suspend fun fetchOrders(): Response<String>
    suspend fun fetchOrders(): List<Order>

    /** 根據會員編號取得訂單資訊 */
    @GET("rest/order/member/{memberId}")
    suspend fun fetchOrdersByMemberId(@Path("memberId") memberId: Int): List<Order>

    /** 新增訂單至資料庫 */
    @POST("rest/order")
    suspend fun addOrder(@Body orderRequest: OrderRequest): Response<OrderResponse>

    /** 刪除指定訂單 */
    @DELETE("rest/order/{ordno}")
    suspend fun deleteOrder(@Path("ordno") ordNo: Int): Response<Unit>  // 回傳空的 Response

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

