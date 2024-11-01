package com.example.mymovies.entities

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MoviesDAO {
    @Insert
    fun add(movies: Movies)

    @Delete
    fun delete(movies: Movies)

    @Query("SELECT * FROM Movies")
    fun getAllMovies(): List<Movies>



}