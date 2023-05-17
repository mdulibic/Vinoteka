package com.example.vinoteka.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.vinoteka.R
import com.example.vinoteka.databinding.FragmentHomeBinding
import com.example.vinoteka.utils.WineAdapter
import com.example.vinoteka.utils.VerticalSpaceItemDecorator
import com.example.vinoteka.utils.toPx
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var wineListdapter: WineAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        wineListdapter = WineAdapter()
        val verticalSpace = VerticalSpaceItemDecorator(resources.getInteger(R.integer.margin_tv_item).toPx())
        /*
        binding.rvEuroCentsList.apply {
            adapter = wineListdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(verticalSpace)
        }

         */
        /*
            val items = listOf()
        euroCentsListdapter.setData(items)
         */
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}