package com.udacity.asteroidradar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.work.*
import com.udacity.asteroidradar.api.AsteroidCacheWorker
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val navController by lazy {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val topLevelDestinations = setOf(R.id.mainFragment)
        val appToolbarConfig = AppBarConfiguration(topLevelDestinationIds = topLevelDestinations)

        NavigationUI.setupActionBarWithNavController(
            this,
            navController,
            appToolbarConfig
        )

        startAsteroidWorker()
    }

    private fun startAsteroidWorker() {
        val constraints: Constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .build()

        val workRequest: PeriodicWorkRequest =
            PeriodicWorkRequestBuilder<AsteroidCacheWorker>(1, TimeUnit.DAYS)
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(
                AsteroidCacheWorker.WORKER_NAME,
                ExistingPeriodicWorkPolicy.REPLACE,
                workRequest
            )
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}
