package com.example.mymovies.AppDataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mymovies.entities.Movies
import com.example.mymovies.entities.MoviesDAO
import com.example.mymovies.entities.MoviesRank
import com.example.mymovies.entities.MoviesRankDAO

@Database(entities = arrayOf(Movies::class, MoviesRank::class), version = 1)
abstract class AppDataBase : RoomDatabase(){

    abstract fun getMoviesDAO(): MoviesDAO
    abstract fun getMoviesRankDAO(): MoviesRankDAO

}