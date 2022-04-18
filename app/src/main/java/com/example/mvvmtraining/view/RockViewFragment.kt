package com.example.mvvmtraining.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mvvmtraining.R
import com.example.mvvmtraining.adapters.RockAdapter
import com.example.mvvmtraining.model.remote.SongResponse
import com.example.mvvmtraining.model.remote.SongService
import com.example.mvvmtraining.utils.CATEGORY_ROCK
import retrofit2.Call
import retrofit2.Response

class RockViewFragment : Fragment() {
    private lateinit var rockView: RecyclerView
    private lateinit var rockRefresh: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.rock_fragment_list_layout, container, false)
        initView(view)
        rockRefresh = view.findViewById(R.id.rock_swipe_refresh)
        rockRefresh.setOnRefreshListener {
            connectToRetrofit()
            Log.d("refresh", "refresh success")
            Handler(Looper.getMainLooper()).postDelayed({
                rockRefresh.isRefreshing = false
            }, 1000)
        }
        return view
    }

    private fun initView(view: View) {
        rockView = view.findViewById(R.id.rock_song_list)
        connectToRetrofit()
    }

    private fun connectToRetrofit() {
        SongService.initRetrofit().
        getSongs(CATEGORY_ROCK,"music","song","50").
        enqueue(object : retrofit2.Callback<SongResponse>{
            override fun onFailure(call: Call<SongResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<SongResponse>,
                                    response:
                                    Response<SongResponse>) {
                if (response.isSuccessful) {
                    val songResponse = response.body()
                    Log.d("songResponse", songResponse.toString())
                    updateAdapter(songResponse)
                }
            }

        })
    }

    private fun updateAdapter(body: SongResponse?) {
        body?.let{
            rockView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())
            rockView.adapter = RockAdapter(it.results)
        }//?: showError("No response from server")
    }
}