package org.test.testutest.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import org.test.testutest.data.db.dao.UserDao
import org.test.testutest.data.models.User

@Database(version = 1, entities = [User::class])
abstract class AppDataBase : RoomDatabase() {


    abstract fun userDao(): UserDao

    companion object {

        lateinit var db: AppDataBase

        fun init(context: Context) {
            db = Room.databaseBuilder(context, AppDataBase::class.java, "test.db")
                    .build()
        }
    }
}