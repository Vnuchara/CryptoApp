package com.example.cryptoapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.cryptoapp.domain.entities.CoinInfo

interface Repository {

    fun getCoinInfoList(): LiveData<List<CoinInfo>>

    suspend fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo>

    suspend fun loadData()
}