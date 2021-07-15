package io.rusha.bnet_test

class GetEntriesResponse(val data: List<List<Entry>>) {
    fun entryList(): List<Entry> {
        return data.firstOrNull() ?: emptyList()
    }
}