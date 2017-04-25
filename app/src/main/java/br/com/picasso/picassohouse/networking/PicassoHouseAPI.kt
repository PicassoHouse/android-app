package br.com.picasso.picassohouse.networking

import android.content.Context
import br.com.picasso.picassohouse.R
import br.com.picasso.picassohouse.models.*
import br.com.picasso.picassohouse.utils.AuthHelper
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import rx.Observable

interface PicassoHouseAPI {

    @FormUrlEncoded
    @POST("auth")
    fun login(@Field("username") username: String, @Field("password") password: String): Observable<String>


    @GET("users/current") fun getUserLogged(): Observable<User>

    @GET("rooms") fun getRooms(): Observable<List<Room>>

    @GET("house") fun getHouseInfo(): Observable<House>
    @POST("house/lock") fun setHomeLocked(@Query("lock") isLocked: Boolean): Observable<Void>
    @POST("house/openGarage") fun setGarageOpened(@Query("open") isOpened: Boolean): Observable<Void>
    @POST("house/turnlighton") fun turnLightOn(@Query("on") isOn: Boolean, @Query("room_id") roomId: String): Observable<Void>

    @GET("reports/currentmonthlightinfo") fun getCurrentMonthLightInfo(): Observable<CurrentMonthLightInfo>
    @GET("reports/monthlighthistory") fun getLightHistory(): Observable<List<LightHistoryItem>>


    // --------------------------------------------------------
    // API Factory
    // --------------------------------------------------------
    companion object {
        fun create(context: Context): PicassoHouseAPI {

            //gson
            val gson = GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create()
            //client
            val client = OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        var newRequest = chain.request()
                        val url = newRequest
                                .url()
                                .newBuilder()
                                .addQueryParameter("access_token", AuthHelper.getAccessToken(context))
                                .build()
                        newRequest = newRequest.newBuilder().url(url).build()
                        chain.proceed(newRequest)
                    }.build()

            //service
            return Retrofit.Builder()
                    .client(client)
                    .baseUrl(context.getString(R.string.API_URL))
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()
                    .create(PicassoHouseAPI::class.java)
        }
    }

}