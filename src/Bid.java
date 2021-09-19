
// Written by Elvin Latifi

public class Bid {
    private int amount;
    private User bidOwner;

    public Bid(int amount, User bidOwner) {
        this.amount = amount;
        this.bidOwner = bidOwner;
    }

    public int getAmount() {
        return amount;
    }

    public User getBidOwner() {
        return bidOwner;
    }

    public String toString() {
        return String.format("%s %d kr", bidOwner.getName(), amount);
    }
}