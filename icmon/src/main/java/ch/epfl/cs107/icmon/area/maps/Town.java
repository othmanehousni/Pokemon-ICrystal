package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.ICMonDoor;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import ch.epfl.cs107.icmon.actor.items.ICMonBall;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.math.Orientation;

/**
 * Town is a specific area within the ICMon game world, representing the main area that the player can visit.
 */
public class Town extends ICMonArea {

    private ICMonBall ball = new ICMonBall(this, Orientation.DOWN, new DiscreteCoordinates(24, 7), "items/icball");
    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return new DiscreteCoordinates(5, 5);
    }

    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        ICShopAssistant assistant = new ICShopAssistant(this, Orientation.DOWN, new DiscreteCoordinates(8, 22), "actors/assistant");
        this.registerActor(assistant);
        ICMonDoor toLab = new ICMonDoor("lab", new DiscreteCoordinates(6, 2), this, Orientation.UP, new DiscreteCoordinates(15, 24));
        registerActor(toLab);
        ICMonDoor toArena = new ICMonDoor("arena", new DiscreteCoordinates(4, 2), this, Orientation.UP, new DiscreteCoordinates(20, 16));
        registerActor(toArena);
        ICMonDoor toHouse = new ICMonDoor("house", new DiscreteCoordinates(2, 2), this, Orientation.UP, new DiscreteCoordinates(7, 27));
        registerActor(toHouse);
        ICMonDoor toShop = new ICMonDoor("shop", new DiscreteCoordinates(3, 2), this, Orientation.UP, new DiscreteCoordinates(25, 20));
        registerActor(toShop);
        ICMonDoor toViridian = new ICMonDoor("viridian", new DiscreteCoordinates(2, 22), this, Orientation.DOWN, new DiscreteCoordinates(29, 14),new DiscreteCoordinates(29, 15)) ;
        registerActor(toViridian);
        registerActor(new ICMonDoor("town",new DiscreteCoordinates(3,3) , this, Orientation.DOWN, new DiscreteCoordinates(19,24),new DiscreteCoordinates(20,24),new DiscreteCoordinates(21,24),new DiscreteCoordinates(22,24),new DiscreteCoordinates(23,24),new DiscreteCoordinates(24,24) , new DiscreteCoordinates(25,24) ));
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }


    public ICMonBall getBall() {
        return ball;
    }



    @Override
    public String getTitle() {
        return "town";
    }

}