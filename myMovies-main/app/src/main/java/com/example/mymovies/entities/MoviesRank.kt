package com.example.mymovies.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MoviesRank (

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "idMovie")
    var idMovie: Int? = null,

    @ColumnInfo(name = "MovieRank")
    var movieRank: Int? = null,

    @ColumnInfo(name = "Favorite")
    var favorite: Int = 0

)