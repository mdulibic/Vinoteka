package com.example.vinoteka.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.vinoteka.R
import com.example.vinoteka.databinding.ActivityMainBinding
import com.example.vinoteka.viewModel.WelcomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentNavController: LiveData<NavController>? = null

    private lateinit var viewModel: WelcomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[WelcomeViewModel::class.java]

        viewModel.checkIfEmailValidated()

        viewModel.emailValidationSuccess.observe(this) {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.host_fragment) as NavHostFragment
            val inflater = navHostFragment.navController.navInflater
            val graph = inflater.inflate(R.navigation.nav_graph_home)
            graph.setStartDestination(R.id.navigation_home)
            val navController = navHostFragment.navController
            navController.setGraph(graph, intent.extras)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: super.onSupportNavigateUp()
    }
}
