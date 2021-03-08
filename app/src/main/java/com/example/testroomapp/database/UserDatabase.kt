package com.example.testroomapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testroomapp.dao.UserDao
import com.example.testroomapp.data.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    // Will just return UserDao Object
    abstract fun userDao(): UserDao

    // Basically STATIC for Kotlin
    companion object{
        // Volatile tells the compiler that this objects will have a lot of IO operations (i.e. Read and Write)
        // Also this is following SINGLETON design pattern returning the same instance of the class not creating new objects
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            // synchronized this is basically used to sync with other thread so that on one of these
            // functions are run at a single give time basically locking the use of this function
            // until the first thread has finished working with this function
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}