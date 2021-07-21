package io.rusha.bnet_test

import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface MainApi {
    @POST("./?a=get_entries")
    fun getEntries(
        @Body request: GetEntriesRequest,
        @Header("token") token: String
    ): Single<GetEntriesResponse>

    @POST("./?a=add_entry")
    fun addEntries(@Body request: AddEntryRequest, @Header("token") token: String): Single<Unit>

    @POST("./?a=new_session")
    fun newSession(@Header("token") token: String): Single<NewSessionResponse>
}