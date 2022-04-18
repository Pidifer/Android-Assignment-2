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
import com.example.mvvmtraining.adapters.PopAdapter
import com.example.mvvmtraining.model.remote.SongResponse
import com.example.mvvmtraining.model.remote.SongService
import com.example.mvvmtraining.utils.CATEGORY_POP
import retrofit2.Call
import retrofit2.Response

class PopViewFragment : Fragment() {
    private lateinit var popView: RecyclerView
    private lateinit var popRefresh: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.pop_fragment_list_layout, container, false)
        initView(view)
        popRefresh.setOnRefreshListener {
            connectToRetrofit()
            Log.d("refresh", "refresh success")
            Handler(Looper.getMainLooper()).postDelayed({
                popRefresh.isRefreshing = false
            }, 1000)
        }
        return view


    }


    private fun initView(view: View) {
        popView = view.findViewById(R.id.pop_song_list)
        connectToRetrofit()
        popRefresh = view.findViewById(R.id.pop_swipe_refresh)
    }

    private fun connectToRetrofit() {
        SongService.initRetrofit().getSongs(CATEGORY_POP, "music", "song", "50")
            .enqueue(object : retrofit2.Callback<SongResponse> {
                override fun onFailure(call: Call<SongResponse>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<SongResponse>,
                    response:
                    Response<SongResponse>
                ) {
                    if (response.isSuccessful) {
                        val songResponse = response.body()
                        Log.d("songResponse", songResponse.toString())
                        updateAdapter(songResponse)
                    }
                }

            })
    }

    private fun updateAdapter(body: SongResponse?) {
        body?.let {
            popView.layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(requireContext())
            popView.adapter = PopAdapter(it.results)
        }//?: showError("No response from server")
    }

}