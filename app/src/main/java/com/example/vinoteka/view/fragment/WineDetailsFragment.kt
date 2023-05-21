package com.example.vinoteka.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.vinoteka.R
import com.example.vinoteka.databinding.FragmentWineDetailsBinding
import com.example.vinoteka.model.Sort
import com.example.vinoteka.networking.model.WineRequest
import com.example.vinoteka.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WineDetailsFragment : BaseFragment(R.layout.fragment_wine_details) {

    private var _binding: FragmentWineDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    private val wineId by lazy { WineDetailsFragmentArgs.fromBundle(requireArguments()).id }

    val sorts = arrayListOf<Sort>()

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
        viewModel.getWineById(id = wineId)

        viewModel.getSorts()
    }

    override fun getToolbar(): Toolbar? {
        return null
    }

    private fun observeLiveData() {
        viewModel.wineDetailsSuccess.observe(viewLifecycleOwner) { wine ->
            binding.apply {
                editTextName.setText(wine?.name)
                editTextHarvest.setText(wine?.harvest.toString())
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

        viewModel.wineDetailsUpdateSuccess.observe(viewLifecycleOwner) {
            svm.navigate(WineDetailsFragmentDirections.actionNavigationWineDetailsToNavigationHome())
        }

        viewModel.sortsSuccess.observe(viewLifecycleOwner) {
            sorts.clear()
            sorts.addAll(it)
        }
    }

    private fun setOnClickClickListeners() {
        binding.btnUpdate.setOnClickListener {
            val sortId = sorts.single { it.name == binding.editTextSort.text.toString() }.id
            val wine = WineRequest(
                name = binding.editTextName.text.toString(),
                harvest = binding.editTextHarvest.text.toString().toInt(),
                alcoholPercentage = binding.editTextAlcoholPercentage.text.toString().toFloat(),
                maltster = binding.editTextMaltster.text.toString(),
                quality = binding.editTextQuality.text.toString(),
                vineyard = binding.editTextVineyard.text.toString(),
                temperatureOfServing = binding.editTextTemperatureOfServing.text.toString(),
                gastroRecommendation = binding.editTextGastroRecommendation.text.toString(),
                description = binding.editTextDescription.text.toString(),
                price = binding.editTextPrice.text.toString().toDouble(),
                sortId = sortId,
            )
            viewModel.updateWineDetails(id = wineId, wine = wine)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
