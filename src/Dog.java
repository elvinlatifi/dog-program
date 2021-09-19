
// Written by Elvin Latifi

public class Dog {

    private String name;
    private String breed;
    private int age;
    private int weight;
    private double tailLength;
    private User owner;
    private boolean forSale;

    public Dog(String name, String breed, int age, int weight) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;

        if (breedIsDachshund()) {
            final double STANDARD_TAX_TAIL_LENGTH = 3.7;
            tailLength = STANDARD_TAX_TAIL_LENGTH;
        }
        else {
            calculateTailLength();
        }
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    private boolean breedIsDachshund() {
        return breed.equalsIgnoreCase("tax") || breed.equalsIgnoreCase("dachshund");
    }

    public int getAge() {
        return age;
    }

    public void increaseAge(int addedYears) {
        if (addedYears < 0) {
            System.out.println("Error: Age can not be decreased.");
            return;
        }
        age += addedYears;
        if (breedIsDachshund()) {
            return;
        }
        calculateTailLength();
    }

    public int getWeight() {
        return weight;
    }

    public double getTailLength() {
        return tailLength;
    }

    private void calculateTailLength() {
        final double TAIL_LENGTH_DENOMINATOR = 10.0;
        tailLength = age * (weight / TAIL_LENGTH_DENOMINATOR);
    }

    public User getOwner() {
        return owner;
    }

    public String getOwnerName() {
        return owner.getName();
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void changeSaleStatus() {
        forSale = !forSale;
    }

    public boolean isForSale() {
        return forSale;
    }

    public String toString() {
        return String.format("%s", name);
    }
}