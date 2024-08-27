package AdventureModel;

import java.io.Serializable; //you will need this to save the game!

public class GiveHintInteract implements InteractBehaviour, Serializable{
    /**
     * the instance of HintGiver that this behaviour is given to
     */
    private HintGiver npc;

    /**
     * An interact behaviour that gives a hint to a player
     *
     * @param npc that this behaviour is given to
     */
    public GiveHintInteract(HintGiver npc){
        this.npc = npc;
    }

    /**
     * Gives a hint to the player and returns a string of the hint
     *
     * @param player to be interacted with
     * @return string that represents the hint
     */
    public String interact(Player player){
        if (npc.hintGiven){
            return "I've already told you all I know";
        }else{
            player.addHints(this.npc.returnHintText());
            npc.hintGiven = true;
            return this.npc.returnHintText();
        }
    }

}
