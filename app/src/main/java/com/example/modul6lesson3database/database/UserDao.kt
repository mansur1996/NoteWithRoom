package com.example.modul6lesson3database.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.modul6lesson3database.model.Note

//Queries
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user : Note) : Long

    @Query("select * from user_table")
    fun getUsers() : List<Note>
}