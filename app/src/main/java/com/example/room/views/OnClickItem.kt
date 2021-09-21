package com.example.room.views

import com.example.room.Task

interface OnClickItem {
    //for edit the task
    fun onEditClicked(task: Task)
    //for delete the task
    fun onDeleteClicked(task: Task)
}