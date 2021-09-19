
// Written by Elvin Latifi

import java.util.ArrayList;
import java.util.Arrays;

public class User {

    private String name;
    private Dog[] ownedDogs = new Dog[0];
    private ArrayList<Bid> madeBids = new ArrayList<Bid>();

    public User(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void addDog(Dog dog) {
        Dog[] updatedArray = new Dog[ownedDogs.length + 1];
        for (int i = 0; i < ownedDogs.length; i++) {
            updatedArray[i] = ownedDogs[i];
        }
        updatedArray[ownedDogs.length] = dog;
        ownedDogs = updatedArray;
    }

    public String getDogs() {
        return Arrays.toString(ownedDogs);
    }

    public ArrayList<Dog> getDogList() {
        ArrayList<Dog> dogList = new ArrayList<Dog>(Arrays.asList(ownedDogs));
        return dogList;
    }

    public void removeOwnedDog(Dog dogToBeRemoved) {
        for (int i = 0; i < ownedDogs.length; i++) {
            if (ownedDogs[i] == dogToBeRemoved) {
                for (int j = i; j < ownedDogs.length - 1; j++) {
                    ownedDogs[j] = ownedDogs[j+1];
                }
            }
        }
        Dog[] updatedArray = new Dog[ownedDogs.length - 1];
        for (int i = 0; i < ownedDogs.length - 1; i++) {
            updatedArray[i] = ownedDogs[i];
        }
        ownedDogs = updatedArray;
    }

    public ArrayList<Bid> getBids() {
        ArrayList<Bid> copy = new ArrayList<Bid>(madeBids);
        return copy;
    }

    public void addMadeBid(Bid bid) {
        madeBids.add(bid);
    }
}