package com.example.myimagefinder.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myimagefinder.Adapter.ImageListAdapter
import com.example.myimagefinder.Retrofit.ImageResponse
import com.example.myimagefinder.Retrofit.KakaoAPI
import com.example.myimagefinder.Retrofit.KakaoImageData
import com.example.myimagefinder.Retrofit.RetrofitClient
import com.example.myimagefinder.databinding.FragmentImageBinding
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query


class ImageFragment : Fragment() {

    private var _binding: FragmentImageBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ImageListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageRecyclerview.layoutManager = GridLayoutManager(context, 2)
        adapter = ImageListAdapter(mutableListOf())
        binding.imageRecyclerview.adapter = adapter

        binding.btnSearch.setOnClickListener {
            val searching = binding.etSearch.text.toString()
            searchKakaoAPI(searching)
        }
    }

    private fun searchKakaoAPI(query: String) {
        val call = RetrofitClient.api.getImgData(query)

        call.enqueue(object : Callback<ImageResponse> {
            override fun onResponse(
                call: Call<ImageResponse>,
                response: Response<ImageResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { searchResponse ->
                        adapter.itemData.clear()
                        adapter.itemData.addAll(searchResponse.documents)
                        adapter.notifyDataSetChanged()
                    }

                } else {
                    Log.e("SearchFragment", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
                Log.e("SearchFragment", "Request failed", t)
            }
        })
    }

}




