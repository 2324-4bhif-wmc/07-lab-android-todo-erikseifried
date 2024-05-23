package at.htl.todoapp.ui.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import at.htl.todoapp.model.Model
import at.htl.todoapp.model.ModelStore
import at.htl.todoapp.model.TodoService
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.TextField
import androidx.compose.material3.Button

@Composable
fun HomeScreen(model: Model, todoService: TodoService?, store: ModelStore?) {
    val todos = model.todos;
    val text = remember { mutableStateOf(model.homeScreenModel.greetingText) }
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(16.dp)) {
            Text(
                text = text.value,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row(Modifier.align(Alignment.CenterHorizontally).padding(16.dp)) {
            TextField(
                value = text.value,
                onValueChange = {
                    text.value = it
                    store?.apply { mdl -> mdl.homeScreenModel.greetingText = it }
                }
            )
        }
        Row(Modifier.align(Alignment.CenterHorizontally)) {
            Text("${todos.size} Todos have been loaded")
        }
        Row(Modifier.align(Alignment.CenterHorizontally)) {
            Button(modifier = Modifier.padding(16.dp),
                onClick = { todoService?.getAll() }) {
                Text("load Todos now")
            }
        }
        Row(Modifier.align(Alignment.CenterHorizontally)) {
            Button(
                onClick = { store?.setTodos(arrayOf()) }) {
                Text("clean Todos")
            }
        }
    }
}