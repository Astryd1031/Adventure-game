package AdventureModel;

import java.io.Serializable; //you will need this to save the game!

public class GiveItemInteract implements InteractBehaviour, Serializable{

    /**
     * the instance of ItemGiver that this behaviour is given to
     */
    private ItemGiver npc;

    /**
     * An interact behaviour that gives item to a player
     *
     * @param npc that this behaviour is given to
     */
    public GiveItemInteract(ItemGiver npc){
        this.npc = npc;
    }

    /**
     * Gives and item to the player and returns a string to indicates user
     *
     * @param player to be interacted with
     * @return string that indicates the object that was given to the player
     */
    public String interact(Player player){
        if (this.npc.itemGiven){
            return "I've already given you all I have.";
        }else{
            AdventureObject item = this.npc.returnItem();
            player.inventory.add(item);
            this.npc.itemGiven = true;
            return "I have given you the " + item.getName()+ ". It is " + item.getDescription()+
                    ". May it help you on your journey!";
        }
    }
}
