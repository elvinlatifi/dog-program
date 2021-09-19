
// Written by Elvin Latifi

import java.util.ArrayList;
import java.util.Collections;

public class Auction {

    private static int count = 1;
    private int number;
    private Dog dogForSale;
    private ArrayList<Bid> listOfBids = new ArrayList<Bid>();

    public Auction(Dog dogToBeSold) {
        number = count;
        count++;
        dogForSale = dogToBeSold;
    }

    public int getNumber() {
        return number;
    }

    public Dog getDogForSale() {
        return dogForSale;
    }

    public void addBid(Bid newBid) {
        removeBiddersPreviousBid(newBid);
        listOfBids.add(newBid);
        sortBids(listOfBids);
    }

    private void removeBiddersPreviousBid(Bid newBid) {
        Bid bidToBeRemoved = null;
        for (Bid bid : listOfBids) {
            if (newBid.getBidOwner() == bid.getBidOwner()) {
                bidToBeRemoved = bid;
            }
        }
        if (bidToBeRemoved != null) {
            listOfBids.remove(bidToBeRemoved);
        }
    }

    private String getTopThreeBids() {
        sortBids(listOfBids);
        String topThreeBids = "";
        for (int i = 0; i < 3 && i < listOfBids.size(); i++) {
            if (listOfBids.get(i) != null) {
                String bidInfo = " " + listOfBids.get(i).getBidOwner().getName() + " " + listOfBids.get(i).getAmount() + " kr,";
                topThreeBids = topThreeBids + bidInfo;
            }
        }
        if (!topThreeBids.equals("")) {
            topThreeBids = topThreeBids.substring(1, topThreeBids.length()-1);
        }
        return topThreeBids;
    }

    public ArrayList<Bid> getListOfBids() {
        ArrayList<Bid> copy = new ArrayList<Bid>(listOfBids);
        return copy;
    }

    public void removeBid(Bid bid) {
        listOfBids.remove(bid);
    }

    private void sortBids(ArrayList<Bid> list) {
        for (int i = 0; i < list.size()-1; i++) {
            Bid bidAtMaxIndex = list.get(i);
            int currentMax = bidAtMaxIndex.getAmount();
            int currentMaxIndex = i;

            for (int j = i+1; j < list.size(); j++) {
                bidAtMaxIndex = list.get(currentMaxIndex);
                Bid bidToBeCompared = list.get(j);

                if (bidToBeCompared.getAmount() > bidAtMaxIndex.getAmount()) {
                    currentMax = bidToBeCompared.getAmount();
                    currentMaxIndex = j;
                }
            }
            Collections.swap(list, currentMaxIndex, i);
        }
    }

    public void listBids() {
        if (!listOfBids.isEmpty()) {
            sortBids(listOfBids);
            System.out.println("Here are the bids for this auction");
            for (Bid bid : listOfBids) {
                System.out.println(bid);
            }
        }
        else {
            System.out.println("No bids have been registered yet for this auction");
        }
    }

    public String toString() {
        return String.format("Auction #%d: %s. Top bids: [%s]", number, dogForSale.getName(), getTopThreeBids());
    }
}