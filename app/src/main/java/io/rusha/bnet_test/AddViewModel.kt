package io.rusha.bnet_test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class AddViewModel : ViewModel() {
    val isRetryShowedLiveEvent = SingleLiveEvent<Unit>()
    val isClosedLiveData = MutableLiveData <Boolean>()
    private val mainApi = ServiceLocator.getMainApi()
    private var nameEntry = ""

    fun onCancelClick() {
        isClosedLiveData.value = true
    }

    fun onAddClick(nameEntry : String) {
        this.nameEntry = nameEntry
        addEntry()
    }

    fun onRetryClick() {
        addEntry()
    }

    private fun addEntry() {
        mainApi.addEntries(AddEntryRequest(body = nameEntry), token = "4x1bIe0-jS-2h7xnM2")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _ ->
                    isClosedLiveData.value = true
                },
                { e ->

                }
            )
    }

}