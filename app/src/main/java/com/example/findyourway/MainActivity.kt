package com.example.findyourway

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.rememberNavController
import com.example.findyourway.HomePage.HomeScreen
import com.example.findyourway.Navigation.NavGraph
import com.example.findyourway.ViewModel.viewModel
import com.example.findyourway.ui.theme.FindyourwayTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

lateinit var local: Location
class MainActivity : ComponentActivity() {

    private lateinit var fusedLocationProvider: FusedLocationProviderClient
    private lateinit var currentLocation: Location
    var firebaseUser: FirebaseUser? = null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationProvider = LocationServices.getFusedLocationProviderClient(this)
        val IsFineLocallowed = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val IsCoarseLocAllowed = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
        val granted = PackageManager.PERMISSION_GRANTED

        if (IsFineLocallowed != granted || IsCoarseLocAllowed != granted)
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 101)
            return
        }
        val task = fusedLocationProvider.lastLocation
        task.addOnSuccessListener { location ->
            if (location != null) {
                local = location
            }
        }

        firebaseUser = FirebaseAuth.getInstance().currentUser
        val viewModel: viewModel by viewModels()

        setContent {
            FindyourwayTheme {
                val navContoller = rememberNavController()
                if (firebaseUser != null){
                    HomeScreen(navHostController = navContoller, viewModel = viewModel)
                }else{
                    NavGraph(navHostController = navContoller)
                }
            }
        }
    }
}