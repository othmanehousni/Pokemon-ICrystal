package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.ICMonDoor;
import ch.epfl.cs107.icmon.actor.npc.ProfOak;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;


/**
 * Lab is a specific area within the ICMon game world, representing a scientific lab.
 */
public class Lab extends ICMonArea {


    @Override
    public String getTitle() {
        return "lab";
    }

    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        ICMonDoor toTown = new ICMonDoor("town", new DiscreteCoordinates(15, 23), this, Orientation.DOWN, new DiscreteCoordinates(6, 1),new DiscreteCoordinates(7, 1)) ;
        registerActor(toTown);
        ProfOak profOak = new ProfOak(this, Orientation.DOWN, new DiscreteCoordinates(11,7),"actors/prof-oak");
        registerActor(profOak);
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
