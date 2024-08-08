package com.example.myimagefinder.Fragment

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myimagefinder.Adapter.ImageListAdapter
import com.example.myimagefinder.Retrofit.ImageResponse
import com.example.myimagefinder.Retrofit.RetrofitClient
import com.example.myimagefinder.databinding.FragmentImageBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

        binding.etSearch.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val searching = binding.etSearch.text.toString()
                searchKakaoAPI(searching)

                hideKeyBoard()//hide
                return@OnKeyListener true
            }
            false
        })


        binding.btnSearch.setOnClickListener {
            val searching = binding.etSearch.text.toString()
            searchKakaoAPI(searching)
            hideKeyBoard()
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

    private fun hideKeyBoard() {
        val input: InputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        input.hideSoftInputFromWindow(view?.windowToken, 0)
    }

}




