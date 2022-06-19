package com.example.feedapplication.presention

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.feedapplication.R
import com.example.feedapplication.databinding.ActivityMainBinding
import com.example.feedapplication.presention.adapter.FavoritAdapter
import com.example.feedapplication.presention.adapter.HomeAdapter
import com.example.feedapplication.presention.viewmodel.Homeviewmodel.HomeViewModel
import com.example.feedapplication.presention.viewmodel.Homeviewmodel.HomeViewModelFactory
import com.example.feedapplication.presention.viewmodel.localviewmodel.LocalViewModel
import com.example.feedapplication.presention.viewmodel.localviewmodel.LocalViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import junit.runner.Version.id
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    @Inject
    lateinit var localViewModelFactory: LocalViewModelFactory
    lateinit var localViewModel: LocalViewModel

    @Inject
    lateinit var homeAdapter: HomeAdapter

    @Inject
    lateinit var favoritAdapter: FavoritAdapter

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory
    lateinit var homeViewModel: HomeViewModel

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeViewModel = ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)
        localViewModel =
            ViewModelProvider(this, localViewModelFactory).get(LocalViewModel::class.java)
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHost.navController

        binding.BottomNavigationView.setupWithNavController(navController)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when (destination.id) {

            R.id.homeFragment -> {
                Log.i("TAG", "Home")

            }
            R.id.favoritsFragment -> {
                Log.i("TAG", "Favorit")


            }
            R.id.newsDetailsFragment -> {
                Log.i("TAG", "Details")

            }

        }

    }



    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(this)

    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(this)

    }


}