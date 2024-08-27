package AdventureModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.Serializable; //you will need this to save the game!

/**
 * This class contains the information of a shop which has items that can be sold to the player.
 */
public class Shop implements Serializable{
    /**
     * a list of all items available in the shop
     */
    ArrayList<AdventureObject> inventory;

    /**
     * Shop constructor
     */
    public Shop(){
        this.inventory = new ArrayList<AdventureObject>();
        //will have to setup the inventory here after we decide what kind of items
    }

    /**
     * returns a hashmap containing the names of the items and their corresponding prices
     */
    public HashMap<String, Integer> getItems(){
        //shopobjects should have a corresponding price
        //this method will be used to help display items and price to the user
        HashMap<String, Integer> returnMap = new HashMap<String, Integer>();
        for (AdventureObject item: this.inventory){
            returnMap.put(item.getName(), item.getPrice());
        }
        return returnMap;
    }

    /**
     * removes the object with the corresponding name from the inventory and returns it. If the object
     * name cannot be found, returns null
     *
     * @param name of the item to be sold
     */
    public AdventureObject sellItem(String name){
        for (AdventureObject item: this.inventory){
            if (name.equals(item.getName())){
                this.inventory.remove(item);
                return item;
            }
        }
        return null;
    }

    /**
     * adds an item to the shop's inventory
     * @param item to be added
     */
    public void addItem(AdventureObject item){
        this.inventory.add(item);
    }

}
