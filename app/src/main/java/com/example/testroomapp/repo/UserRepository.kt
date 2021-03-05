package com.example.testroomapp.repo

import androidx.lifecycle.LiveData
import com.example.testroomapp.dao.UserDao
import com.example.testroomapp.data.User

class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}