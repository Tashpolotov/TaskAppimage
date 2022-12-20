package com.example.taskapp.ui.Board

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentOnBoardBinding


class OnBoardFragment : Fragment() {
    lateinit var binding: FragmentOnBoardBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentOnBoardBinding.inflate(LayoutInflater.from(context), container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BoardAdapter(childFragmentManager, this:: onSkipClick, this:: onNextClick)
        binding.vpBoard.adapter = adapter
        binding.dotsIndicator.attachTo(binding.vpBoard)
    }

    private fun onSkipClick(){

        binding.vpBoard.currentItem = 2
    }

    private fun onNextClick(){

        binding.vpBoard.currentItem += 1
    }
}