
// Written by Elvin Latifi

import java.util.ArrayList;
import java.util.Collections;

public class DogProgram {

    private ArrayList<Dog> dogRegister = new ArrayList<Dog>();
    private ArrayList<User> userRegister = new ArrayList<User>();
    private ArrayList<Auction> auctionRegister = new ArrayList<Auction>();

    private InputHandler input = new InputHandler();

    public static void main(String[] args) {
        DogProgram program = new DogProgram();
        program.run();
    }

    private void run() {
        startUp();
        runCommandLoop();
        closeDown();
    }

    private void startUp() {
        System.out.println("Hello and welcome to the dog-program!");
        System.out.println("Please enter a command.");
    }

    private void runCommandLoop() {
        String command;
        do {
            command = readCommand();
            handleCommand(command);
        } while (!(command.equals("Exit")));
    }

    private void closeDown() {
        System.out.println("Goodbye!");
    }

    private String readCommand() {
        return input.readString("Command?> ");
    }

    private void handleCommand(String command) {
        switch (command) {
            case "Register new dog":
                registerNewDog();
                break;
            case "Increase age":
                increaseAge();
                break;
            case "List dogs":
                listDogs();
                break;
            case "Remove dog":
                removeDog();
                break;
            case "Register new user":
                registerNewUser();
                break;
            case "Give dog":
                giveDog();
                break;
            case "List users":
                listUsers();
                break;
            case "Remove user":
                removeUser();
                break;
            case "Start auction":
                startAuction();
                break;
            case "Make bid":
                makeBid();
                break;
            case "List auctions":
                listAuctions();
                break;
            case "List bids":
                listBids();
                break;
            case "Close auction":
                closeAuction();
                break;
            case "Exit":
                break;
            default:
                System.out.println("Error: Command not recognized");
        }
    }

    private void sortDogs(ArrayList<Dog> list) {
        for (int i = 0; i < list.size()-1; i++) {
            Dog dogAtMinIndex = list.get(i);
            double currentMin = dogAtMinIndex.getTailLength();
            int currentMinIndex = i;

            for (int j = i+1; j < list.size(); j++) {
                dogAtMinIndex = list.get(currentMinIndex);
                Dog dogToBeCompared = list.get(j);

                if (shouldDogsSwap(currentMin, dogAtMinIndex, dogToBeCompared)) {
                    currentMin = dogToBeCompared.getTailLength();
                    currentMinIndex = j;
                }
            }
            Collections.swap(list, currentMinIndex, i);
        }
    }

    private boolean shouldDogsSwap(double currentMin, Dog dogAtMinIndex, Dog dogToBeCompared) {
        return currentMin > dogToBeCompared.getTailLength() || currentMin == dogToBeCompared.getTailLength() && dogAtMinIndex.getName().compareToIgnoreCase(dogToBeCompared.getName()) > 0;
    }

    private void registerNewDog() {
        String name = input.readString("Name?> ");
        String breed = input.readString("Breed?> ");
        int age = input.readInt("Age?> ");
        int weight = input.readInt("Weight?> ");

        Dog dog = new Dog(name, breed, age, weight);
        dogRegister.add(dog);
        System.out.println(name + " added to the register");
    }

    private void listDogs() {
        if (!dogRegister.isEmpty()) {
            sortDogs(dogRegister);
            double minTailLength = input.readDouble("Smallest tail length to display?> ");
            for (Dog dog : dogRegister) {
                if (dog.getTailLength() >= minTailLength) {
                    if (dog.getOwner() != null) {
                        System.out.printf("* %s (%s, %d years, %d kilo, %.2f cm tail, owned by %s)", dog.getName(), dog.getBreed(), dog.getAge(), dog.getWeight(), dog.getTailLength(), dog.getOwnerName());
                        System.out.println();
                    }
                    else {
                        System.out.printf("* %s (%s, %d years, %d kilo, %.2f cm tail)", dog.getName(), dog.getBreed(), dog.getAge(), dog.getWeight(), dog.getTailLength());
                        System.out.println();
                    }
                }
            }
        }
        else {
            System.out.println("Error: Register is empty");
        }
    }

    private void increaseAge() {
        String nameOfDog = input.readString("Enter the name of the dog?> ");
        Dog dog = findDog(nameOfDog);
        if (dog == null) {
            return;
        }
        dog.increaseAge(1);
        System.out.println(nameOfDog + " is now one year older");
    }

    private void removeDog() {
        String nameOfDog = input.readString("Enter the name of the dog?> ");
        Dog dogToBeRemoved = findDog(nameOfDog);
        if (dogToBeRemoved == null) {
            return;
        }
        if (dogToBeRemoved.getOwner() != null) {
            User owner = dogToBeRemoved.getOwner();
            owner.removeOwnedDog(dogToBeRemoved);
        }
        if (dogToBeRemoved.isForSale()) {
            auctionRegister.remove(findAuction(dogToBeRemoved));
        }
        dogRegister.remove(dogToBeRemoved);
        System.out.println(nameOfDog + " is removed from the register");
    }

    private void registerNewUser() {
        String name = input.readString("Name?> ");
        User user = new User(name);
        userRegister.add(user);
        System.out.println(name + " added to the register");
    }

    private void giveDog() {
        Dog dogToBeAdded;
        User owner;

        String nameOfDog = input.readString("Enter the name of the dog?> ");
        dogToBeAdded = findDog(nameOfDog);
        if (dogToBeAdded == null) {
            return;
        }
        if (dogToBeAdded.getOwner() != null) {
            System.out.println("Error: The dog already has an owner");
            return;
        }

        String nameOfUser = input.readString("Enter the name of the new owner?> ");
        owner = findUser(nameOfUser);
        if (owner == null) {
            return;
        }

        owner.addDog(dogToBeAdded);
        dogToBeAdded.setOwner(owner);
        System.out.println(nameOfUser + " now owns " + nameOfDog);
    }

