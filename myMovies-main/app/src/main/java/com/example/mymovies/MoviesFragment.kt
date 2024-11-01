package com.example.mymovies

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.mymovies.AppDataBase.AppDataBase
import com.example.mymovies.databinding.FragmentMoviesBinding
import com.example.mymovies.entities.Movies
import com.example.mymovies.entities.MoviesDAO


class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private lateinit var moviesDAO : MoviesDAO
    private var PICK_IMAGE: Int = 1
    private lateinit var imgMovie : ImageView
    //private val RESULT_OK = -1
    //private val profImg: ImageView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movies, container, false)




        val appDatabase = Room.databaseBuilder(
            this.requireActivity(),
            AppDataBase::class.java,
            "movies.db"
        ).allowMainThreadQueries().build()

        moviesDAO = appDatabase.getMoviesDAO()

        val btnImage = view.findViewById<ImageView>(R.id.btn_add_image)
        val btnSave = view.findViewById<Button>(R.id.btn_save)
        imgMovie = view.findViewById<ImageView>(R.id.img_movie)



        btnSave.setOnClickListener{
            var movieName = view.findViewById<TextView>(R.id.edit_name)
            var movieGenre = view.findViewById<RadioGroup>(R.id.radio_genre_group)


            //get radio text by ID
            val id: Int = movieGenre.checkedRadioButtonId
            val genreText: RadioButton = view.findViewById(id)

            if (movieName.text.isEmpty()){
                Toast.makeText(context, "Need a Name", Toast.LENGTH_SHORT).show()
            }else{
                val movie = Movies(0, movieName.text.toString(), genreText.text.toString())

                moviesDAO.add(movie)

                movieName.text = null
            }




        }

        btnImage.setOnClickListener{
            val imageIntent = Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(imageIntent, PICK_IMAGE)
        }


        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK){
            if (requestCode==PICK_IMAGE){

                imgMovie.setImageURI(data!!.data)

            }
        }
    }











    /*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //GETTING IMAGE FROM GALLERY
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && null != data) {
            val selectedImage = data.data
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val cursor: Cursor? = this.requireActivity().contentResolver.query(
                selectedImage!!,
                filePathColumn, null, null, null
            )
            if (cursor != null) {
                cursor.moveToFirst()
            }
            val columnIndex = cursor!!.getColumnIndex(filePathColumn[0])
            val picturePath: String = cursor!!.getString(columnIndex)
            cursor.close()
            profImg!!.setImageBitmap(BitmapFactory.decodeFile(picturePath))
        }
    }
     */
}






