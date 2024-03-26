package ch.epfl.cs107.icmon.gamelogic.message;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonFightEvent;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;

public abstract class GamePlayMessage {
    public abstract void process(ICMon.ICMonGameState gameState);

}