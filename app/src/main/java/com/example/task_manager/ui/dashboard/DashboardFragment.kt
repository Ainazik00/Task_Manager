package com.example.task_manager.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.task_manager.R
import com.example.task_manager.databinding.FragmentDashboardBinding
import com.example.task_manager.model.Film
import com.example.task_manager.utils.showToast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            val data = Film(binding.etFilm.text.toString(), binding.etDirector.text.toString())
            FirebaseAuth.getInstance().currentUser?.uid?.let { it1 ->
                db.collection(it1)
                    .add(data)
                    .addOnSuccessListener {
                        showToast(getString(R.string.success_msg))
                    }
                    .addOnFailureListener {
                        showToast(it.message.toString())
                    }
            }
        }
    }

}