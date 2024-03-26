package ch.epfl.cs107.icmon.area.maps;


import ch.epfl.cs107.icmon.actor.ICMonDoor;
import ch.epfl.cs107.icmon.actor.pokemon.pokemonList.Bulbizarre;
import ch.epfl.cs107.icmon.actor.pokemon.pokemonList.Latios;
import ch.epfl.cs107.icmon.actor.pokemon.pokemonList.Nidoqueen;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

/**
 * Arena is a specific area within the ICMon game world, representing a battle arena.
 */
public class Arena extends ICMonArea {


    @Override
    public String getTitle() {
        return "arena";
    }

    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        ICMonDoor toTown = new ICMonDoor("town", new DiscreteCoordinates(20, 15), this, Orientation.DOWN, new DiscreteCoordinates(4, 1),new DiscreteCoordinates(5, 1)) ;
        registerActor(toTown);
        Nidoqueen nidoqueen = new Nidoqueen(this);
        registerActor(nidoqueen);


    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }


    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return new DiscreteCoordinates(20,15);
    }
}
