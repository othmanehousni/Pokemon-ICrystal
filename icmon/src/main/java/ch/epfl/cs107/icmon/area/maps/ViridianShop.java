package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.ICMonDoor;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.Infirmary;
import ch.epfl.cs107.icmon.actor.npc.ProfOak;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public class ViridianShop extends ICMonArea {
    @Override
    public String getTitle() {
        return "viridianShop";
    }

    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        ICMonDoor toViridian = new ICMonDoor("viridian", new DiscreteCoordinates(36, 19), this, Orientation.DOWN, new DiscreteCoordinates(3, 1),new DiscreteCoordinates(4, 1)) ;
        registerActor(toViridian);
        Infirmary infirmary = new Infirmary(this,Orientation.DOWN, new DiscreteCoordinates(3, 6),"actors/infirmary");
        registerActor(infirmary);
    }

    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return null;
    }
}
