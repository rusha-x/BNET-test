package io.rusha.bnet_test

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView

class ListFragment : Fragment(R.layout.fragment_list) {
    private val viewModel by viewModels<ListViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val addButton = view.findViewById<View>(R.id.add_button)

        viewModel.entriesLiveData.observe(viewLifecycleOwner) { entries ->
            recyclerView.adapter = EntriesAdapter(entries ?: listOf())
        }
        viewModel.isRetryShowedLiveEvent.observe(viewLifecycleOwner) {
            AlertDialog.Builder(requireContext())
                .setTitle("Интернет недоступен")
                .setPositiveButton("Повторить", { _, _ ->
                    viewModel.onRetryClick()
                })
                .show()
        }
        addButton.setOnClickListener() {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AddFragment())
                .addToBackStack(null)
                .commit()
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.onAppear()
    }

}