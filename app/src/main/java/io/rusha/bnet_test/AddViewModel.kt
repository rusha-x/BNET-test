package io.rusha.bnet_test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class AddViewModel : ViewModel() {
    private val mainApi = ServiceLocator.getMainApi()
    val isClosedLiveData = MutableLiveData <Boolean>()

    fun onCancelClick() {
        isClosedLiveData.value = true
    }

    fun onAddClick(nameEntry : String) {
        mainApi.addEntries(AddEntryRequest(body = nameEntry))
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