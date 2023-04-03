package com.mobilearts.nftworld

import android.app.Application
import com.mobilearts.nftworld.objects.Constants
import com.revenuecat.purchases.Purchases
import com.revenuecat.purchases.PurchasesConfiguration

class MainApplication : Application() {

    companion object { lateinit var instance: MainApplication }

    override fun onCreate() {
        super.onCreate()
        instance = this
        // initialize for any
        Purchases.debugLogsEnabled = true
        Purchases.configure(
            PurchasesConfiguration.Builder(
                this,
                Constants.GOOGLE_API_KEY
            ).build()
        )
    }
}
