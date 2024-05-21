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
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import at.htl.todoapp.model.Model
import at.htl.todoapp.model.ModelStore
import at.htl.todoapp.model.Todo
import at.htl.todoapp.model.TodoService
import at.htl.todoapp.ui.theme.TodoTheme
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainView{
    @Inject
    lateinit var modelStore: ModelStore

    @Inject
    lateinit var todoService: TodoService

    @Inject
    constructor(){}

    fun setContentOfActivity(activity: ComponentActivity) {
        val view = ComposeView(activity)
        view.setContent {
            val viewModel = modelStore.pipe.observeOn(AndroidSchedulers.mainThread()).subscribeAsState(initial = Model()).value
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                TabScreen(viewModel, modelStore, todoService)
            }
        }
        activity.setContentView(view)
    }
}

