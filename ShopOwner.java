package AdventureModel;

import java.io.Serializable; //you will need this to save the game!

/**
 * This class contains the information of a NPC who can sell items to the player through a shop.
 */
public class ShopOwner extends NPC implements Serializable{
    /**
     * what the NPC does for the player
     */
    private ShopInteract behaviour;
    /**
     * name of the shopOwner
     */
    public String name;
    /**
     * the shop
     */
    public Shop shop;
    /**
     * the amount of currency the shopOwner has from selling items to the player
     */
    private int moneyAmount;

    /**
     * The default speech that appears when the player enters the room the ShopOwner resides in.
     */
    private String talkText = "";

    /**
     * Constructor for an item selling NPC
     * @param name of the shopOwner
     */
    public ShopOwner(String name){
        super(name);
        this.moneyAmount = 0;
        this.shop = new Shop();
    }

    @Override
    /**
     * Returns the current items in the shop
     */
    public String talk(){
        return "Welcome to the shop! The available items are as follows: "+ (this.shop.getItems()).toString();
    }

    /**
     * Removes the item corresponding item from the shop and returns it
     * @param itemName: name of the item the user wants to buy
     * @return: AdventureObject
     */
    public String interact(Player player, String itemName){
        return this.behaviour.interact(player, itemName);
    }

    @Override
    /**
     * Sets the default speech text of the NPC
     * @param talktext
     */
    public void setTalkBehaviour(String talktext){
        System.out.println("ShopOwner already has a default talk behaviour");
    }


    /**
     * adds the amount to moneyAmount
     *
     * @param amount is the value to be added
     */
    public void addMoney(int amount){
        this.moneyAmount += amount;
    }

}
