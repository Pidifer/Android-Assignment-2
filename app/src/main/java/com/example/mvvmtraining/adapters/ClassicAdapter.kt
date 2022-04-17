package com.example.mvvmtraining.adapters

import android.app.AlertDialog
import android.content.DialogInterface
import android.media.MediaPlayer
import android.view.View

import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtraining.model.remote.Song
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.mvvmtraining.R
import com.squareup.picasso.Picasso
import java.io.IOException

class ClassicAdapter(private val dataSet: List<Song>) :
    RecyclerView.Adapter<ClassicAdapter.ClassicViewHolder>() {

    class ClassicViewHolder(private val view: View):RecyclerView.ViewHolder(view){
        var player: MediaPlayer? = null
        private val cdPic: ImageView =
            view.findViewById(R.id.cd_picture)
        private val albumTitle: TextView =
            view.findViewById(R.id.album_name)
        private val artistName: TextView =
            view.findViewById(R.id.band_name)
        private val price: TextView =
            view.findViewById(R.id.price)

        fun onBind(dataItem: Song){
            val builder = AlertDialog.Builder(view.context)
            player = MediaPlayer()
            albumTitle.text = dataItem.collectionName
            artistName.text = dataItem.artistName
            price.text = dataItem.trackPrice.toString()
            Picasso.get().load(dataItem.artworkUrl100).into(cdPic)

            view.setOnClickListener {
                try {
                    player?.setDataSource(dataItem.previewUrl)
                    player?.prepare()
                    player?.start()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                builder.setTitle("Playing Music")
                builder.setMessage("Do you want to stop the music?")
                builder.setPositiveButton("Yes",{ dialogInterface: DialogInterface, i -> player?.stop() })
                builder.setCancelable(false)
                builder.show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassicViewHolder {
        return ClassicViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.song_item_layout,
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: ClassicViewHolder, position: Int) {
        holder.onBind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}
