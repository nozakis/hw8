/******************
 * A class that sorts file input
 *
 * java -s file.txt
 *   (for selection sort)
 *
 * java -m file.txt
 *   (for merge sort)
 *
 * @author Andy Exley
 *****************/

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;

public class Sort {

  public static void main(String[] args) {
    if(args.length < 1) {
      usage();
    }
    
    char sorttype = 's';
    String filename = "";

    if(args[0].equals("-m")) {
      sorttype = 'm';
      if(args.length < 2) { usage(); }
      filename = args[1];
    } else if(args[0].equals("-s")) {
      sorttype = 's';
      if(args.length < 2) { usage(); }
      filename = args[1];
    } else {
      System.err.println("No sort specified, using selection sort");
      filename = args[0];
    }

    String[] arr = null;
    File tfile = new File(filename);
    try {
      ArrayList<String> list = new ArrayList<String>();
      Scanner scan = new Scanner(tfile);
      while(scan.hasNextLine()) {
        list.add(scan.nextLine());
      }
      arr = new String[list.size()];
      list.toArray(arr);
    } catch(FileNotFoundException e) {
      System.err.println("Error, cannot find file: " + filename);
      System.exit(1);
    }

    if(sorttype == 'm') {
      mergeSort(arr);
    } else {
      selectionSort(arr);
    }

    for(String line : arr) {
      System.out.println(line);
    }
  }

  /************
   * Prints out an error message explaining how to use this program
   * Then quits
   ****************/
  public static void usage() {
    System.err.println("Usage:\n\tjava Sort <sorttype> filename");
    System.err.println(" Where <sorttype> is:\n  -m Merge Sort\n -s Selection Sort");
    System.exit(1);
  }

  /*****
   * Merge sort helper method
   * @param arr The array to sort
   ******/
  public static void mergeSort(String[] arr) {
    mergeSortRec(arr, 0, arr.length - 1);
  }

  /*********
   * Merge sort recursive method
   * @param arr The array to sort
   * @param beg The beginning index to sort
   * @param end The end index to sort
   *********/
  private static void mergeSortRec(String[] arr, int beginIndex, int endIndex) {
    if(endIndex > beginIndex) {
      int a1length = (endIndex-beginIndex+1)/2;
      int a1end = beginIndex+a1length - 1;
      mergeSortRec(arr, beginIndex, beginIndex + (endIndex-beginIndex+1)/2 - 1);
      mergeSortRec(arr, a1end+1, endIndex);
      merge(arr,beginIndex,a1end,a1end+1,endIndex);
    } else {
      return;
    }
  }

  /****************
   * Merge step of merge sort algorithm
   * @param arr The array to sort
   * @param a1start Where the first sub-array begins
   * @param a1end Where the first sub-array ends
   * @param a2start Where the second sub-array begins
   * @param a2end Where the second sub-array ends
   **************/
  private static void merge(String[] arr, int a1start, int a1end, int a2start, int a2end) {
    String[] tmp = new String[a2end - a1start + 1]; 
    int tmpindex = 0;
    int start = a1start;
    while(a1start <= a1end && a2start <= a2end) {
      if(arr[a1start].compareTo(arr[a2start]) < 0) {
        tmp[tmpindex] = arr[a1start];
        a1start++;
      } else {
        tmp[tmpindex] = arr[a2start];
        a2start++;
      }
      tmpindex++;
    }
    // copy remaining items in array 1
    while(a1start <= a1end) {
      tmp[tmpindex] = arr[a1start];
      a1start++;
      tmpindex++;
    }
    // copy remaining items in array 2
    while(a2start <= a2end) {
      tmp[tmpindex] = arr[a2start];
      a2start++;
      tmpindex++;
    }
    // copy from temp array back into arr
    for(int i = start; i <= a2end; i++) {
      arr[i] = tmp[i - start];
    }
  }

  /*********
   * Selection sort method
   * @param arr The array to sort
   ******/
  public static void selectionSort(String[] arr) {
    for(int i = 0; i < arr.length; i++) {
      // find next smallest
      int smallestIndex = findNextSmallest(arr, i);
      
      // swap
      String tmp = arr[i];
      arr[i] = arr[smallestIndex];
      arr[smallestIndex] = tmp;
    }
  }

  /*************
   * Finds the index of the smallest item in arr, from n to arr.length-1
   * @param arr The array to search
   * @param n The index to start looking
   * @return The index of the smallest item
   **********/
  private static int findNextSmallest(String[] arr, int n) {
    int minindex = n;
    for(int i = n+1; i < arr.length; i++) {
      if(arr[i].compareTo(arr[minindex]) < 0) {
        minindex = i;
      }
    }
    return minindex;
  }

}
