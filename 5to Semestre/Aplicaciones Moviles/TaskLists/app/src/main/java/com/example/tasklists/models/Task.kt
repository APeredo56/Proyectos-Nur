package com.example.tasklists.models

import androidx.recyclerview.widget.RecyclerView

data class Task(
    var title : String,
    var innerTaskList : RecyclerView
){

}