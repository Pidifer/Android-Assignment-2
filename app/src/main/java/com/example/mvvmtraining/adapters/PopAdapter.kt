package com.example.mvvmtraining.adapters

import android.app.AlertDialog
import android.content.DialogInterface
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtraining.R
import com.example.mvvmtraining.model.remote.Song
import com.squareup.picasso.Picasso
import java.io.IOException


class PopAdapter(private val dataSet: List<Song>) :
    RecyclerView.Adapter<PopAdapter.PopViewHolder>() {


    class PopViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private var player: MediaPlayer? = null
        private val cdPic: ImageView =
            view.findViewById(R.id.cd_picture)
        private val albumTitle: TextView =
            view.findViewById(R.id.album_name)
        private val artistName: TextView =
            view.findViewById(R.id.band_name)
        private val price: TextView =
            view.findViewById(R.id.price)


        fun onBind(dataItem: Song) {
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
                builder.setPositiveButton("Yes"){ _: DialogInterface, _ -> player?.stop() }
                builder.setCancelable(false)
                builder.show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopViewHolder {
        return PopViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.song_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PopViewHolder, position: Int) {
        holder.onBind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}

