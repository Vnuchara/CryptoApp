package com.example.cryptoapp.domain.usecases

import com.example.cryptoapp.domain.repository.Repository

class GetCoinInfoListUseCase(
    private val repository: Repository
) {
    operator fun invoke() = repository.getCoinInfoList()
}