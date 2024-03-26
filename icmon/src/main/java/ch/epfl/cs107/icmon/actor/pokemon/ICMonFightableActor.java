package ch.epfl.cs107.icmon.actor.pokemon;

/**
 * ICMonFightableActor is an interface representing actors in the ICMon game that can engage in fights.
 */
public interface ICMonFightableActor {
    /**
     * Default method for initiating a fight. Specific fight behavior should be implemented by the classes implementing this interface.
     */
    default void fight () {}
}
