package com.example.testroomapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.testroomapp.data.User

// Interface that has all the database logic
// All CURD operations are declared here
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) // if same user then ignore it
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>
}