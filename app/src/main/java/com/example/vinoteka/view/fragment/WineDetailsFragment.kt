package com.example.vinoteka.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.vinoteka.R
import com.example.vinoteka.databinding.FragmentHomeBinding
import com.example.vinoteka.databinding.FragmentWineDetailsBinding
import com.example.vinoteka.model.Maltster
import com.example.vinoteka.model.Sort
import com.example.vinoteka.model.Wine
import com.example.vinoteka.utils.wineList
import com.example.vinoteka.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WineDetailsFragment : BaseFragment(R.layout.fragment_wine_details) {

    private var _binding: FragmentWineDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    private val wineId by lazy { WineDetailsFragmentArgs.fromBundle(requireArguments()).id }

    private var wine: Wine? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentWineDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        observeLiveData()
        setOnClickClickListeners()
        //viewModel.getWines()

        wine = wineList.find { it.id.toString() == wineId }
        binding.apply {
            editTextName.setText(wine?.name)
            editTextHarvest.setText(wine?.harvest)
            editTextAlcoholPercentage.setText(wine?.alcoholPercentage.toString())
            editTextMaltster.setText(wine?.maltster?.name)
            editTextQuality.setText(wine?.quality)
            editTextVineyard.setText(wine?.vineyard)
            editTextTemperatureOfServing.setText(wine?.temperatureOfServing)
            editTextGastroRecommendation.setText(wine?.gastroRecommendation)
            editTextDescription.setText(wine?.description)
            editTextPrice.setText(wine?.price.toString())
            editTextSort.setText(wine?.sort?.name)
        }
    }

    override fun getToolbar(): Toolbar? {
        return null
    }

    private fun observeLiveData() {
        viewModel.wineListSuccess.observe(viewLifecycleOwner) { wines ->
            wine = wineList.find { it.id.toString() == wineId }
            binding.apply {
                editTextName.setText(wine?.name)
                editTextHarvest.setText(wine?.harvest)
                editTextAlcoholPercentage.setText(wine?.alcoholPercentage.toString())
                editTextMaltster.setText(wine?.maltster?.name)
                editTextQuality.setText(wine?.quality)
                editTextVineyard.setText(wine?.vineyard)
                editTextTemperatureOfServing.setText(wine?.temperatureOfServing)
                editTextGastroRecommendation.setText(wine?.gastroRecommendation)
                editTextDescription.setText(wine?.description)
                editTextPrice.setText(wine?.price.toString())
                editTextSort.setText(wine?.sort?.name)
            }
        }
    }

    private fun setOnClickClickListeners() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
