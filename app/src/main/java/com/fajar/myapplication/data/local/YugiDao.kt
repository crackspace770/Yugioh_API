package com.fajar.myapplication.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface YugiDao {

    @Insert
    fun insert(favorite: Yugi)

    @Query("SELECT count(*) FROM favorite WHERE favorite.id = :id")
    fun check(id: String): Int

    @Query("DELETE FROM favorite WHERE favorite.id = :id")
    fun delete(id: String): Int

    @Query("SELECT * FROM favorite")
    fun getAllFavorite(): LiveData<List<Yugi>>
}