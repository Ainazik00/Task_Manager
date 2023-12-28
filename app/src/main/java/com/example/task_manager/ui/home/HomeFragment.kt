package com.example.task_manager.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.task_manager.App
import com.example.task_manager.R
import com.example.task_manager.databinding.FragmentHomeBinding
import com.example.task_manager.model.Task
import com.example.task_manager.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val adapter = TaskAdapter(this::onLongClickListener)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
        val tasks = App.db.taskDao().getAll()
        recyclerView.adapter = adapter
        adapter.addTasks(tasks)
    }

    private fun onLongClickListener(position: Task): Boolean {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.delete_title))
            .setMessage(getString(R.string.delete_desc, position.title + " " + position.desc))
            .setPositiveButton(getString(R.string.Yes)) { _, _ ->
                App.db.taskDao().delete(position)
                findNavController().navigate(R.id.navigation_home)
            }
            .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                dialog.dismiss()
            }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}