package com.example.tasklists.adapters

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklists.R
import com.example.tasklists.models.Objective

class ObjectiveListAdapter(var objectivesList : ArrayList<Objective>,
                           var objectiveListener : OnObjectiveClickListener) :
    RecyclerView.Adapter<ObjectiveListAdapter.ObjectiveListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectiveListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.objective_item, parent, false)

        val activityName = parent.context.javaClass.simpleName
        if(activityName == "MainActivity"){
            view.findViewById<ImageButton>(R.id.btnDelete).visibility = View.GONE
            val txt = view.findViewById<TextView>(R.id.txtDescription)
            txt.isFocusable = false
            txt.setOnClickListener {
                parent.performClick()
            }
        }

        return ObjectiveListViewHolder(view, EditTextListener())
    }


    override fun getItemCount(): Int {
        return objectivesList.size
    }

    override fun onBindViewHolder(holder: ObjectiveListViewHolder, position: Int) {

        val objective = objectivesList[position]
        holder.cbDone.isChecked = objective.isDone

        holder.editTextListener.updatePosition(holder.adapterPosition)
        holder.txtDescripcion.setText(objectivesList[position].description)

        holder.cbDone.setOnClickListener{
            objectiveListener.onObjectiveCheck(objective)
        }

        holder.btnDelete.setOnClickListener {
            objectiveListener.onDeleteClick(objective)
        }
    }

    override fun onViewAttachedToWindow(holder: ObjectiveListViewHolder) {
        holder.enableTextWatcher()
    }

    override fun onViewDetachedFromWindow(holder: ObjectiveListViewHolder) {
        holder.disableTextWatcher()
    }

    fun addObjective(objective: Objective){
        objectivesList.add(objective)
        notifyItemInserted(objectivesList.size - 1)
    }

    fun deleteObjective(objective: Objective){
        val position = objectivesList.indexOf(objective)
        objectivesList.remove(objective)
        notifyItemRemoved(position)
    }

    fun markObjective(objective: Objective){
        val position = objectivesList.indexOf(objective)
        objectivesList[position].isDone = !objectivesList[position].isDone
        notifyItemChanged(position)
    }

    class ObjectiveListViewHolder(itemView: View, etListener : EditTextListener) :
        RecyclerView.ViewHolder(itemView) {
        val cbDone = itemView.findViewById<CheckBox>(R.id.cbDone)
        val txtDescripcion = itemView.findViewById<EditText>(R.id.txtDescription)
        val btnDelete = itemView.findViewById<ImageButton> (R.id.btnDelete)
        val editTextListener = etListener

        fun enableTextWatcher() {
            txtDescripcion.addTextChangedListener(editTextListener)
        }

        fun disableTextWatcher() {
            txtDescripcion.removeTextChangedListener(editTextListener)
        }
    }

    inner class EditTextListener: TextWatcher {
        var position = 0

        fun updatePosition(position: Int) {
            this.position = position
        }

        override fun afterTextChanged(s: Editable?) {
            //TODO
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //TODO
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            objectivesList[position].description = s.toString()
        }
    }

    interface OnObjectiveClickListener{
        fun onObjectiveCheck(objective: Objective)
        fun onDeleteClick(objective: Objective)
    }
}