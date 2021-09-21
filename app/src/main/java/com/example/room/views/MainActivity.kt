package com.example.room.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.*
import com.example.room.repository.TaskRepo
import com.example.room.viewModels.TaskViewModel
import com.example.room.viewModels.TaskViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity   : AppCompatActivity(), OnClickItem {

    lateinit var taskAdapter: TasksAdapter
    private val tasksList = mutableListOf<Task>()

    lateinit var roomDb: TaskRoomDatabase
    lateinit var taskDao: TaskappDAO

    lateinit var viewModel:TaskViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        roomDb= TaskRoomDatabase.getDatabaseObject(this)
        taskDao=roomDb.getTaskDAO()
        val repo=TaskRepo(taskDao)
        val viewModelFactory=TaskViewModelFactory(repo)

        viewModel=ViewModelProviders.of(this,viewModelFactory)
            .get(TaskViewModel::class.java)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val newTask= Task("Dummy title","Dummy desc")
            viewModel.addTask(newTask)


        }

        taskAdapter = TasksAdapter(this, tasksList,this)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = taskAdapter

        viewModel.getTasks().observe(this, Observer {
            tasksList.clear()
            tasksList.addAll(it)
            taskAdapter.notifyDataSetChanged()
        })


    }
    fun updateUI(){
        tasksList.clear()
        taskAdapter.notifyDataSetChanged()
    }

    override fun onEditClicked(task: Task) {
        val newTitle="New Expense"
        val newDesc="New price"
        task.title=newTitle
        task.desc=newDesc

       viewModel.update(task)

    }

    override fun onDeleteClicked(task: Task) {
     viewModel.delete(task)
    }
}
