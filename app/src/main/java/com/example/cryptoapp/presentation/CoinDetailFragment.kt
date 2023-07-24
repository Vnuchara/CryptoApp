package com.example.cryptoapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.databinding.FragmentCoinDetailBinding
import com.example.cryptoapp.domain.entities.CoinInfo
import com.squareup.picasso.Picasso


class CoinDetailFragment : Fragment() {

    private lateinit var coinInfo: CoinInfo

    private var _binding: FragmentCoinDetailBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[CoinViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            coinInfo = it.getParcelable(ARG_PARAM1) ?: throw RuntimeException("Missing coinInfo")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getDetailInfo(coinInfo.fromSymbol).observe(viewLifecycleOwner) {
            with(binding) {
                tvPrice.text = it.price
                tvMinPrice.text = it.lowDay
                tvMaxPrice.text = it.highDay
                tvLastMarket.text = it.lastMarket
                tvLastUpdate.text = it.lastUpdate
                tvFromSymbol.text = it.fromSymbol
                tvToSymbol.text = it.toSymbol
                Picasso.get().load(it.imageUrl).into(ivLogoCoin)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_PARAM1 = "coinInfo"

        @JvmStatic
        fun newInstance(coinInfo: CoinInfo) =
            CoinDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, coinInfo)
                }
            }
    }
}