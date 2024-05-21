package at.htl.todoapp.model;

import android.util.Log;

import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;
import javax.inject.Singleton;
import org.eclipse.microprofile.config.Config;
import at.htl.todoapp.util.resteasy.RestApiClientBuilder;

@Singleton
public class TodoService {
    public static String JSON_PLACEHOLDER_BASE_URL_SETTING = "https://jsonplaceholder.typicode.com";
    public final TodoClient todoClient;
    public final ModelStore store;

    @Inject
    TodoService(Config config, RestApiClientBuilder builder, ModelStore store){
        var baseUrl = config.getValue(JSON_PLACEHOLDER_BASE_URL_SETTING, String.class);
        todoClient = builder.build(TodoClient.class, baseUrl);
        this.store = store;
    }

    public void getAll(){
        CompletableFuture
                .supplyAsync(todoClient::all)
                .thenAccept(store::setTodos);
    }
}
