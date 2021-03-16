package com.eastnine.util.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel: ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    
    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
    
    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}