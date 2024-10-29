package com.mobile.simpletodo

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeViewmodel:ViewModel
    (){

        var todoID:Int = 0
    var newtodo= mutableStateListOf<Todo>()


    var mutableChecker = mutableStateOf<Boolean>(false)

    fun changeChecker(todo:Todo){
       todo.checker.value = !todo.checker.value
    }

    fun addTod(todo:String){
            newtodo.add(Todo(todoID = todoID++ ,todo))
        }



    fun removeTodo(it: Todo) {
        newtodo.remove(it)

    }
}

data class Todo(
    var todoID :Int? = null,

    var todo:String?=null,
    var checker: MutableState<Boolean> = mutableStateOf(false)
)