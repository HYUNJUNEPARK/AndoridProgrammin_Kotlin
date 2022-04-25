package com.example.networkstate

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.wifi.WifiManager.ACTION_PICK_WIFI_NETWORK
import android.widget.Toast

class NetworkConnection(private val context: Context) : ConnectivityManager.NetworkCallback() {
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val networkRequest: NetworkRequest = NetworkRequest.Builder()
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .build()
    private val unConnectionDialog: AlertDialog by lazy {
        AlertDialog.Builder(context)
            .setTitle("네트워크 연결 안 됨")
            .setMessage("WIFE 또는 LTE 연결을 확인해주세요")
            .setPositiveButton("취소") { _, _ -> }
            .setNegativeButton("WIFI 연결") { _, _ ->
                connectWifi()
            }
            .create()
    }
    var networkType : String? = "-"
    var networkState: String? = "연결 해제"

//[START NetworkCallback]
    //NetworkCallback 등록
    fun register() {
        connectivityManager.registerNetworkCallback(networkRequest, this)
    }

    //콜백이 등록되거나 네트워크가 연결되었을 때 실행되는 메소드
    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        if (getConnectivityStatus() == null) {
            Toast.makeText(context, "Network Null", Toast.LENGTH_SHORT).show()
            unConnectionDialog.show()
        } else {
            Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show()
            networkState = "연결 중"
            unConnectionDialog.dismiss()
        }
    }

    //네트워크 끊겼을 때 실행되는 메소드
    override fun onLost(network: Network) {
        super.onLost(network)

        networkState = "연결 해제"
        networkType = "-"
        Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show()
        unConnectionDialog.show()
    }

    //NetworkCallback 해제
    fun unregister() {
        connectivityManager.unregisterNetworkCallback(this)
    }
//[END NetworkCallback]

    private fun getConnectivityStatus(): Network? {
        val network: Network? = connectivityManager.activeNetwork ?: null // 연결된 네트워크가 없을 시 null 리턴
        setNetworkType(network)
        return network
    }

    private fun setNetworkType(network: Network?) {
        val networkCapabilities: NetworkCapabilities? = connectivityManager.getNetworkCapabilities(network) ?: null
        if (networkCapabilities == null) {
            networkType = "networkCapabilities null"
        }
        when {
            networkCapabilities!!.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                networkType = "WIFI"
            }
            networkCapabilities!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                networkType = "LTE"
            }
            else -> "알 수 없는 네트워크"
        }
    }

    private fun connectWifi() {
        val intent = Intent(ACTION_PICK_WIFI_NETWORK)
        context.startActivity(intent)
    }
}