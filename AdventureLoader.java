package AdventureModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class AdventureLoader. Loads an adventure from files.
 */
public class AdventureLoader {

    private AdventureGame game; //the game to return
    private String adventureName; //the name of the adventure

    /**
     * Adventure Loader Constructor
     * __________________________
     * Initializes attributes
     * @param game the game that is loaded
     * @param directoryName the directory in which game files live
     */
    public AdventureLoader(AdventureGame game, String directoryName) {
        this.game = game;
        this.adventureName = directoryName;
    }

     /**
     * Load game from directory
     */
    public void loadGame() throws IOException {
        parseRooms();
        parseObjects();
        parseSynonyms();
        parseNPCs();
        this.game.setHelpText(parseOtherFile("help"));
    }

     /**
     * Parse Rooms File
     */
    private void parseRooms() throws IOException {

        int roomNumber;

        String roomFileName = this.adventureName + "/rooms.txt";
        BufferedReader buff = new BufferedReader(new FileReader(roomFileName));

        while (buff.ready()) {

            String currRoom = buff.readLine(); // first line is the number of a room

            roomNumber = Integer.parseInt(currRoom); //current room number

            // now need to get room name
            String roomName = buff.readLine();

            // now we need to get the description
            String roomDescription = "";
            String line = buff.readLine();
            while (!line.equals("-----")) {
                roomDescription += line + "\n";
                line = buff.readLine();
            }
            roomDescription += "\n";

            // now we make the room object
            Room room = new Room(roomName, roomNumber, roomDescription, adventureName);

            // now we make the motion table
            line = buff.readLine(); // reads the line after "-----"
            while (line != null && !line.equals("")) {
                String[] part = line.split(" \s+"); // have to use regex \\s+ as we don't know how many spaces are between the direction and the room number
                String direction = part[0];
                String dest = part[1];
                if (dest.contains("/")) {
                    String[] blockedPath = dest.split("/");
                    String dest_part = blockedPath[0];
                    String object = blockedPath[1];
                    Passage entry = new Passage(direction, dest_part, object);
                    room.getMotionTable().addDirection(entry);
                } else {
                    Passage entry = new Passage(direction, dest);
                    room.getMotionTable().addDirection(entry);
                }
                line = buff.readLine();
            }
            this.game.getRooms().put(room.getRoomNumber(), room);
        }

    }

     /**
     * Parse Objects File
     */
    public void parseObjects() throws IOException {

        String objectFileName = this.adventureName + "/objects.txt";
        BufferedReader buff = new BufferedReader(new FileReader(objectFileName));

        while (buff.ready()) {
            String objectName = buff.readLine();
            String objectDescription = buff.readLine();
            String objectLocation = buff.readLine();
            String separator = buff.readLine();
            if (separator != null && !separator.isEmpty())
                System.out.println("Formatting Error!");
            int i = Integer.parseInt(objectLocation);
            Room location = this.game.getRooms().get(i);
            AdventureObject object = new AdventureObject(objectName, objectDescription, location);
            // below is for game.objectsInGame attribute, where shop can display all the items within the game without
            // worrying if the removal would cause problems (and the objects being sold showing up in objectInRoom sidebar)
            AdventureObject objectLog = new AdventureObject(objectName, objectDescription);
            game.objectsInGame.put(objectName, objectLog);
            location.addGameObject(object);
            game.objectsInGame.put(objectName, object);
        }

    }

     /**
     * Parse Synonyms File
     */
    public void parseSynonyms() throws IOException {
        String synonymsFileName = this.adventureName + "/synonyms.txt";
        BufferedReader buff = new BufferedReader(new FileReader(synonymsFileName));
        String line = buff.readLine();
        while(line != null){
            String[] commandAndSynonym = line.split("=");
            String command1 = commandAndSynonym[0];
            String command2 = commandAndSynonym[1];
            this.game.getSynonyms().put(command1,command2);
            line = buff.readLine();
        }

    }

    /**
     * Parse NPC file
     * Initiates NPCs based on the file parsed
     * Adds NPCs to their respective assigned rooms
     * @throws IOException
     */
    public void parseNPCs() throws IOException {
        String npcFileName = this.adventureName + "/npc.txt";
        BufferedReader buff = new BufferedReader(new FileReader(npcFileName));

        while (buff.ready()) {
            String npcType = buff.readLine();
            String npcName = buff.readLine();
            String npcDefaultText = buff.readLine();
            String npcHintOrItem = buff.readLine();
            String npcLocation = buff.readLine();
            String separator = buff.readLine();
            if (separator != null && !separator.isEmpty())
                System.out.println("Formatting Error!");
            if (npcType.equals("HintGiver")) {
                HintGiver hintNPC = new HintGiver(npcName);
                hintNPC.setTalkBehaviour(npcDefaultText);
                hintNPC.setHintText(npcHintOrItem);
                hintNPC.setInteractBehaviour(new GiveHintInteract(hintNPC));
                Room location = this.game.getRooms().get(Integer.parseInt(npcLocation));
                location.addNPC(hintNPC);
            } else if (npcType.equals("ItemGiver")) {
                ItemGiver itemNPC = new ItemGiver(npcName);
                itemNPC.setTalkBehaviour(npcDefaultText);
                AdventureObject object = game.objectsInGame.get(npcHintOrItem);
                AdventureObject hintObject = new AdventureObject(npcHintOrItem, object.getDescription());
                itemNPC.setItem(hintObject);
                itemNPC.setInteractBehaviour(new GiveItemInteract(itemNPC));
                Room location = this.game.getRooms().get(Integer.parseInt(npcLocation));
                location.addNPC(itemNPC);
            } else if (npcType.equals("ShopOwner")) {
                ShopOwner merchant = new ShopOwner(npcName);
                merchant.setTalkBehaviour(npcDefaultText);
                merchant.setInteractBehaviour(new ShopInteract(merchant));
                // TODO: shop associated with merchant should be initiated?
                Room location = this.game.getRooms().get(Integer.parseInt(npcLocation));
                location.addNPC(merchant);}
            }
    }

    /**
     * Parse Files other than Rooms, Objects and Synonyms
     *
     * @param fileName the file to parse
     */
    public String parseOtherFile(String fileName) throws IOException {
        String text = "";
        fileName = this.adventureName + "/" + fileName + ".txt";
        BufferedReader buff = new BufferedReader(new FileReader(fileName));
        String line = buff.readLine();
        while (line != null) { // while not EOF
            text += line+"\n";
            line = buff.readLine();
        }
        return text;
    }

}
