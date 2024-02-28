package com.example.tasklists.activities

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklists.R
import com.example.tasklists.adapters.ObjectiveListAdapter
import com.example.tasklists.models.Objective

class TaskOpenActivity : AppCompatActivity(), ObjectiveListAdapter.OnObjectiveClickListener {
    lateinit var etTitle : TextView
    lateinit var objectivesList : RecyclerView
    lateinit var btnBack : ImageButton
    lateinit var btnCreate : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_open)
        etTitle = findViewById(R.id.etInnerTaskTitle)
        objectivesList = findViewById(R.id.innerChoreList)
        btnBack = findViewById(R.id.btnBack)
        btnCreate = findViewById(R.id.btnCreate)

        setupObjectivesList()
        setupOnClickListeners()
    }

    private fun setupObjectivesList(){
        val title = intent.getStringExtra("title")
        etTitle.text = title
        objectivesList.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)
        var objectivesList: ArrayList<Objective?> = intent.extras?.
            getParcelableArrayList<Parcelable>("objectivesList") as ArrayList<Objective?>
        this.objectivesList.adapter = ObjectiveListAdapter(objectivesList as ArrayList<Objective>, this)
    }

    private fun setupOnClickListeners(){
        btnBack.setOnClickListener {
            passChanges()
            setResult(RESULT_OK, intent)
            finish()
        }

        btnCreate.setOnClickListener {
            val adapter = objectivesList.adapter as ObjectiveListAdapter
            adapter.addObjective(Objective("", false))
        }

        objectivesList.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(
                v: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int,
                oldRight: Int, oldBottom: Int) {
                objectivesList.scrollToPosition(
                    (objectivesList.adapter as ObjectiveListAdapter).objectivesList.size - 1)
            }
        })

    }

    private fun passChanges(){
        val objectivesList = (objectivesList.adapter as ObjectiveListAdapter).objectivesList

        val iterator = objectivesList.iterator()
        while (iterator.hasNext()) {
            val objective = iterator.next()
            if (objective.description == "") {
                iterator.remove()
            }
        }

        intent.putExtra("title", etTitle.text.toString())
        intent.putParcelableArrayListExtra("newObjectivesList", objectivesList)
        setResult(Activity.RESULT_OK, intent)
    }

    override fun onBackPressed() {
        passChanges()
        finish()
        onBackPressedDispatcher.onBackPressed()
    }

    override fun onObjectiveCheck(objective: Objective) {
        val adapter = objectivesList.adapter as ObjectiveListAdapter
        adapter.markObjective(objective)
    }

    override fun onDeleteClick(objective: Objective) {
        val adapter = objectivesList.adapter as ObjectiveListAdapter
        adapter.deleteObjective(objective)
    }
}