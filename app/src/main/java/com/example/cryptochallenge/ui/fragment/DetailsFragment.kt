package com.example.cryptochallenge.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptochallenge.R
import com.example.cryptochallenge.databinding.FragmentDetailsBinding
import com.example.cryptochallenge.domain.model.TickerData
import com.example.cryptochallenge.formatCurrency
import com.example.cryptochallenge.ui.adapter.OrderAdapter
import com.example.cryptochallenge.ui.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var askAdapter: OrderAdapter
    private lateinit var bidAdapter: OrderAdapter

    private val detailsViewModel: DetailsViewModel by viewModels()

    private val detailsArgs by navArgs<DetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bookName = detailsArgs.bookName
        detailsViewModel.callAPI(bookName)
        setupObservers()
        setupAskRecyclerView()
        setupBidRecyclerView()
    }

    private fun setupBidRecyclerView() {
        bidAdapter = OrderAdapter()
        binding.askRecyclerView.apply {
            adapter = bidAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setupAskRecyclerView() {
        askAdapter = OrderAdapter()
        binding.bidsRecyclerView.apply {
            adapter = askAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setupObservers() {
        detailsViewModel.tickerData.observe(viewLifecycleOwner, {
            bindTicker(it)
        })
        detailsViewModel.error.observe(viewLifecycleOwner, {
            Toast.makeText(context, "Something went wrong... $it", Toast.LENGTH_LONG).show()
            findNavController().navigateUp()
        })
        detailsViewModel.payloadOrder.observe(viewLifecycleOwner, {
            askAdapter.submitList(it.asks)
            bidAdapter.submitList(it.bids)
        })
    }

    private fun bindTicker(tickerData: TickerData) {
        binding.apply {

            val baseCurrency = tickerData.book.split("_")[0]
            val resImg = root.context.resources.getIdentifier(
                baseCurrency, "drawable",
                root.context.packageName
            )
            // TODO: change it to something more "kotlin"
            if (resImg> 0) {
                cryptoDetailImageView.setImageResource(resImg)
            } else {
                cryptoDetailImageView.setImageResource(R.drawable.unknow)
            }

            highTextView.text = tickerData.high.formatCurrency()
            lowTextView.text = tickerData.low.formatCurrency()
            volumeTextView.text = tickerData.volume.formatCurrency()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
