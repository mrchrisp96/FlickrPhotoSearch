package com.example.photosearchairsidemobile

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    lateinit var photoList: ArrayList<Photo>
    lateinit var photoGridView: GridView
    lateinit var photoAdapter: PhotoAdapter
    lateinit var photoLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        photoGridView = findViewById(R.id.photoGridView)
        photoList = ArrayList()

        val photosViewModel: PhotoViewModel by viewModels()

        photosViewModel.liveData.observe(this,
            Observer<List<Photo>>{ list ->
                with(photoAdapter) {
                    photoList.clear()
                    photoList.addAll(list)
                    notifyDataSetChanged()
                }
            })

        photoAdapter = PhotoAdapter(photoList!!)
        photoGridView.adapter = photoAdapter

        photoGridView.setOnItemClickListener { parent, view, position, id ->
            val photo = photoList[position]
            val intent = Intent(this, PhotoDisplay::class.java).apply {
                intent.putExtra("photoURL", photo.photoURL)
                intent.putExtra("photoTitle", photo.photoTitle)
                intent.putExtra("photoID", photo.photoID)
            }
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        val searchBarPhoto = menu.findItem(R.id.photoSearch)
        if(searchBarPhoto != null) {
            val searchView = searchBarPhoto.actionView as SearchView
            searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if(query!!.isEmpty()) {
                        // Check if user entered a valid text to search
                        Toast.makeText(baseContext, "Please enter a search query...", Toast.LENGTH_SHORT).show()
                        return false
                    }
                    val connectivityManager = baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
                    if(activeNetwork == null || !activeNetwork.isConnected) {
                        // if disconnected from internet...
                        Toast.makeText(baseContext, "Disconnected from internet...", Toast.LENGTH_SHORT).show()
                    } else {

                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }

        return super.onCreateOptionsMenu(menu)
    }

    fun showProgressDialog() {

    }

    //
//    private fun setDataList() : ArrayList<PhotoObject> (
//
//    )

}
