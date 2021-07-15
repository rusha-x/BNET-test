package io.rusha.bnet_test

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView

class ListFragment : Fragment(R.layout.fragment_list) {
    private val viewModel by viewModels<ListViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        viewModel.entriesLiveData.observe(viewLifecycleOwner) { entries ->
            recyclerView.adapter = EntriesAdapter(entries ?: listOf())
        }
    }

}