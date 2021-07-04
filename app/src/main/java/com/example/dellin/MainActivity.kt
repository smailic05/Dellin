package com.example.dellin

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.dellin.databinding.MainActivityBinding
import com.google.android.gms.location.*


class MainActivity : AppCompatActivity(), AppBarInterface {
    private lateinit var binding: MainActivityBinding
    lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val model: MainViewModel by viewModels()
        val view = binding.root
        setContentView(view)
        setupNavigation()
        fusedLocationClient=LocationServices.getFusedLocationProviderClient(this)
        checkPermission()
        fusedLocationClient.lastLocation.addOnSuccessListener {
            Dellin.location=it
            model.createRequest()
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
            Toast.makeText(this, "Require permission", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return true
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