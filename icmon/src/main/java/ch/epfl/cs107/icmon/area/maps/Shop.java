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

/**
 * Shop is a specific area within the ICMon game world, representing a pokemon shop.
 */
public class Shop extends ICMonArea {
    @Override
    public String getTitle() {
        return "shop";
    }

    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        ICMonDoor toTown = new ICMonDoor("town", new DiscreteCoordinates(25, 19), this, Orientation.DOWN, new DiscreteCoordinates(3, 1),new DiscreteCoordinates(4, 1)) ;
        registerActor(toTown);
        ICShopAssistant assistant = new ICShopAssistant(this, Orientation.DOWN, new DiscreteCoordinates(9,5),"actors/assistant");
        registerActor(assistant);
        Infirmary infirmary = new Infirmary(this,Orientation.DOWN, new DiscreteCoordinates(2, 3),"actors/infirmary");
        registerActor(infirmary);
    }

    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return null;
    }
}
