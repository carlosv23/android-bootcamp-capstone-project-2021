package com.example.cryptochallenge.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptochallenge.data.datasource.remote.RemoteBitsoDataSource
import com.example.cryptochallenge.data.repository.BitsoRepository
import com.example.cryptochallenge.domain.model.Order
import com.example.cryptochallenge.domain.model.OrderBook
import com.example.cryptochallenge.domain.model.PayloadOrder
import com.example.cryptochallenge.domain.model.TickerData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: RemoteBitsoDataSource,
    private val repo: BitsoRepository
) : ViewModel() {

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private var _tickerData = MutableLiveData<TickerData>()
    val tickerData: LiveData<TickerData> = _tickerData

    private var _payloadOrder = MutableLiveData<PayloadOrder>()
    val payloadOrder: LiveData<PayloadOrder> = _payloadOrder

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var disposable = CompositeDisposable()

    fun callAPI(book: String) {
        getOrderBook(book)
        getTicker(book)
    }

    private fun getTicker(book: String) {
        repo.getTicker(book)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(this::onTickerSuccess, this::onRequestError)
            ?.let { disposable.add(it) }
    }

    private fun onTickerSuccess(tickerData: TickerData) {
        _tickerData.postValue(tickerData)
    }


    private fun onRequestError(t: Throwable) {
        _error.postValue(t.message)
    }

    private fun getOrderBook(book: String) {
        repo.getOrderBook(book)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe( this::onOrderBookSuccess, this::onRequestError)
            ?.let { disposable.add(it) }
    }

    private fun onOrderBookSuccess(payloadOrder: PayloadOrder) {
        _payloadOrder.postValue(payloadOrder)
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }


}