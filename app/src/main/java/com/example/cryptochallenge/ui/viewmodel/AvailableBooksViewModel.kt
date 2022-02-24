package com.example.cryptochallenge.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptochallenge.data.datasource.remote.RemoteBitsoDataSource
import com.example.cryptochallenge.data.repository.BitsoRepository
import com.example.cryptochallenge.domain.model.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AvailableBooksViewModel @Inject constructor(
    private val repository: BitsoRepository
) : ViewModel() {

    private var _bookList = MutableLiveData<List<Book>>()
    val bookList: LiveData<List<Book>> = _bookList

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private var disposable = CompositeDisposable()

    init {
        getAvailableBooks()
    }

    fun getAvailableBooks() {
        repository.getAvailableBooks()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(this::onAvailableBookReceived, this::onAvailableBookError)
            .let {
                it?.let {
                    disposable.add(it)
                }
            }
    }

    private fun onAvailableBookReceived(bookList: List<Book>) {
        _bookList.postValue(bookList)
    }

    private fun onAvailableBookError(error: Throwable) {
        _error.postValue(error.message)
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }


}
