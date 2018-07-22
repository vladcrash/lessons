package ru.sberbank.android.school.lessons;

class StateManager {
    private static final StateManager ourInstance = new StateManager();
    private int counter;
    private String[] states = {"A", "B", "C", "D", "E"};
    private String currentState;

    static StateManager getInstance() {
        return ourInstance;
    }

    private StateManager() {
        currentState = states[0];
    }

    public void changeState() {
        currentState = states[++counter % 5];
    }

    public String getCurrentState() {
        return currentState;
    }
}
