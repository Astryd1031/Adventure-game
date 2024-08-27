package AdventureModel;

public interface InteractBehaviour {

    /**
     * Gives something to the player and returns a string indicating what was given
     *
     * @param player to be interacted with
     * @return string that indicates what is given to the player
     */
    public String interact(Player player);

}
