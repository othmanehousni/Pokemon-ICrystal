package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;

public class PokemonFightEvent extends ICMonEvent {
    private ICMonFight fightMenu;
    private Pokemon pokemon;
    public PokemonFightEvent(ICMonFight fightMenu, Pokemon pokemon) {
        this.fightMenu = fightMenu;
        this.pokemon = pokemon;
    }

    @Override
    public void update(float deltaTime) {
        if (fightMenu.getIsRunning()) {
            fightMenu.update(deltaTime);
        } else {
            complete(); }
    }

    public ICMonFight getFight() {
        return fightMenu;
    }

    @Override
    public void interactWith(Pokemon pokemon, boolean isCellInteraction) {
        super.interactWith(pokemon, isCellInteraction);
    }
}
