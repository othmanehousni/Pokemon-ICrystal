package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.StartEventAction;
import ch.epfl.cs107.play.engine.actor.Dialog;

public class IntroductionEvent extends ICMonEvent {
    private Dialog dialog;
    private ICMonPlayer player;
    private String dialogText;

    public IntroductionEvent(ICMonPlayer player) {
        this.player = player;
        this.dialog = new Dialog("welcome_to_icmon");
    }

    @Override
    public void start() {
        player.openDialog(dialog);
        super.start();


    }

    @Override
    public void update(float deltaTime) {
        if (!player.isInDialog()) {
            this.complete();
        }

    }
}