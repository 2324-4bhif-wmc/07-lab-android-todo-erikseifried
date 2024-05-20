package at.htl.todoapp.ui.layout

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import at.htl.todoapp.model.Model
import at.htl.todoapp.model.ModelStore
import at.htl.todoapp.model.Todo
import at.htl.todoapp.ui.theme.TodoTheme
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainView @Inject constructor(
    val store: ModelStore
) {
    fun buildContent(activity: ComponentActivity) {
        activity.setContent {
            TodoApp(store)
        }
    }
}

@Composable
fun TodoApp(store: ModelStore) {
    val viewModel by store.pipe
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeAsState(initial = Model())

    TodoTheme {
        Scaffold { paddingValues ->
            TodoList(model = viewModel, store = store, modifier = Modifier.padding(paddingValues))
        }
    }
}

@Composable
fun TodoList(model: Model, store: ModelStore, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        var text by remember { mutableStateOf("") }
        val keyboardController = LocalSoftwareKeyboardController.current

        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Add a todo") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                if (text.isNotBlank()) {
                    store.addTodo(Todo(1L, System.currentTimeMillis(), text, false))
                    text = ""  // Clear the input field after submitting
                    keyboardController?.hide()  // Optionally hide the keyboard
                }
            }),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(items = model.todos, key = { it.id }) { todo ->
                TodoItem(todo = todo, onRemove = {
                    store.removeTodo(todo)
                })
            }
        }
    }
}



@Composable
fun TodoItem(todo: Todo, onRemove: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),  // Added padding for better spacing
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = todo.title, style = MaterialTheme.typography.bodyLarge)
        IconButton(onClick = onRemove) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
        }
    }
}

