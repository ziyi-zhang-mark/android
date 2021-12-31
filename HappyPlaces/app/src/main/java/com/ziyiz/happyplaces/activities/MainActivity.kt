package com.ziyiz.happyplaces.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ziyiz.happyplaces.R
import com.ziyiz.happyplaces.adapters.HappyPlacesAdapter
import com.ziyiz.happyplaces.database.DatabaseHandler
import com.ziyiz.happyplaces.models.HappyPlaceModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val ADD_PLACE_ACTIVITY_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabAddPlace.setOnClickListener {
            val intent = Intent(this, AddHappyPlaceActivity::class.java)
            startActivityForResult(intent, ADD_PLACE_ACTIVITY_REQUEST_CODE)
        }
        getHappyPlaceListFromLocalDB()
    }

    private fun setupHappyPlacesRecyclerView(placeList: ArrayList<HappyPlaceModel>) {
        rv_happy_place_list.layoutManager = LinearLayoutManager(this)
        rv_happy_place_list.setHasFixedSize(true)
        rv_happy_place_list.adapter = HappyPlacesAdapter(this, placeList)
    }

    private fun getHappyPlaceListFromLocalDB() {
        val dbHandler = DatabaseHandler(this)
        val placeList: ArrayList<HappyPlaceModel> = dbHandler.getHappyPlacesList()

        if (placeList.size > 0) {
            rv_happy_place_list.visibility = View.VISIBLE
            tv_no_records_available.visibility = View.GONE
            setupHappyPlacesRecyclerView(placeList)
        } else {
            rv_happy_place_list.visibility = View.GONE
            tv_no_records_available.visibility = View.VISIBLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_PLACE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                getHappyPlaceListFromLocalDB()
            } else {
                Log.i("MainActivity", "Cancelled or back button pressed")
            }
        }
    }
}