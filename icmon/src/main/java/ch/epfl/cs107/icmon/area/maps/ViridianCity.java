package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonDoor;
import ch.epfl.cs107.icmon.actor.npc.Garry;
import ch.epfl.cs107.icmon.actor.npc.ProfOak;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Window;

public class ViridianCity extends ICMonArea {


    @Override
    public String getTitle() {
        return "viridian";
    }

    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        ICMonDoor toArena = new ICMonDoor("viridianArena", new DiscreteCoordinates(4, 2), this, Orientation.DOWN, new DiscreteCoordinates(26, 13));
        registerActor(toArena);
        ICMonDoor toTown = new ICMonDoor("town", new DiscreteCoordinates(28, 15), this, Orientation.DOWN, new DiscreteCoordinates(0, 21), new DiscreteCoordinates(0, 22)) ;
        registerActor(toTown);
    }

    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return new DiscreteCoordinates(3,20);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        return super.begin(window, fileSystem);
    }
}

