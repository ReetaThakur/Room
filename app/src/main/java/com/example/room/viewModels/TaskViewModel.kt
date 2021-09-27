package com.example.room.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.room.Task
import com.example.room.models.LoginResponse
import com.example.room.models.RetrofitNetworkRequestHandler
import com.example.room.repository.LoginRequestModel
import com.example.room.repository.TaskRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


@HiltViewModel
class TaskViewModel @Inject constructor(val repo: TaskRepo): ViewModel()  {

    fun userLogin(loginRequestModel: LoginRequestModel):LiveData<RetrofitNetworkRequestHandler.Resource<LoginResponse>>{
       val liveData= liveData(Dispatchers.IO) {
          val result=  repo.login(loginRequestModel)
           emit(result)
        }
        return liveData
    }

    fun getTasksFromAPI(){
        repo.getRemoteTasks()
    }
    fun addTask(task: Task){
        repo.addTaskToRoom(task)
    }
    fun getTasksFROMDB(): LiveData<List<Task>> {
       return repo.getAllTasks()
    }
    fun  delete(task: Task){
        repo.deleteTask(task)
    }
    fun update(task: Task){
        repo.updateTask(task)
    }

}