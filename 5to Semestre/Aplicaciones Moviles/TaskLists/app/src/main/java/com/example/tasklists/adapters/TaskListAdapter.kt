package com.example.tasklists.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklists.R
import com.example.tasklists.models.Task
import com.example.tasklists.models.Objective

class TaskListAdapter (val tasksList: ArrayList<Task>, val taskListener : OnTaskClickListener) :
    RecyclerView.Adapter<TaskListAdapter.TaskListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.task_list_item, parent, false)

        return TaskListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasksList.size
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        val task = tasksList[position]
        holder.lblTaskTitle.text = task.title
        holder.innerTaskList.adapter = tasksList.get(position).innerTaskList.adapter
        holder.innerTaskList.layoutManager = LinearLayoutManager(holder.innerTaskList.context)

        holder.itemView.setOnClickListener { taskListener.onTaskClick(task) }
        holder.innerTaskList.setOnClickListener { taskListener.onTaskClick(task) }
        holder.btnDelTask.setOnClickListener { taskListener.onBtnDeleteClick(task) }
    }

    fun addTask(task : Task){
        tasksList.add(0, task)
        notifyItemRangeChanged(0,tasksList.size)
    }

    fun deleteTask(index : Int){
        tasksList.removeAt(index)
        notifyDataSetChanged()
    }

    fun replaceTaskDetails(title : String, objectivesList: ArrayList<Objective>, index : Int){
        tasksList.get(index).title = title
        val auxTaskListAdapter = tasksList.get(index).innerTaskList.adapter as ObjectiveListAdapter
        auxTaskListAdapter.objectivesList = objectivesList
        notifyItemRangeChanged(index,1)
    }

    class TaskListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lblTaskTitle: TextView = itemView.findViewById(R.id.lblTaskTitle)
        val innerTaskList : RecyclerView = itemView.findViewById(R.id.ObjectiveList)
        val btnDelTask : ImageButton = itemView.findViewById(R.id.btnDelTask)
    }

    interface OnTaskClickListener{
        fun onTaskClick(task: Task)
        fun onBtnDeleteClick(task: Task)
    }
}