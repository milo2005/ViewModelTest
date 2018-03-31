package org.test.testutest.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Maybe
import io.reactivex.Single
import org.test.testutest.data.models.User

@Dao
interface UserDao{

    @Insert
    fun insert(user:User)

    @Delete
    fun delete(user:User)

    @Query("SELECT * from User limit 1")
    fun getUser(): Maybe<User>

}