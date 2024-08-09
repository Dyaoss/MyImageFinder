package com.example.myimagefinder.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myimagefinder.Adapter.ImageListAdapter
import com.example.myimagefinder.R
import com.example.myimagefinder.ViewModel.ImageViewModel
import com.example.myimagefinder.databinding.FragmentMypickBinding


class MypickFragment : Fragment() {
    private val ImageViewModel: ImageViewModel by viewModels()
    private lateinit var adapter: ImageListAdapter

    private var _binding: FragmentMypickBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMypickBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pickedData = ImageViewModel.getPickedDataList()
        adapter = ImageListAdapter(pickedData ?: mutableListOf())
        binding.mypickRecyclerview.adapter = adapter
        binding.mypickRecyclerview.layoutManager = GridLayoutManager(context, 2)

        ImageViewModel.liveDataList.observe(viewLifecycleOwner, Observer {
            adapter.setData(it.toMutableList())
        })

        adapter.itemClick = object : ImageListAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {

                if (position < pickedData.size) {
                    pickedData[position].isLiked = false
                    pickedData.removeAt(position)
                    ImageViewModel.pickedDataList(pickedData)
                    adapter.notifyDataSetChanged()
                }
            }

        }
    }
}