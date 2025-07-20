package com.example.flutter_checkout_payments_briding.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities

import android.os.Build.*
import android.util.Log
import com.example.flutter_checkout_payments_briding.network.exceptions.NetworkExceptions

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject


class JeelNetworkInterceptor @Inject constructor(private  var context: Context):Interceptor{
 override fun intercept(chain: Interceptor.Chain): Response {
    if (isNetworkConnected()) {
        try {
            val request = chain.request()

            val response = chain.proceed(request)
            

            return response
        } catch (e: IOException) {
             throw NetworkExceptions(e.message ?: "Network error occurred")
        } catch (e: Exception) {
              throw e
        }
    } else {

        throw NetworkExceptions("No Internet Connection")
    }
}


    private fun isNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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