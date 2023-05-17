package com.example.vinoteka.view.fragment

import com.example.vinoteka.utils.NavCommand
import com.example.vinoteka.viewModel.SharedViewModel
import timber.log.Timber
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.vinoteka.R

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {

    protected val svm: SharedViewModel by activityViewModels()
    private val navController by lazy { findNavController() }

    init {
        if (layoutRes == 0) Timber.w("You are trying to use layoutRes from constructor, but haven't set it (probably for ViewBinding)")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getToolbar()?.let {
            initToolbar(it)
        }

        svm.navCommand.observe(viewLifecycleOwner) {
            val content: NavCommand? = it.getContentIfNotHandled()
            try {
                content?.let { command ->
                    when (command) {
                        is NavCommand.To -> {
                            Timber.tag(this::class.java.simpleName)
                                .i("Navigating from ${navController.currentDestination}")
                            Timber.tag(this::class.java.simpleName)
                                .i("with action: ${command.directions.actionId}")
                            Timber.tag(this::class.java.simpleName)
                                .i("with arguments: ${command.directions.arguments}")
                            try {
                                navController.navigate(
                                    command.directions
                                )
                            } catch (exc: Exception) {
                                Timber.w(exc)
                                navController.navigate(command.directions)
                            }
                            Timber.tag(this::class.java.simpleName)
                                .i("Navigated to: ${navController.currentDestination}")
                        }
                        is NavCommand.ToRoot -> TODO("not implemented")
                        is NavCommand.Up -> {
                            Timber.tag(this::class.java.simpleName)
                                .i("Navigating *UP* from ${navController.currentDestination}")
                            navController.navigateUp()
                            Timber.tag(this::class.java.simpleName)
                                .i("Navigated *UP* to ${navController.currentDestination}")
                        }
                        is NavCommand.UpTo -> TODO("Not implemented")
                    }
                }
            } catch (exc: Exception) {
                Timber.e(exc, "Failed to navigate $content")
            }
        }
    }

    open fun initToolbar(toolbar: Toolbar) {
        toolbar.apply {
            setupWithNavController(navController)
            navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        }
    }

    abstract fun getToolbar(): Toolbar?
}