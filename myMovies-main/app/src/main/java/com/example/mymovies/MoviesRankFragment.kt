package com.example.mymovies
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.mymovies.AppDataBase.AppDataBase
import com.example.mymovies.entities.MoviesDAO
import com.example.mymovies.entities.MoviesRank
import com.example.mymovies.entities.MoviesRankDAO


class MoviesRankFragment : Fragment() {


    private lateinit var moviesDAO : MoviesDAO
    private lateinit var moviesRankDAO : MoviesRankDAO
    private var movieFavorite = 0


    private lateinit var moviesId: ArrayList<Int>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movies_rank, container, false)


        val appDatabase = Room.databaseBuilder(
            this.requireActivity(),
            AppDataBase::class.java,
            "movies.db"
        ).allowMainThreadQueries().build()

        moviesDAO = appDatabase.getMoviesDAO()
        moviesRankDAO = appDatabase.getMoviesRankDAO()

        var movieRank = view.findViewById<TextView>(R.id.edit_rank)




        val getSeekBar = view.findViewById<SeekBar>(R.id.seek_rank)
        getSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                movieRank.text = progress.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })


        val btnSave = view.findViewById<Button>(R.id.btn_save_rank)
        btnSave.setOnClickListener{
            var spnMovies = view.findViewById<Spinner>(R.id.spn_movies)
            var movieFav = view.findViewById<CheckBox>(R.id.fav_box)
            var rank = movieRank.text.toString().toInt()

            if (movieFav.isChecked){
                movieFavorite = 1
            }else{
                movieFavorite = 0
            }

            val myRank = MoviesRank(null, moviesId.get(spnMovies.selectedItemPosition),rank, movieFavorite)

            moviesRankDAO.add(myRank)


        }


        return view
    }


    override fun onResume() {
        super.onResume()
        this.updateMovie()
    }

    private fun updateMovie(){
        val movies = moviesDAO.getAllMovies()
        val titles = ArrayList<String>()
        moviesId = ArrayList()
        for(movie in movies){
            titles.add(movie.name)
            moviesId.add(movie.id!!)

        }
        val spnMovies = this.requireView().findViewById<Spinner>(R.id.spn_movies)
        val adapter = ArrayAdapter<String>(this.requireActivity(), android.R.layout.simple_spinner_dropdown_item, titles)
        spnMovies.adapter = adapter

    }



}










