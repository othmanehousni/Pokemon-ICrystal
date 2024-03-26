package ch.epfl.cs107.icmon.gamelogic.actions;

public class LogAction implements Action {
    public String message;
    @Override
    public void perform() {
        System.out.println(message);
    }
    public LogAction (String message) {
        this.message = message;

        }

    }
