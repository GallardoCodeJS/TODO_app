package com.example.ignacioapp

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG //Revisar para que sirve
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter (
    private val todos: MutableList<Todo> //instancia de clase
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    //ctrl+i (implement members)

    //Create the viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    //return cants items on the list
    override fun getItemCount(): Int {
        return todos.size
    }

    //Funcion que agrega tarea a la lista de tareas
    fun addTodo(todo: Todo){
        todos.add(todo) //Agrega tarea a la lista

        //actualiza los datos en pantalla
        notifyItemInserted(todos.size - 1) //Agrega al final de la fila
    }

    //Funcion que elimina todas las tareas de la lista
    fun deleteDoneTodos(){
        todos.removeAll{ todo ->
            todo.isChecked //Solo si esta checkeado el check
        }
        notifyDataSetChanged() //Notifica
    }

    // Controla funcionamiento de los checks (Tacha el title)
    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean){
        if(isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv() //revierte el estado de la flag anterior
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            tvTodoTitle.text = curTodo.title
            cbDone.isChecked = curTodo.isChecked
            //Llama a la funcion de las checks
            toggleStrikeThrough(tvTodoTitle,curTodo.isChecked)

            //Llama a la funcion cuando se da click al checkbox
            //El _ (guion bajo, en kotlin significa que no necesitamos ese parametro
            cbDone.setOnCheckedChangeListener {_,isChecked ->
                toggleStrikeThrough(tvTodoTitle,isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }
}