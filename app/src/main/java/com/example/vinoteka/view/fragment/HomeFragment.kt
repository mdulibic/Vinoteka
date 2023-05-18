package com.example.vinoteka.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vinoteka.R
import com.example.vinoteka.databinding.FragmentHomeBinding
import com.example.vinoteka.utils.SpacingItemDecorator
import com.example.vinoteka.utils.WineAdapter
import com.example.vinoteka.utils.toPx
import com.example.vinoteka.utils.wineList
import com.example.vinoteka.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var wineListAdapter: WineAdapter

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setOnClick()

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        binding.tvAdminName.text = viewModel.getAdminEmail()

        // viewModel.getWines()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.wineListSuccess.observe(viewLifecycleOwner) {
            wineListAdapter.setData(it)
        }
    }

    private fun setOnClick() {
        binding.btnAdd.setOnClickListener {
            svm.navigate(HomeFragmentDirections.actionNavigationHomeToAddWineFragment())
        }
    }

    private fun initRecyclerView() {
        wineListAdapter = WineAdapter(
            onItemClicked = {
                svm.navigate(HomeFragmentDirections.actionNavigationHomeToNavigationWineDetails(it))
            },
            onItemDelete = {
                // viewModel.deleteWine(id = it)
                viewModel.getWines()
            },
        )
        val spacingDecoration =
            SpacingItemDecorator(resources.getInteger(R.integer.margin_tv_item).toPx())
        binding.rvWineList.apply {
            adapter = wineListAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(spacingDecoration)
        }

        wineListAdapter.setData(wineList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getToolbar(): Toolbar? {
        return null
    }
}
