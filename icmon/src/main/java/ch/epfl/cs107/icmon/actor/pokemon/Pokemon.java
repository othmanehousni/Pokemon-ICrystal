package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Pokemon is an abstract class representing pokemons in the ICMon game world.
 * It extends ICMonActor and implements ICMonFightableActor.
 */
public abstract class Pokemon extends ICMonActor implements ICMonFightableActor {
    private String name;
    private int hp;
    private int maxHp;
    private int damage;
    private RPGSprite pokemonSprite;
    private List<ICMonFightAction> fightingActions = new ArrayList<>();

    /**
     * Constructor for Pokemon, setting up its initial state.
     * @param area (Area): The area where the Pokemon is initially located.
     * @param orientation (Orientation): The initial orientation of the Pokemon in the area.
     * @param position (DiscreteCoordinates): The initial position of the Pokemon in the area.
     * @param pokemonName (String): The name of the Pokemon.
     * @param maxHp (int): The maximum health of the Pokemon.
     * @param damage (int): The damage the Pokemon can inflict.
     */

    public Pokemon(Area area, Orientation orientation, DiscreteCoordinates position, String pokemonName, int maxHp, int damage) {
        super(area, orientation, position);
        this.name= pokemonName;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.damage = damage;
        this.pokemonSprite = new RPGSprite("pokemon/" + pokemonName, 1,1, this );

    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    /** Pokemon can be crossed */

    @Override
    public boolean takeCellSpace() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }


    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }

    /**
     * Draws the Pokemon on the canvas.
     * @param canvas (Canvas): The canvas to draw onto.
     */
    public void draw(Canvas canvas) {
        pokemonSprite.draw(canvas);
    }

    /**
     * Updates the Pokemon's health when it receives damage.
     * @param amount (int): The amount of damage received.
     */
    public void receivedDamage(int amount) {
        hp = Math.max(hp - amount, 0);
    }

    public int getDamage() {
        return damage;
    }

    /**
     * Checks if the Pokemon is dead (hp <= 0).
     * @return (boolean): true if the Pokemon is dead, false otherwise.
     */
    public boolean isDead() {
        return hp <= 0;
    }

    /**
     * Returns the list of fight actions available to the Pokemon.
     * @return (List<ICMonFightAction>): List of fighting actions.
     */
    public List<ICMonFightAction> getFightingActions() {
        return fightingActions; }



    /**
     * Returns properties of the Pokemon.
     * @return (PokemonProperties): The properties object of the Pokemon.
     */
    public PokemonProperties getProperties() {
        return new PokemonProperties(this);
    }

    /**
     * Inner class to encapsulate the properties of the Pokemon.
     */
    public final class PokemonProperties {

        private Pokemon pokemon;
        public PokemonProperties(Pokemon pokemon) {
            this.pokemon = pokemon;
        }

        public String name(){
            return pokemon.name;
        }

        public float hp(){
            return hp;
        }

        public float maxHp(){
            return maxHp;
        }

        public void gainHp (int amount) {
            hp += amount;
            if(hp>maxHp) {
                hp = maxHp;
            }
        }


    }

}