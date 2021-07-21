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
    val isRetryShowedLiveEvent = SingleLiveEvent<Unit>()
    var session = ""

    init {
        mainApi.newSession(token = "4x1bIe0-jS-2h7xnM2")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    session = response.data.session
                    loadEntries()
                },
                { e ->
                }
            )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun onAppear() {
        if (session != ""){
            loadEntries()
        }
    }

    fun onRetryClick() {
        loadEntries()
    }

    private fun loadEntries() {
        compositeDisposable.add(
            mainApi.getEntries( request = GetEntriesRequest(session), token = "4x1bIe0-jS-2h7xnM2")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        entriesLiveData.value = response.entryList()
                    },
                    { e ->
                        isRetryShowedLiveEvent.call()

                    }
                )
        )
    }
}