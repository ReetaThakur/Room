package com.example.room.repository

import androidx.lifecycle.LiveData
import com.example.room.Task
import com.example.room.TaskappDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskRepo(private val taskDAO: TaskappDAO) {

    fun addTaskToRoom(task: Task){
        CoroutineScope(Dispatchers.IO).launch {
            taskDAO.addTask(task)
        }
    }
    fun getAllTasks():LiveData<List<Task>>{
        return taskDAO.getTasks()
    }
    fun updateTask(task: Task){
        CoroutineScope(Dispatchers.IO).launch {
            taskDAO.updateTask(task)
        }
    }
    fun deleteTask(task: Task){
        CoroutineScope(Dispatchers.IO).launch {
            taskDAO.delete(task)
        }

    }
}