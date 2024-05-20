package at.htl.todoapp.model;

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
}
