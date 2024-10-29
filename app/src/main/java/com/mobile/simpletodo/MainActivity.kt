package com.mobile.simpletodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobile.simpletodo.ui.theme.SimpleTodoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleTodoTheme(darkTheme = true) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NewHome(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun NewHome(modifier: Modifier = Modifier) {

    var viewmodel: HomeViewmodel = viewModel()
    var etext = remember {
        mutableStateOf("")
    }
    Column(modifier = modifier.padding(horizontal = 20.dp)) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = etext.value,
            onValueChange = { etext.value = it })

        Spacer(modifier = Modifier.padding(10.dp))


        Button(modifier = Modifier.fillMaxWidth(), onClick = {


            viewmodel.addTod(etext.value)
            etext.value = ""
            println("OB: " + viewmodel.newtodo)

        }) {
            Text(text = "Save")
        }


        Column {
            viewmodel.newtodo.sortedByDescending { it.todoID }.forEach { todo ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Checkbox(
                        checked = todo.checker.value,

                        onCheckedChange = {
                            viewmodel.changeChecker(todo)
                            println("TOGGLES: " + todo.checker)

                        },
                    )
                    Text(
                        text = todo.todo.toString(),
                        modifier = Modifier.clickable { viewmodel.removeTodo(todo) })

                    IconButton(onClick = {

                        viewmodel.removeTodo(todo)
                    }, content = {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    })

                }


            }
        }

    }


}


@Preview(
    showBackground = true,
    device = Devices.PIXEL_7_PRO,
    showSystemUi = true,
)
@Composable
fun Home() {
    SimpleTodoTheme {
        NewHome()
    }
}