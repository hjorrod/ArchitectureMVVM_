package com.inditex.architecturemvvm.presentation.feature.second

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.inditex.architecturemvvm.R
import com.inditex.architecturemvvm.databinding.FragmentSecondBinding
import com.inditex.architecturemvvm.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class SecondFragment
    : BaseFragment<FragmentSecondBinding, SecondViewModel>(SecondViewModel::class) {

    override val viewModel: SecondViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_second

    override fun addBindingVariables() {
        super.addBindingVariables()
        with(binding) {
            viewModel = this@SecondFragment.viewModel
            setSecondButtonClicked(this@SecondFragment::onSecondButtonClicked)
        }
    }

    private fun onSecondButtonClicked(view: View) {
        SecondFragmentDirections.actionSecondFragmentToFirstFragment().also { action ->
            findNavController().navigate(action)
        }
    }
}