package com.example.networkstate

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest

class NetworkConnectionCheckModule(private val context: Context) : ConnectivityManager.NetworkCallback()  {
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val networkRequest: NetworkRequest =  NetworkRequest.Builder()
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .build()

    fun register() {
        connectivityManager.registerNetworkCallback(networkRequest, this)

        if (networkConnectionState() == null) {
            //TODO 네트워크 연결 끊긴 상황에 맞는 코드 작성
            //NetworkDialog(context).unConnectionDialog.show()
        }
    }

    fun unregister() {
        connectivityManager.unregisterNetworkCallback(this)
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        //TODO 네트워크 연결 끊긴 상황에 맞는 코드 작성
        //NetworkDialog(context).unConnectionDialog.show()
    }

    private fun networkConnectionState(): Network? {
        val network: Network? = connectivityManager.activeNetwork
        return network
    }
}