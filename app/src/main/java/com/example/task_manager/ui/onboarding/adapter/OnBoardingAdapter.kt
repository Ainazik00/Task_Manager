package com.example.task_manager.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.task_manager.databinding.ItemOnboardingBinding
import com.example.task_manager.model.OnBoarding
import com.example.task_manager.utils.loadImage

class OnBoardingAdapter(private val onClick: () -> Unit) :
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {
    private val list = arrayListOf(
        OnBoarding("https://cdn-icons-png.flaticon.com/512/6119/6119641.png", "Development of New Functionality", "Working on new functionality to enhance user experience. Involves design, coding, and testing."),
        OnBoarding("https://cdn-icons-png.flaticon.com/512/6119/6119740.png", "Preparation of Documents for Meeting", "Prepare all necessary documents and materials for the upcoming leadership meeting."),
        OnBoarding("https://cdn-icons-png.flaticon.com/512/6119/6119806.png", "Prepare Report", "Gather data and prepare a report for the weekly meeting"),
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(list.get(position))
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) :
        ViewHolder(binding.root) {
        fun bind(onBoarding: OnBoarding) = with(binding) {
            tvTitle.text = onBoarding.title
            tvDesc.text = onBoarding.desc
            ivBoard.loadImage(onBoarding.image)
            skip.setOnClickListener {
                onClick()
            }
            btnStart.setOnClickListener {
                onClick()
            }
            skip.isInvisible = adapterPosition != list.lastIndex
            btnStart.isVisible = adapterPosition == list.lastIndex
        }
    }
}