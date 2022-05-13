package com.example.modul6lesson3database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
open class Note{
    @PrimaryKey
    var id : Long? = null
    var time : String? = null
    var note : String? = null

    constructor(id: Long?, time: String?, note: String?) : super() {
        this.id = id
        this.time = time
        this.note = note
    }

    constructor(){}
}
