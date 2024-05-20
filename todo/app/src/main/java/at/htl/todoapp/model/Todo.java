package at.htl.todoapp.model;
public class Todo {
    public Long userId;
    public Long id;
    public String title;
    public Boolean completed;

    public Todo(Long userId, Long id, String title, Boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }
}