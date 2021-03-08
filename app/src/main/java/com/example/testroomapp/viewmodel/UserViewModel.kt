package com.example.testroomapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.testroomapp.data.User
import com.example.testroomapp.database.UserDatabase
import com.example.testroomapp.repo.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// User View Model
class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    // first methods that runs when a object is created
    init {
        // getting the database instance and then getting the UserDao object
        val userDao = UserDatabase.getDatabase(application).userDao()
        // passing the UserDao object to UserRepository
        repository = UserRepository(userDao)
        // Reading all the data
        // Note: We have also implemented an observe in the AddFragment so if there are any changes here it will update that recyclerview
        readAllData = repository.readAllData
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteUser(user)
        }
    }

    fun deleteAllUsers(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllUsers()
        }
    }
}