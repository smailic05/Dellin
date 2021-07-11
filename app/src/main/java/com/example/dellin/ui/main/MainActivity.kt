package com.example.dellin.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.dellin.Dellin
import com.example.dellin.R
import com.example.dellin.databinding.MainActivityBinding
import com.google.android.gms.location.*


class MainActivity : AppCompatActivity(), AppBarInterface {
    private lateinit var binding: MainActivityBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val requestPermissionLauncher =registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(this, "Nice", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Require permission", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupNavigation()
        fusedLocationClient=LocationServices.getFusedLocationProviderClient(this)
        checkPermission()
        fusedLocationClient.lastLocation.addOnSuccessListener {
            Dellin.location =it
        }

    }

    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)

        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return false
    }
    private fun setupNavigation()
    {
        setSupportActionBar(binding.toolbar)
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.container) as NavHostFragment? ?: return
        val navController = host.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController,appBarConfiguration)
    }

    override fun hideAppBar() {
        supportActionBar?.hide()
    }

    override fun showAppBar() {
        supportActionBar?.show()
    }
}