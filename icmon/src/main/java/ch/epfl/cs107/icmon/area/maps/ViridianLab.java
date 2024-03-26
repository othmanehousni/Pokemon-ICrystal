package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.ICMonDoor;
import ch.epfl.cs107.icmon.actor.npc.ProfOak;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public class ViridianLab extends ICMonArea {


    @Override
    public String getTitle() {
        return "viridianLab";
    }

    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        ICMonDoor toViridian = new ICMonDoor("viridian", new DiscreteCoordinates(15, 23), this, Orientation.DOWN, new DiscreteCoordinates(6, 1),new DiscreteCoordinates(7, 1)) ;
        registerActor(toViridian);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }


    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return new DiscreteCoordinates(6,2);
    }
}
