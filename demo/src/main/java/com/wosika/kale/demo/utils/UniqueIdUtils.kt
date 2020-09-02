package com.wosika.kale.demo.utils

import android.content.Context
import android.provider.Settings
import android.text.TextUtils
import java.lang.reflect.Method
import java.net.NetworkInterface
import java.security.MessageDigest
import java.util.*

//Get unique ID of the device
object UniqueIdUtils {

    /*
    * Gets a unique ID after the phone's andoridId+ MAC address +SerialNumber MD5
    * No need for permission
    * It could be empty
    * */
    fun getUniqueId(context: Context): String? {
        val uniqueId = getAndroidId(
            context
        ) + getMacAddressFromHardware() + getSerialNumber()
        return if (uniqueId.isNotBlank()) {
            uniqueId.md5()
        } else {
            null
        }
    }

    /**
     *
     * get AndroidId
     * defect:
     * When the factory is restored or the machine is swiped, it will be reset in some manufacturers' customized systems.
     * It may be empty, or it may produce the same value in different devices. For CDMA Device summary, Android Id and Device Id will return the same value
     * @param context
     * @return androidId or ""
     */

    private fun getAndroidId(context: Context): String {
        val androidId: String =
            Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        return if (TextUtils.isEmpty(androidId)) "" else androidId
    }

    /**
     * Get  Android serial number
     * 6.0 can be obtained through
     * 10 cannot get null.
     *
     * @return serialNumber or ""
     */
    @Synchronized
    private fun getSerialNumber(): String {
        var serialNumber = ""
        try {
            val clazz = Class.forName("android.os.SystemProperties")
            if (clazz != null) {
                val method: Method =
                    clazz.getMethod("get", String::class.java, String::class.java)
                if (method != null) {
                    serialNumber = method.invoke(clazz, "ro.serialno", "") as String
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return serialNumber
    }


    /**
     * Iterate through all the network interfaces and find that the interface is WLAN0
     * Required permissions <uses-permission android:name="android.permission.INTERNET"></uses-permission>
     * @return macAddressFromHardware
     */
    private fun getMacAddressFromHardware(): String {
        try {
            val all: List<NetworkInterface> =
                Collections.list(NetworkInterface.getNetworkInterfaces())
            for (nif in all) {
                if (!nif.name.equals("wlan0", true)) {
                    continue
                }
                val macBytes: ByteArray = nif.hardwareAddress ?: return ""
                val res1 = StringBuilder()
                for (b in macBytes) {
                    res1.append(String.format("%02X:", b))
                }
                if (res1.isNotEmpty()) {
                    res1.deleteCharAt(res1.length - 1)
                }
                return res1.toString()
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return ""
    }


    private fun String.md5(): String {
        val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
        return bytes.hex()
    }

    private fun ByteArray.hex(): String {
        return joinToString("") { "%02X".format(it) }
    }

}