package com.example.vinoteka.view.fragment

import androidx.appcompat.widget.Toolbar
import com.example.vinoteka.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddWineFragment : BaseFragment(R.layout.fragment_add_wine) {
    override fun getToolbar(): Toolbar? {
        return null
    }
}
