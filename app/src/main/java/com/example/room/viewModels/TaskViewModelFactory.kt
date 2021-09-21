package com.example.room.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.room.repository.TaskRepo

class TaskViewModelFactory(val repo: TaskRepo):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return TaskViewModel(repo) as T
    }

}