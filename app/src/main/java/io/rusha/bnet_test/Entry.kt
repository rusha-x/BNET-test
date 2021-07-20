package io.rusha.bnet_test

import java.util.*

class Entry(val id: String, val body: String, val da: String, val dm: String) {

    val creationDate = Date(da.toLong() * 1000)
    val modificationDate = Date(dm.toLong() * 1000)
}
