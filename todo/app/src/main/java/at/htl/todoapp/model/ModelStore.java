package at.htl.todoapp.model;

import static at.htl.todoapp.model.TodoService.TAG;

import android.util.Log;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.todoapp.util.store.Store;

@Singleton
public class ModelStore extends Store<Model> {
    @Inject
    ModelStore(){
        super(Model.class, new Model());
    }

    public void setTodos(Todo[] todos){
        apply(model -> model.todos = todos);
    }

    public void addTodo(Todo todo) {
        Log.d(TAG, "Adding todo: " + todo);
        apply(model -> {
            Todo[] newTodos = new Todo[model.todos.length + 1];
            System.arraycopy(model.todos, 0, newTodos, 0, model.todos.length);
            newTodos[model.todos.length] = todo;
            model.todos = newTodos;
        });
    }

    public void removeTodo(Todo todo) {
        Log.d(TAG, "Removing todo: " + todo);
        apply(model -> {
            Todo[] newTodos = Arrays.stream(model.todos)
                    .filter(t -> !t.id.equals(todo.id))
                    .toArray(Todo[]::new);
            model.todos = newTodos;
        });
    }

}
