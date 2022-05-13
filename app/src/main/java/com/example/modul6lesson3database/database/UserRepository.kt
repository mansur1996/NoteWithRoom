package com.example.modul6lesson3database.database

import android.app.Application
import com.example.modul6lesson3database.manager.RoomManager
import com.example.modul6lesson3database.model.Note

class UserRepository(application: Application) {
    var userDao: UserDao

    init {
        val db = RoomManager.getDatabase(application)
        userDao = db.userDao()
    }

    fun saveUser(user : Note){
        userDao.saveUser(user)
    }

    fun getUsers() : List<Note> {
        return userDao.getUsers()
    }
}