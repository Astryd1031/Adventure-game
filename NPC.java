package AdventureModel;

import java.io.Serializable; //you will need this to save the game!

public abstract class NPC implements Serializable {
    /**
     * name of the NPC
     */
    public String name;
    /**
     * The default speech that appears when the player enters the room the NPC resides in.
     */
    private String talkText;

    /**
     * abstract NPC constructor, do not instantiate
     * @param name
     */

    /**
     * what the NPC does for the player
     */
    private InteractBehaviour behaviour;

    /**
     * abstract class, do not instantiate
     * @param name of the NPC
     */
    public NPC(String name){
        this.name = name;
    }

    /**
     * Returns the default speech of the NPC
     * @return string containing the default text that will appear when the player enters the same room as the NPC
     */
    public String talk(){
        return this.talkText;
    }

    /**
     * Sets the default talk text
     * @param talkText is the text to be set as the NPC's default text
     */
    public void setTalkBehaviour(String talkText){
        this.talkText = talkText;
    }

    /**
     * Sets what will happen when player interacts with NPC
     * @param behaviour to be set to
     */
    public void setInteractBehaviour(InteractBehaviour behaviour){
        this.behaviour = behaviour;
    }

    /**
     * Gives something to the player and returns a string indicating what was given
     *
     * @param player to be modified
     * @return string which tells user what the player received
     */
    public String interact(Player player){
        return this.behaviour.interact(player);
    }


}
