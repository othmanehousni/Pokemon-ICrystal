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

public class ViridianArena extends ICMonArea {

    @Override
    public String getTitle() {
        return "viridianArena";
    }

    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        ICMonDoor toViridian = new ICMonDoor("viridian", new DiscreteCoordinates(26, 12), this, Orientation.DOWN, new DiscreteCoordinates(4, 1),new DiscreteCoordinates(5, 1)) ;
        registerActor(toViridian);
        Bulbizarre bulbizarre = new Bulbizarre(this);
        registerActor(bulbizarre);
        Latios latios = new Latios(this);
        registerActor(latios);



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
