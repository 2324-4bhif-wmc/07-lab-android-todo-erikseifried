package at.htl.todoapp.model;

public class Model {
    public static class UIState{
        public int selectedTab = 0;
    }

    public Todo[] todos = new Todo[0];
    public UIState uiState = new UIState();

    public static class HomeScreenModel {
        public String greetingText = "Hello, world!";
    }
    public HomeScreenModel homeScreenModel = new HomeScreenModel();
}
