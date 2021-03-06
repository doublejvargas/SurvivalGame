package model;

public class Bush extends  StationaryObject{

    int numberOfBerries;
    boolean hasBerries;


    public Bush( Vector2 position, Game game){
        super(position, game, true);
        numberOfBerries = 1;
        hasBerries = true;
    }

    @Override
    public void interact(Player player){
        if (numberOfBerries > 0) {
            player.getInventory().insertFood(Food.FoodType.Berries);
        }
        numberOfBerries = 0;
    }

    public int getNumberOfBerries(){
        return numberOfBerries;
    }

    public boolean getHasBerries(){
        return hasBerries;
    }


    public int gatherBerries(){
        hasBerries = false;
        int returnVal = numberOfBerries;
        numberOfBerries = 0;
        return returnVal;
    }
}