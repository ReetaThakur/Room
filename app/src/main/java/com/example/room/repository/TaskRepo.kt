package com.example.room.repository

import androidx.lifecycle.LiveData
import com.example.room.Task
import com.example.room.TaskappDAO
import com.example.room.models.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.create
import java.lang.Exception
import javax.inject.Inject

class TaskRepo @Inject constructor( val taskDAO: TaskappDAO) {

    private val api:TaskAPI=Network.getRetrofit()!!.create(TaskAPI::class.java)
    private val responseHandler:RetrofitNetworkRequestHandler.ResponseHandler=
        RetrofitNetworkRequestHandler.ResponseHandler()

    private val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MGE0YmI3OTAzMjdlN2MwNmE2MTk1ODYiLCJpYXQiOjE2MzIxMzg2ODR9.cTxpYQrTfvramIOSPih6b1hJO_x1G-V2GmaRnTYSjU0"

    suspend fun login(loginRequestModel: LoginRequestModel):RetrofitNetworkRequestHandler.Resource<LoginResponse> {
        return try {
            val response = api.login(loginRequestModel)
            responseHandler.handleSuccess(response)
        }catch (e:Exception){
            responseHandler.handleException(e)
        }
    }

     fun getRemoteTasks() {
         CoroutineScope(Dispatchers.IO).launch {
             val response = api.getTasksFromAPI(token)
             saveTODB(response)
         }
     }

    private fun saveTODB(response: GetTasksResponseModel) {
        val listOFTasks=ArrayList<Task>()
        response.forEach {
            val newTask=Task(it.title,it.description)
            taskDAO.addTask(newTask)
            listOFTasks.add(newTask)
        }
        taskDAO.addTasks(listOFTasks)
    }

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