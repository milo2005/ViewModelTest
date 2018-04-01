package org.test.testutest.data.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.test.testutest.util.ExcludeField

@Entity
class User() {
    @PrimaryKey
    @ExcludeField
    var id: Long? = null
    @SerializedName("_id")
    lateinit var idRemote: String
    lateinit var name: String
    lateinit var role: String

    @Ignore
    constructor(idRemote: String, name: String, role: String) : this() {
        this.name = name
        this.idRemote = idRemote
        this.role = role
    }
}