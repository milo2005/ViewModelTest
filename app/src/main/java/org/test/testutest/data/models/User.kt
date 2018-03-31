package org.test.testutest.data.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.test.testutest.util.ExcludeField

@Entity
class User(@PrimaryKey @ExcludeField val id: Long,
           @SerializedName("_id") val idRemote: String,
           name: String,
           role: String)