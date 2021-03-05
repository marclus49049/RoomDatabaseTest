package com.example.testroomapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testroomapp.data.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) // if same user then ignore it
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>
}