package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonDoor;
import ch.epfl.cs107.icmon.actor.npc.Garry;
import ch.epfl.cs107.icmon.actor.npc.ProfOak;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public class House extends ICMonArea {

    private Garry garry = new Garry(this,Orientation.DOWN,new DiscreteCoordinates(1,3),"actors/garry");

    @Override
    public String getTitle() {
        return "house";
    }

    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        ICMonDoor toTown = new ICMonDoor("town", new DiscreteCoordinates(7, 26), this, Orientation.DOWN, new DiscreteCoordinates(3, 1),new DiscreteCoordinates(4, 1)) ;
        registerActor(toTown);
        registerActor(garry);

    }

    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return new DiscreteCoordinates(7,4);
    }

    public Garry getGarry() {
        return garry;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
}
