package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICMonBall;
import ch.epfl.cs107.icmon.actor.items.ICMonItem;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.ProfOak;
import ch.epfl.cs107.icmon.actor.pokemon.pokemonList.Latios;
import ch.epfl.cs107.icmon.actor.pokemon.pokemonList.Raichu;
import ch.epfl.cs107.icmon.gamelogic.actions.RegisterinAreaAction;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.Updatable;
import ch.epfl.cs107.play.engine.actor.Dialog;

import javax.swing.*;

public class AfterWinningGarryEvent extends ICMonEvent {
    private Area mainArea;
    private ICMonPlayer player;

    public AfterWinningGarryEvent(Area mainArea, ICMonPlayer player) {
        this.mainArea = mainArea;
        this.player = player;

    }


    public void interactWith(ICShopAssistant assistant, boolean isCellInteraction){
        player.openDialog(new Dialog("winningGarry"));
        player.getPokemonList().add(new Raichu(mainArea));
    }

}

