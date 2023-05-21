package com.example.vinoteka.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.vinoteka.R
import com.example.vinoteka.databinding.FragmentAddWineBinding
import com.example.vinoteka.model.Maltster
import com.example.vinoteka.model.Sort
import com.example.vinoteka.networking.model.WineRequest
import com.example.vinoteka.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddWineFragment : BaseFragment(R.layout.fragment_add_wine) {

    private var _binding: FragmentAddWineBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    private val sorts = arrayListOf<Sort>()

    private val maltsters = mutableListOf(*Maltster.values())

    private var selectedSort: Long? = null

    private var selectedMaltster: Maltster? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAddWineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClick()

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        maltsters.removeLast()

        viewModel.getSorts()
        observeLiveData()

        val spinner = binding.maltsterSpinner
        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            maltsters.map { it.value },
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
            spinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(p0: AdapterView<*>?) {}

                    override fun onItemSelected(
                        p0: AdapterView<*>?,
                        p1: View?,
                        position: Int,
                        p3: Long,
                    ) {
                        selectedMaltster = maltsters[position]
                    }
                }
        }
    }

    private fun observeLiveData() {
        viewModel.sortsSuccess.observe(viewLifecycleOwner) {
            sorts.clear()
            sorts.addAll(it)
            val spinner = binding.sortSpinner

            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                sorts.map { it.name },
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
                spinner.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(p0: AdapterView<*>?) {}

                        override fun onItemSelected(
                            p0: AdapterView<*>?,
                            p1: View?,
                            position: Int,
                            p3: Long,
                        ) {
                            selectedSort = sorts[position].id
                        }
                    }
            }
        }
    }

    private fun setOnClick() {
        binding.btnAdd.setOnClickListener {
            val wine = WineRequest(
                name = binding.editTextName.text.toString(),
                harvest = binding.editTextHarvest.text.toString().toInt(),
                alcoholPercentage = binding.editTextAlcoholPercentage.text.toString().toFloat(),
                maltster = selectedMaltster!!.value,
                quality = binding.editTextQuality.text.toString(),
                vineyard = binding.editTextVineyard.text.toString(),
                temperatureOfServing = binding.editTextTemperatureOfServing.text.toString(),
                gastroRecommendation = binding.editTextGastroRecommendation.text.toString(),
                description = binding.editTextDescription.text.toString(),
                price = binding.editTextPrice.text.toString().toDouble(),
                sortId = selectedSort!!,
            )
            viewModel.addWine(wine = wine)
            svm.navigate(AddWineFragmentDirections.actionAddWineFragmentToNavigationHome())
        }
    }

    override fun getToolbar(): Toolbar? {
        return null
    }
}
