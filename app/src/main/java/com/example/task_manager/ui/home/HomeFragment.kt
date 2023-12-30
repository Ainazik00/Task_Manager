package com.example.task_manager.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
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
    private val adapter = TaskAdapter(this::onLongClickListener, this::onUpdateClick)

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
        recyclerView.adapter = adapter
        setData()
        fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    private fun navigateTask() {
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
        setData()
    }

    private fun onLongClickListener(task: Task) {
        val alert = AlertDialog.Builder(requireContext())
        alert.setTitle(getString(R.string.delete))
        alert.setMessage(getString(R.string.delete_it))
        alert.setPositiveButton(getString(R.string.yes)) { dialog, _ ->
            App.db.taskDao().delete(task)
            setData()
        }
        alert.setNegativeButton(getString(R.string.no)) { dialog, _ ->
            dialog.cancel()
        }
        alert.create().show()
    }

    private fun setData() {
        val tasks = App.db.taskDao().getAll()
        adapter.addTasks(tasks)
    }

    private fun onUpdateClick(task: Task) {
        findNavController().navigate(R.id.taskFragment, bundleOf(TASK_EDIT_KEY to task))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TASK_EDIT_KEY = "task.edit.key"
    }

}