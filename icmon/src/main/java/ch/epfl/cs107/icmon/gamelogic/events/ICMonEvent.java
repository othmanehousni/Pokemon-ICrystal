package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.Action;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.engine.Updatable;
import java.util.ArrayList;


public abstract class ICMonEvent implements Updatable, ICMonInteractionVisitor {
    protected ICMonPlayer player;
    private boolean started = false;
    private boolean completed = false;
    private boolean suspended = false;

    public ArrayList<Action> isStartedActions = new ArrayList<Action>();
    public ArrayList<Action> isCompletedActions = new ArrayList<Action>();
    public ArrayList<Action> isSuspendedActions = new ArrayList<Action>();
    public ArrayList<Action> isResumedActions = new ArrayList<Action>();
    public void start(){
        if (!started) {
            started = true;
            for (Action action : isStartedActions) {
                action.perform();
            } isStartedActions.clear();
        }
    }
    public void complete(){
        if (started && !completed) {
            completed = true;
            for (Action action : isCompletedActions){
                action.perform();

            }
            isCompletedActions.clear();
        }
    }
    public final void suspend(){
        if (started && !completed && !suspended) {
            suspended = true;
            for (Action action : isSuspendedActions) {
                action.perform();
            }
            isSuspendedActions.clear();

        }
    }
    public final void resume(){
        if (started && suspended && !completed) {
            suspended = false;
            for (Action action : isResumedActions) {
                action.perform();
            }
        }
    }
    public void onStart(Action action){
        isStartedActions.add(action);
    }
    public void onComplete(Action action){
        isCompletedActions.add(action);
    }
    public final void onSuspension(Action action){
        isSuspendedActions.add(action);
    }
    public final void onResume(Action action){
        isResumedActions.add(action);
    }

    public boolean isCompleted() {
        return completed;
    }
    public boolean isStarted() {
        return started;
    }
    public boolean isSuspended() {
        return suspended;
    }

   @Override
    public void update(float deltaTime) {
    }
}
