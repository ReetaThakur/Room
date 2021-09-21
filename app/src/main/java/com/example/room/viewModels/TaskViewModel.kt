package com.example.room.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.room.Task
import com.example.room.repository.TaskRepo

class TaskViewModel(val repo: TaskRepo): ViewModel()  {
    fun addTask(task: Task){
        repo.addTaskToRoom(task)
    }
    fun getTasks(): LiveData<List<Task>> {
       return repo.getAllTasks()
    }
    fun  delete(task: Task){
        repo.deleteTask(task)
    }
    fun update(task: Task){
        repo.updateTask(task)
    }

}