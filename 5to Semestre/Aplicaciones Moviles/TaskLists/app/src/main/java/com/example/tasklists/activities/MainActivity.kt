package com.example.tasklists.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklists.R
import com.example.tasklists.adapters.ObjectiveListAdapter
import com.example.tasklists.adapters.TaskListAdapter
import com.example.tasklists.models.Task
import com.example.tasklists.models.Objective
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), TaskListAdapter.OnTaskClickListener,
    ObjectiveListAdapter.OnObjectiveClickListener {

    private lateinit var taskList: RecyclerView
    private lateinit var fabCreate: FloatingActionButton
    private var openedTaskPos: Int = -1
    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode != Activity.RESULT_OK) {
                openedTaskPos = -1
                return@registerForActivityResult
            }
            val data: Intent? = result.data
            val objectivesList =
                data?.getParcelableArrayListExtra<Objective>("newObjectivesList")
            val title = data?.getStringExtra("title")

            if (title == "" && objectivesList.isNullOrEmpty()) {
                (taskList.adapter as TaskListAdapter).deleteTask(openedTaskPos)
            } else if (title != null || !objectivesList.isNullOrEmpty()) {
                (taskList.adapter as TaskListAdapter).replaceTaskDetails(title!!,
                    objectivesList as ArrayList<Objective>, openedTaskPos)
                println("Posicion: $openedTaskPos")
                println("Titulo: $title")
                println("List: $objectivesList")
            }
            openedTaskPos = -1
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskList = findViewById(R.id.taskList)
        fabCreate = findViewById(R.id.fabCreate)

        setupTaskList()

        fabCreate.setImageResource(R.drawable.plus)
        fabCreate.setOnClickListener {
            val taskListAdapter = taskList.adapter as TaskListAdapter
            val newTask = Task("", RecyclerView(this))
            val newObjectivesList = arrayListOf(Objective("", false))

            newTask.innerTaskList.adapter = ObjectiveListAdapter(newObjectivesList, this)
            taskListAdapter.addTask(newTask)
            openedTaskPos = 0

            launchTaskOpenActivity(newTask.title, newObjectivesList)
        }

        var arr = arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9))

        for (row in arr) {
            println(row.toString())
        }
    }

    private fun setupTaskList() {
        val tasksList = arrayListOf(
            Task("Lunes", RecyclerView(this)),
            Task("Martes", RecyclerView(this)),
            Task("Miercoles", RecyclerView(this)),
        )

        val objectivesList = arrayListOf(
            Objective("Papa", false),
            Objective(
                "Cebolla", false
            ),
            Objective("Tomate", false),
        )

        val objectivesList2 = arrayListOf(
            Objective("Arroz", false),
            Objective("Leche", false),
            Objective("Huevos", false),
        )

        val objectivesList3 = arrayListOf(
            Objective("Carne", false),
            Objective("Pescado", false),
            Objective("Frutas", false),
        )

        var index = 1
        for (task in tasksList) {
            task.innerTaskList.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL, false
            )
            if (index == 1) {
                task.innerTaskList.adapter = ObjectiveListAdapter(objectivesList, this)
            } else if (index == 2) {
                task.innerTaskList.adapter = ObjectiveListAdapter(objectivesList2, this)
            } else if (index == 3) {
                task.innerTaskList.adapter = ObjectiveListAdapter(objectivesList3, this)
            }
            index++
        }

        val taskListAdapter = TaskListAdapter(tasksList, this)
        taskList.apply {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL, false
            )
            adapter = taskListAdapter
        }

    }

    private fun launchTaskOpenActivity(
        taskTitle: String,
        objectivesList: ArrayList<Objective>
    ) {
        val intent = Intent(this, TaskOpenActivity::class.java)
        intent.putExtra("title", taskTitle)
        val bundle = Bundle()
        bundle.putParcelableArrayList("objectivesList", objectivesList)
        intent.putExtras(bundle)
        resultLauncher.launch(intent)
    }

    override fun onTaskClick(task: Task) {
        openedTaskPos = (taskList.adapter as TaskListAdapter).tasksList.indexOf(task)
        val objectiveAdapter = task.innerTaskList.adapter as ObjectiveListAdapter
        launchTaskOpenActivity(task.title, objectiveAdapter.objectivesList)
    }

    override fun onBtnDeleteClick(task: Task) {
        val index = (taskList.adapter as TaskListAdapter).tasksList.indexOf(task)
        (taskList.adapter as TaskListAdapter).deleteTask(index)
    }

    override fun onObjectiveCheck(objective: Objective) {
        val choresList = (taskList.adapter as TaskListAdapter).tasksList
        for (chore in choresList) {
            if ((chore.innerTaskList.adapter as ObjectiveListAdapter).objectivesList.contains(
                    objective
                )
            ) {
                openedTaskPos = choresList.indexOf(chore)
                break
            }
        }
        val adapter = choresList[openedTaskPos].innerTaskList.adapter as ObjectiveListAdapter
        adapter.markObjective(objective)
    }

    override fun onDeleteClick(objective: Objective) {
        TODO("Not yet implemented")
    }


}