package com.example.task_manager.ui.profile

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.task_manager.R
import com.example.task_manager.data.local.Pref
import com.example.task_manager.data.local.Pref.Companion.GALLERY_REQUEST_CODE
import com.example.task_manager.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val pref: Pref by lazy {
        Pref(requireContext())
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.userNamePr.addTextChangedListener {
            binding.userNamePr.setText(pref.getName())
            pref.saveUserName(binding.userNamePr.text.toString())
        }
        val imageUri = pref.getImage()

        Glide.with(this)
            .load(imageUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.user_profile)
                    .transform(CircleCrop())
            )
            .into(binding.imgProfile)


        binding.imgProfile.setOnClickListener {
            openGalleryForImage()
        }
    }

    private fun openGalleryForImage() {
        val galleryIntent =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
    }
}