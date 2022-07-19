package com.happyplaces.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.AsyncTask
import android.util.Log
import java.io.IOException
import java.util.*

/**
 * A AsyncTask class to get the address from latitude and longitude.
 */
class GetAddressFromLatLng(
    context: Context,
    private val latitude: Double,
    private val longitude: Double): AsyncTask<Void, String, String>() {

    /**
     * Constructs a Geocoder whose responses will be localized for thegiven Locale.
     *
     * @param context the Context of the calling Activity
     * @param locale the desired Locale for the query results
     *
     * @throws NullPointerException if Locale is null
     */
    private val geocoder: Geocoder = Geocoder(context, Locale.getDefault())

    private lateinit var mAddressListener: AddressListener

    // Background method of AsyncTask where the background operation will be performed.
    override fun doInBackground(vararg params: Void?): String {
        try {
            val addressList: List<Address>? = geocoder.getFromLocation(latitude, longitude, 1)
            if (addressList != null && addressList.isNotEmpty()) {
                val address: Address = addressList[0]
                val sb = StringBuilder()
                for (i in 0..address.maxAddressLineIndex) {
                    sb.append(address.getAddressLine(i)).append(",")
                }
                sb.deleteCharAt(sb.length - 1) // Here we remove the last comma that we have added above from the address.
                return sb.toString()
            }
        } catch (e: IOException) {
            Log.e("HappyPlaces", "Unable connect to Geocoder")
        }
        return ""
    }

    // onPostExecute method of AsyncTask where the result will be received and assigned to the interface accordingly.
    override fun onPostExecute(resultString: String?) {
        if (resultString == null) {
            mAddressListener.onError()
        } else {
            mAddressListener.onAddressFound(resultString)
        }
        super.onPostExecute(resultString)
    }

    fun setAddressListener(addressListener: AddressListener) {
        mAddressListener = addressListener
    }

    // A public function to execute the AsyncTask from the class is it called.
    fun getAddress() {
        execute()
    }

    // A interface for AddressListener which contains the function like success and error.
    interface AddressListener {
        fun onAddressFound(address: String?)
        fun onError()
    }
}