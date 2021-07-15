package io.rusha.bnet_test

import io.reactivex.rxjava3.core.Single

interface MainApi {
    fun getEntries(): Single<GetEntriesResponse>
}