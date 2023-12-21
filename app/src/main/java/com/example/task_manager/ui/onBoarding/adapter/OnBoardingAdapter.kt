package com.example.task_manager.ui.onBoarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.task_manager.R
import com.example.task_manager.databinding.ItemOnboardingBinding
import com.example.task_manager.model.OnBoarding

class OnBoardingAdapter(private val onClick: () -> Unit) :
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {
    private val list = arrayListOf(
        OnBoarding("https://img.freepik.com/free-vector/time-management-marketers-teamwork-media-planning-media-representation-control-reach-your-client-best-media-plan_335657-23.jpg?w=740&t=st=1703149902~exp=1703150502~hmac=8b079e73fd07ed536e5997baf606f5edfa27d7c62180c2c4bf5150806dd5436d", "Development of New Functionality", "Working on new functionality to enhance user experience. Involves design, coding, and testing."),
        OnBoarding("https://img.freepik.com/free-vector/flat-hand-drawn-time-management-concept_23-2148860802.jpg?w=900&t=st=1703149800~exp=1703150400~hmac=5919c222deb0d2b49c7d30f1b00d9528a84461991d550784c3642ba56bc2c289", "Preparation of Documents for Meeting", "Prepare all necessary documents and materials for the upcoming leadership meeting."),
        OnBoarding("https://seousluga.ru/files/images/izobrazhenie_2022-01-24_152307-1536x1152.png", "Prepare Report", "Gather data and prepare a report for the weekly meeting"),
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