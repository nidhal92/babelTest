package com.example.mertkesgin.weatherapp.ui.citiesList

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.mertkesgin.weatherapp.databinding.FragmentAddLocationDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddLocationDialogFragment : DialogFragment() {
    private val viewModel: ListViewModel by viewModels()
    private var _binding: FragmentAddLocationDialogBinding? = null
    private val binding get() = _binding!!
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentAddLocationDialogBinding.inflate(LayoutInflater.from(context))
        initLayout()
        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
    }

    private fun initLayout() {
        binding.addBtn.setOnClickListener {
            if (binding.editText.text.toString().isNotEmpty()) {

                viewModel.addLocation(binding.editText.text.toString())

                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
