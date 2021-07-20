package io.rusha.bnet_test

import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body

interface MainApi {
    fun getEntries(): Single<GetEntriesResponse>
    fun addEntries(@Body request: AddEntryRequest): Single<Unit>
}