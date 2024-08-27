package AdventureModel;

import java.io.Serializable; //you will need this to save the game!

/**
 * This class contains the information of a NPC who can give items to the player.
 */
public class ItemGiver extends NPC implements Serializable{
    /**
     * name of the ItemGiver
     */
    public String name;
    /**
     * The default speech that appears when the player enters the room the HintGiver resides in.
     */
    private String talkText;
    /**
     * The object the ItemGiver has
     */
    private AdventureObject item = null;
    /**
     * tracks whether the ItemGiver has given the item to player
     */
    public boolean itemGiven = false;

    /**
     * what the NPC does for the player
     */
    private InteractBehaviour GiveItemInteract;

    /**
     * Constructor for an item giving NPC
     * @param name of the item giving NPC
     */
    public ItemGiver(String name){
        super(name);
        this.talkText = "";
    }


    /**
     * Sets the ItemGiver's item
     * @param item is the item that can be given to the player
     */
    public void setItem(AdventureObject item){
        this.item = item;
    }

    /**
     * getter method for item
     * @return item NPC has
     */
    public AdventureObject returnItem(){
        return this.item;
    }
}