    private Dog findDog(String nameOfDog) {
        for (Dog dog : dogRegister) {
            if (nameOfDog.equals(dog.getName())) {
                return dog;
            }
        }
        System.out.println("Error: Dog not found in register");
        return null;
    }

    private User findUser(String nameOfUser) {
        for (User user : userRegister) {
            if (nameOfUser.equals(user.getName())) {
                return user;
            }
        }
        System.out.println("Error: User not found in register");
        return null;
    }

    private void listUsers() {
        if (!userRegister.isEmpty()) {
            for (User user : userRegister) {
                System.out.println(user.getName() + " " + user.getDogs());
            }
        }
        else {
            System.out.println("Error: Register is empty");
        }
    }

    private void removeUser() {
        String nameOfUser = input.readString("Enter the name of the user?> ");
        User user = findUser(nameOfUser);
        if (user == null) {
            return;
        }
        if (!user.getBids().isEmpty()) {
            ArrayList<Bid> usersBids = user.getBids();
            removeUsersBids(usersBids);
        }
        ArrayList<Dog> userDogList = user.getDogList();
        removeUsersDogs(userDogList);
        userRegister.remove(user);
        System.out.println(nameOfUser + " is removed from the register");
    }

    private void removeUsersDogs(ArrayList<Dog> userDogList) {
        for (Dog dog : userDogList) {
            dogRegister.remove(dog);
        }
    }

    private void removeUsersBids(ArrayList<Bid> usersBids) {
        for (Auction auction : auctionRegister) {
            for (Bid userBid : usersBids) {
                for (Bid bidInAuction : auction.getListOfBids()) {
                    if (userBid == bidInAuction) {
                        auction.removeBid(bidInAuction);
                    }
                }
            }
        }
    }

    private void startAuction() {
        String nameOfDog = input.readString("Enter the name of the dog?> ");
        Dog dogToBeSold = findDog(nameOfDog);
        if (dogToBeSold == null) {
            return;
        }
        if (dogToBeSold.isForSale()) {
            System.out.println("Error: This dog is already for sale");
            return;
        }
        if (dogToBeSold.getOwner() != null) {
            System.out.println("Error: The dog already has an owner");
            return;
        }

        Auction auction = new Auction(dogToBeSold);
        auctionRegister.add(auction);
        dogToBeSold.changeSaleStatus();
        System.out.println(nameOfDog + " has been put up for auction in auction #" + auction.getNumber());
    }

    private void makeBid() {
        String nameOfUser = input.readString("Enter the name of the user?> ");
        User user = findUser(nameOfUser);
        if (user == null) {
            return;
        }
        String nameOfDog = input.readString("Enter the name of the dog?> ");
        Dog dog = findDog(nameOfDog);
        if (dog == null) {
            return;
        }
        Auction auction = findAuction(dog);
        if (auction == null) {
            return;
        }
        handleBidding(auction, user);
    }

    private void handleBidding(Auction auction, User user) {
        Bid highestBid = null;
        if (!auction.getListOfBids().isEmpty()) {
            highestBid = auction.getListOfBids().get(0);
        }
        int highestBidAmount = 0;
        if (highestBid != null) {
            highestBidAmount = highestBid.getAmount();
        }
        int bidAmount;
        do {
            bidAmount = input.readInt("Amount to bid(min " + highestBidAmount + ")?> ");
            if (bidAmount <= highestBidAmount) {
                System.out.println("Error: Bid is too low!");
            }
            else {
                Bid newBid = new Bid(bidAmount, user);
                auction.addBid(newBid);
                user.addMadeBid(newBid);
                System.out.println("Bid made");
            }
        } while (bidAmount <= highestBidAmount);
    }

    private void listAuctions() {
        if (!auctionRegister.isEmpty()) {
            for (Auction auction : auctionRegister) {
                System.out.println(auction);
            }
        }
        else {
            System.out.println("Error: There are currently no auctions in progress");
        }
    }

    private void listBids() {
        String nameOfDog = input.readString("Enter the name of the dog?> ");
        Dog dog = findDog(nameOfDog);
        if (dog == null) {
            return;
        }
        Auction auction = findAuction(dog);
        if (auction == null) {
            return;
        }
        auction.listBids();
    }

    private void closeAuction() {
        String nameOfDog = input.readString("Enter the name of the dog?> ");
        Dog dog = findDog(nameOfDog);
        if (dog == null) {
            return;
        }
        Auction auction = findAuction(dog);
        if (auction == null) {
            return;
        }

        if (!auction.getListOfBids().isEmpty()) {
            rewardWinner(auction);
            auctionRegister.remove(auction);
            dog.changeSaleStatus();
            System.out.println("The auction is closed. The winning bid was " + auction.getListOfBids().get(0).getAmount() + "kr and was made by " + auction.getListOfBids().get(0).getBidOwner().getName());
            return;
        }
        auctionRegister.remove(auction);
        dog.changeSaleStatus();
        System.out.println("The auction is closed. No bids were made for " + dog.getName());
    }

    private Auction findAuction(Dog dog) {
        if (!dog.isForSale()) {
            System.out.println("Error: This dog is not up for auction");
            return null;
        }
        for (Auction auction : auctionRegister) {
            if (auction.getDogForSale() == dog) {
                return auction;
            }
        }
        return null;
    }

    private void rewardWinner(Auction auction) {
        User auctionWinner = auction.getListOfBids().get(0).getBidOwner();
        Dog dog = auction.getDogForSale();
        auctionWinner.addDog(dog);
        dog.setOwner(auctionWinner);
    }
}