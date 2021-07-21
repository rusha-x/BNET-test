package io.rusha.bnet_test

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

class AddFragment : Fragment(R.layout.create_entry) {
    private val viewModel by viewModels <AddViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addNewEntry = view.findViewById<View>(R.id.add_button)
        val cancelEntry = view.findViewById<View>(R.id.cancel_button)
        val nameEdit = view.findViewById<EditText>(R.id.nameEdit)

        cancelEntry.setOnClickListener() {
            viewModel.onCancelClick()
        }

        addNewEntry.setOnClickListener() {
            viewModel.onAddClick(nameEntry = nameEdit.text.toString())
        }
        viewModel.isClosedLiveData.observe(viewLifecycleOwner) { isClosed ->
            if ( isClosed != null && isClosed) {
                requireActivity().supportFragmentManager.popBackStack()

            }
        }
        viewModel.isRetryShowedLiveEvent.observe(viewLifecycleOwner) {
            AlertDialog.Builder(requireContext())
                .setTitle("Интернет недоступен")
                .setPositiveButton("Повторить", { _, _ ->
                    viewModel.onRetryClick()
                })
                .show()
        }
    }

}