package io.rusha.bnet_test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ListViewModel : ViewModel() {
    private val mainApi = ServiceLocator.getMainApi()
    private val compositeDisposable = CompositeDisposable()
    val entriesLiveData = MutableLiveData<List<Entry>>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun onAppear() {
        compositeDisposable.add(
            mainApi.getEntries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        entriesLiveData.value = response.entryList()
                    },
                    { e ->

                    }
                )
        )
    }
}