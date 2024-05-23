package at.htl.todoapp.ui.layout

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import at.htl.todoapp.model.Model
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import at.htl.todoapp.model.Todo
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.material3.Text

@Composable
fun Todos(model: Model){
    val todos = model.todos

    LazyColumn (modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(todos.size) { index ->
            ToDoRow(todo = todos[index])
        }
    }
}

@Composable
fun ToDoRow(todo: Todo) {
    Row(modifier = Modifier.padding(4.dp)) {
        Text(todo.id.toString(), modifier = Modifier.padding(horizontal = 6.dp))
        Text(text = todo.title, overflow = TextOverflow.Ellipsis, maxLines = 1)
    }
}