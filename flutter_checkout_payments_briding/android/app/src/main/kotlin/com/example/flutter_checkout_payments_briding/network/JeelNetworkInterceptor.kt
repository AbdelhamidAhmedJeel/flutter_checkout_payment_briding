package com.suecal.suecalcrmnewkmm.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities

import android.os.Build.*
import com.example.flutter_checkout_payments_briding.utils.NetworkExceptions

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject


class JeelNetworkInterceptor @Inject constructor(private  var ctx: Context):Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {

        if ( isNetworkConnected()){
            try {
                return chain.proceed(chain.request())
            }catch (e: IOException){
                throw NetworkExceptions(e.message!!)

            }

        }else{
            throw NetworkExceptions("No Internet Connection")
        }


    }



    private fun isNetworkConnected(): Boolean {
        val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (VERSION.SDK_INT < 23) {
            val ni = cm.activeNetworkInfo
            if (ni != null) {
                return ni.isConnected && (ni.type == ConnectivityManager.TYPE_WIFI || ni.type == ConnectivityManager.TYPE_MOBILE)
            }
        } else {
            val n: Network? = cm.activeNetwork
            if (n != null) {
                val nc = cm.getNetworkCapabilities(n)
                return nc!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI)
            }
        }
        return false
    }

}