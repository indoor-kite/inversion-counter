import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class InversionCounter {
    public static long merge(int arr[], int leftPos, int midPos, int rightPos) {

        //Create temp sub arrays
        int leftTemp[] = Arrays.copyOfRange(arr, leftPos, midPos + 1);
        int rightTemp[] = Arrays.copyOfRange(arr, midPos + 1, rightPos + 1);


        //merge the temp arrays
        int i = 0, j = 0, k = leftPos;
        long splitInv = 0;

        while (i < leftTemp.length && j < rightTemp.length) {
            if (leftTemp[i] <= rightTemp[j]) {
                arr[k++] = leftTemp[i++];
            } else {
                arr[k++] = rightTemp[j++];
                //add the split inversion count
                splitInv += (midPos + 1) - (leftPos + i);
            }
        }

        while (i < leftTemp.length) {
            arr[k++] = leftTemp[i++];

        }
        while (j < rightTemp.length) {
            arr[k++] = rightTemp[j++];

        }
        return splitInv;
    }

    public static long sort(int arr[], int leftPos, int rightPos) {
        long totalInv = 0;
        if (leftPos < rightPos) {
            // Find the middle point
            int midPos = (leftPos + rightPos) / 2;

            // Sort first and second halves
            totalInv += sort(arr, leftPos, midPos);
            totalInv += sort(arr, midPos + 1, rightPos);

            // Merge the sorted halves
            totalInv += merge(arr, leftPos, midPos, rightPos);
        }
        return totalInv;
    }


    public static void main(String[] args) throws IOException {
        // import necessary packages


        Scanner reader = new Scanner(new FileReader("data.txt"));

        // arraylist to store strings
        //List<Integer> listOfStrings = new ArrayList<Integer>();

        int[] values = new int[100000];
        int count = 0;

        // checking end of file
        while (reader.hasNextInt()) {
            // adding each string to arraylist
            values[count] = reader.nextInt();
            count++;
        }

        /* iterating over an array
        for (int i : values) {

            System.out.print(i + " ");
        }*/
        System.out.println(sort(values, 0, values.length - 1));

    }
}

