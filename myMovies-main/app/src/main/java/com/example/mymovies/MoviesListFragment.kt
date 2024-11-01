package com.example.mymovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.mymovies.AppDataBase.AppDataBase
import com.example.mymovies.entities.MoviesDAO
import com.example.mymovies.entities.MoviesRankDAO


class MoviesListFragment : Fragment() {


    private lateinit var moviesDAO: MoviesDAO
    private lateinit var moviesRankDAO: MoviesRankDAO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)

        val appDataBase = Room.databaseBuilder(
            this.requireActivity(),
            AppDataBase::class.java,
            "movies.db"
        ).allowMainThreadQueries().build()
        moviesDAO = appDataBase.getMoviesDAO()
        moviesRankDAO = appDataBase.getMoviesRankDAO()

        

        return view
    }

    override fun onResume() {
        super.onResume()
        this.loadMovies()
    }



    private fun loadMovies(){
        val movies = moviesDAO.getAllMovies()
        val moviesRank = moviesRankDAO.getAllRank()

        val moviesList = ArrayList<String>()


        for (movie in movies){

            for (rank in moviesRank){
                var mymovie = "Movie: ${movie.name} " +
                        "Genre: ${movie.genre} " +
                        "Rate: ${rank.movieRank}"

                moviesList.add(mymovie)
            }


        }
        val list = this.requireView().findViewById<ListView>(R.id.movies_list)
        val adapter = ArrayAdapter<String>(this.requireActivity(),android.R.layout.simple_list_item_1, moviesList)

        list.adapter = adapter
    }

}