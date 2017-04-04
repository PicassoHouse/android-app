package br.com.picasso.picassohouse.networking

import android.content.Context
import br.com.picasso.picassohouse.R
import br.com.picasso.picassohouse.models.Room
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PicassoHouseAPI {

    @GET("rooms")
    fun getRooms(): Observable<List<Room>>


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
                                .addQueryParameter("access_token", "Bearer asdf123") //TODO: access_token
                                .build()
                        newRequest = newRequest.newBuilder().url(url).build()
                        chain.proceed(newRequest)
                    }.build()

            //service
            val retrofit = Retrofit.Builder()
                    .baseUrl(context.getString(R.string.API_URL))
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build()

            return retrofit.create(PicassoHouseAPI::class.java)
        }
    }

}