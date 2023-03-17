package com.inditex.architecturemvvm.presentation.feature.first

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.inditex.architecturemvvm.R
import com.inditex.architecturemvvm.databinding.FragmentFirstBinding
import com.inditex.architecturemvvm.presentation.base.BaseFragment
import com.inditex.architecturemvvm.presentation.util.toast
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment
    : BaseFragment<FragmentFirstBinding, FirstViewModel>(FirstViewModel::class) {

    override val viewModel: FirstViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_first

    override fun addBindingVariables() {
        super.addBindingVariables()
        with(binding) {
            viewModel = this@FirstFragment.viewModel
            setFirstButtonClicked(this@FirstFragment::onFirstButtonClicked)
        }
    }

    private fun onFirstButtonClicked(view: View) {
        FirstFragmentDirections.actionFirstFragmentToSecondFragment().also { action ->
            findNavController().navigate(action)
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        with(viewModel) {
            examplesList.observe(viewLifecycleOwner) { result ->
                "Recibida una lista de exampleBO de ${result.size} items".toast(requireContext())
            }
        }
    }
}