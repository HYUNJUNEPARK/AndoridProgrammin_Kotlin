package com.example.networkstate

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.widget.Toast

class NetworkConnectionCallback(private val context: Context) : ConnectivityManager.NetworkCallback() {
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val networkRequest: NetworkRequest = NetworkRequest.Builder()
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR) //Mobile Network
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI) //WIFI Network
        .build()
    var networkType : String? = "-"
    var networkState: String? = "연결 해제"

    //NetworkCallback 등록
    fun register() {
        connectivityManager.registerNetworkCallback(networkRequest, this)
    }

    //NetworkCallback 해제
    fun unregister() {
        connectivityManager.unregisterNetworkCallback(this)
    }

    //콜백이 등록되거나 네트워크가 연결되었을 때 실행되는 메소드
    override fun onAvailable(network: Network) {
        super.onAvailable(network)

        if (networkConnectionState() == null) {
            Toast.makeText(context, "Null Network", Toast.LENGTH_SHORT).show()
            MyDialog(context).unConnectionDialog.show()
        }
        else {
            Toast.makeText(context, "Connected Network : $networkType", Toast.LENGTH_SHORT).show()
            networkState = "연결 중"
        }
    }

    //네트워크 끊겼을 때 실행되는 메소드
    override fun onLost(network: Network) {
        super.onLost(network)

        networkState = "연결 해제"
        networkType = "-"
        Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show()
        MyDialog(context).unConnectionDialog.show()
    }

    private fun networkConnectionState(): Network? {
        val network: Network? = connectivityManager.activeNetwork //연결된 네트워크가 없을 시 null 리턴
        val networkCapabilities: NetworkCapabilities? = connectivityManager.getNetworkCapabilities(network)
        if (networkCapabilities == null) {
            networkType = "Null Network"
        }
        when {
            networkCapabilities!!.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> { networkType = "WIFI Network" }
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> { networkType = "Mobile Network" }
        }
        return network
    }
}