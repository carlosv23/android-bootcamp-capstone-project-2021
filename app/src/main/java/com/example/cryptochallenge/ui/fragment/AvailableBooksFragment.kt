package com.example.cryptochallenge.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptochallenge.databinding.FragmentAvailableBooksBinding
import com.example.cryptochallenge.domain.model.Book
import com.example.cryptochallenge.ui.adapter.AvailableBooksAdapter
import com.example.cryptochallenge.ui.viewmodel.AvailableBooksViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AvailableBooksFragment : Fragment() {

    private var _binding: FragmentAvailableBooksBinding? = null
    private val binding get() = _binding!!

    lateinit var availableBookAdapter: AvailableBooksAdapter

    private val availableBooksViewModel: AvailableBooksViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAvailableBooksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupObservers()
        setupRecyclerView()
    }

    private fun setupObservers() {
        availableBooksViewModel.bookList.observe(viewLifecycleOwner, {
            availableBookAdapter.submitList(it)
        })
        availableBooksViewModel.error.observe(viewLifecycleOwner, {
            Toast.makeText(context,"Something went wrong: $it", Toast.LENGTH_LONG).show()
        })
    }

    private fun setupRecyclerView() {
        availableBookAdapter = AvailableBooksAdapter { onClickRecycler(it) }
        binding.availableBookRecyclerView.apply {
            adapter = availableBookAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun onClickRecycler(item: Any){
        if(item is Book){
            val action = AvailableBooksFragmentDirections
                .actionAvailableBooksragmentToDetailsFragment(item.name)
            this.findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}