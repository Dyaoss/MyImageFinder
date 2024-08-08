package com.example.myimagefinder.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myimagefinder.Adapter.ImageListAdapter
import com.example.myimagefinder.Retrofit.ImageResponse
import com.example.myimagefinder.Retrofit.KakaoImageData
import com.example.myimagefinder.Retrofit.RetrofitClient
import com.example.myimagefinder.databinding.FragmentImageBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ImageFragment : Fragment() {

    private var _binding: FragmentImageBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ImageListAdapter
    private val dataList = mutableListOf<KakaoImageData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ImageListAdapter(dataList)

        binding.btnSearch.setOnClickListener {
            val searching = binding.etSearch.text.toString()
            getSearching(searching)
        }
    }

    private fun getSearching(searching: String) {
        RetrofitClient.api.searchImage(query = searching).enqueue(object : Callback<ImageResponse> {
            override fun onResponse(call: Call<ImageResponse>, response: Response<ImageResponse>) {
                val body = response.body()
                body?.let {
                    dataList.addAll(it.documents)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
            }
        })
    }

}




