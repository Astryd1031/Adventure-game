package AdventureModel;

import java.io.Serializable; //you will need this to save the game!

/**
 * This class contains the information of a NPC who can give hints to the player.
 */
public class HintGiver extends NPC implements Serializable{
    /**
     * what the NPC does for the player
     */
    private InteractBehaviour GiveHintInteract;
    /**
     * name of the HintGiver
     */
    public String name;
    /**
     * The hint to be given to the player.
     */
    private String hintText = "";
    /**
     * The default speech that appears when the player enters the room the HintGiver resides in.
     */
    private String talkText;
    /**
     * Tracks whether the hint has already been given to the player.
     */
    public boolean hintGiven = false;

    /**
     * Constructor for a hint giving NPC
     * @param name of the HintGiver
     */
    public HintGiver(String name){
        super(name);
        this.talkText = "Hello!";
    }


    /**
     * Sets the hint text
     * @param hintText is the text to be set as the HintGiver's hint
     */
    public void setHintText(String hintText){
        this.hintText = hintText;
    }

    /**
     * getter method for hint text
     * @return hint string
     */
    public String returnHintText(){
        return this.hintText;
    }
}
