package AdventureModel;

import java.io.Serializable; //you will need this to save the game!

public class ShopInteract implements InteractBehaviour, Serializable{
    /**
     * the instance of ItemGiver that this behaviour is given to
     */
    private ShopOwner npc;


    /**
     * An interact behaviour that gives item to a player
     *
     * @param npc that this behaviour is given to
     */
    public ShopInteract(ShopOwner npc){
        this.npc = npc;
    }

    /**
     * Tells the player they need to pick an item when using the shop
     *
     * @param player
     * @return: string to be displayed on game label
     */
    public String interact(Player player){
        return "I'm not a mind reader! Pick an item you want to buy!";
    }

    public String interact(Player player, String itemName){
        AdventureObject returnItem = this.npc.shop.sellItem(itemName);
        if (returnItem == null){
            return "This item is no longer available";
        }else if (player.getMoneyAmount() < returnItem.getPrice()){
            //case where player doesn't have enough to buy it
            this.npc.shop.addItem(returnItem);
            return "Don't try to scam me! You don't even have enough to buy it!!!";
        }else{
            player.inventory.add(returnItem);
            player.removeMoney(returnItem.getPrice());
            this.npc.addMoney(returnItem.getPrice());
            return "Thank you!";
        }
    }
}

