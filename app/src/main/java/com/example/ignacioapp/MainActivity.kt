package com.example.ignacioapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //Promise
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Carga la lista al comenzar la app (En esta caso va vacia)
        todoAdapter = TodoAdapter(mutableListOf())
        //
        rvTodoItems.adapter = todoAdapter
        //Display item in real time
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        //Funtionality of the bottons
        btnAddTodo.setOnClickListener{
            val todoTitle = etTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()){
                val todo = Todo(todoTitle) //Hace referencia al Todo de TodoAdapter.kt
                todoAdapter.addTodo(todo) //Lo agrega a la lista
                etTodoTitle.text.clear() //Limpia el campo de texto
            }
        }

        btnDeleteDoneTodos.setOnClickListener{
            todoAdapter.deleteDoneTodos() //LLama a la funcion de TodoAdapter.kt
        }
    }
}