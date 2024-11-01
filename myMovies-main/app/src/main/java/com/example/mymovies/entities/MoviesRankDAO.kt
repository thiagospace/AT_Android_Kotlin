package com.example.mymovies.entities

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MoviesRankDAO {
    @Insert
    fun add(moviesRank: MoviesRank)

    @Delete
    fun delete(moviesRank: MoviesRank)

    @Query("SELECT * FROM moviesRank")
    fun getAllRank() : List<MoviesRank>

}