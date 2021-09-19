import java.util.ArrayList;
import java.util.Collections;

// Skriven av Elvin Latifi ella2817

public class DogSorter {

    public void sort(ArrayList<Dog> list) {
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
}

