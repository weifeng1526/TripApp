package com.ron.restdemo

//import com.example.tripapp.ui.feature.trip.plan.restful.CreatePlan
import com.example.tripapp.ui.feature.trip.plan.restful.DeletePlanResponse
import com.example.tripapp.ui.feature.trip.plan.restful.Plan
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

//提供給使用者一個ApiService介面，底下自己定義各種RESTFUL抽象方法
interface ApiService {
//    @POST("/sched/add")
//    註記這個request變數為Http Request Body
//    該變數型態就是body的資料物件(Object Convert to Json)
//    此抽象方法的回傳值是Http Response Body的資料物件(From Json Convert to Object)
//    suspend fun CreatePlan(@Body request: CreatePlanRequest): PlanResponse

    @GET("sched/get_one")
    suspend fun GetPlan(@Query("id") id: Int): Plan

    @GET("sched/get_all")
    suspend fun GetPlans(): List<Plan>

    @POST("sched/create")
    suspend fun CreatePlan(@Body request: Plan): Plan

    @PUT("sched/update")
    suspend fun UpdatePlan(@Body request: Plan): Plan

    @DELETE("sched/delete")
    suspend fun DeletePlan(@Query("id") id: Int): DeletePlanResponse

    
}

//單例RetrofitInstance
//api型態為ApiService介面，採用by lazy延遲初始化(呼叫才會實例化)
//這裡是實例化Retrofit並建立BaseURL，使用GSON函式來轉換Json格式
//主要是@簡化了Json<-->Object序列化、反序列化的過程
object RetrofitInstance {
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/javaweb/") // Base URL
            .addConverterFactory(GsonConverterFactory.create()) // GSON for JSON conversion
            .build()
            .create(ApiService::class.java)
    }
}