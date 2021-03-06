package com.example.testroomapp.repo

import androidx.lifecycle.LiveData
import com.example.testroomapp.dao.UserDao
import com.example.testroomapp.data.User

// This class implements the methods from the UserDao interface
// Note: All Suspend functions must be called in coroutines
class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUsers(){
        userDao.deleteAllUsers()
    }
}