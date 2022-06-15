package com.ziyiz.happyplaces.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ziyiz.happyplaces.R
import com.ziyiz.happyplaces.activities.AddHappyPlaceActivity
import com.ziyiz.happyplaces.activities.MainActivity
import com.ziyiz.happyplaces.database.DatabaseHandler
import com.ziyiz.happyplaces.models.HappyPlaceModel
import kotlinx.android.synthetic.main.item_happy_place.view.*

class HappyPlacesAdapter(
    private val context: Context,
    private val list: ArrayList<HappyPlaceModel>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HappyPlaceViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_happy_place, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val place = list.get(position)

        if (holder is HappyPlaceViewHolder) {
            holder.itemView.iv_place_image.setImageURI(Uri.parse(place.image))
            holder.itemView.tvTitle.text = place.title
            holder.itemView.tvDescription.text = place.description

            holder.itemView.setOnClickListener {
                onClickListener?.onClick(position, place)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    fun notifyEditItem(activity: Activity, position: Int, requestCode: Int) {
        val intent = Intent(context, AddHappyPlaceActivity::class.java)
        intent.putExtra(MainActivity.EXTRA_PLACE_DETAILS, list[position])
        activity.startActivityForResult(intent, requestCode)
        notifyItemChanged(position)
    }

    fun removeAt(position: Int) {
        val dbHandler = DatabaseHandler(context)
        val isDeleted = dbHandler.deleteHappyPlace(list[position])
        if (isDeleted > 0) {
            list.removeAt(position)
//            notifyItemRemoved(position) // notify the adapter
        }
    }

    interface OnClickListener {
        fun onClick(position: Int, model: HappyPlaceModel)
    }

    class HappyPlaceViewHolder(view: View): RecyclerView.ViewHolder(view)
}