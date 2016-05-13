/******************
 * A class that sorts file input
 *
 * java -s file.txt
 *   (for selection sort)
 *
 * java -m file.txt
 *   (for merge sort)
 *   
 * java -q file.txt
 *   (for quick sort)
 *   
 * java -r file.txt
 *   (for radix sort)
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
		} else if(args[0].equals("-q")) {
			sorttype = 'q';
			if(args.length < 2) { usage(); }
			filename = args[1];
		} else if(args[0].equals("-r")) {
			sorttype = 'r';
			if(args.length < 2) { usage(); }
			filename = args[1];
		} else{
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
		} else if(sorttype == 'q') {
			quickSort(arr);
		} else if(sorttype == 'r') {
			radixSort(arr);
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
	
	/**
	 * Sorts the specified array of objects using quick sort.
	 * 
	 * @param arr the array to be sorted
	 */
	public static void quickSort(String[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}
	
	private static void quickSort(String[] arr, int min, int max) {
		if(min < max) {
			// create partitions
			int index = partition(arr, min, max);
			// sort lower partition
			quickSort(arr, min, index - 1);
			// sort higher partition
			quickSort(arr, index + 1, max);
		}
	}
	
	/**
	 * 
	 * @param arr array to be sorted
	 * @param min minimum index in the range to be sorted 
	 * @param max maximum index in the range to be sorted
	 * @return the index where arr will be partitioned
	 */
	private static int partition(String[] arr, int min, int max) {
		String partitionElement;
		int l, r;
		int mid = (min + max) / 2;
		// use the middle element as the partition element
		partitionElement = arr[mid];
		// move it to the front so the rest of the array can be re-ordered
		arr[mid] = arr[min];
		arr[min] = partitionElement;
		l = min;
		r = max;
		
		while(l < r) {
			// search for an element => partitionElement
			while(l < r && arr[l].compareTo(partitionElement) <= 0) {
				l++;
			}
			
			// search for an element < partionElement
			while(arr[r].compareTo(partitionElement) > 0) {
				r--;
			}
			
			// swap elements
			if(l < r) {
				String tmp = arr[l];
				arr[l] = arr[r];
				arr[r] = tmp;
			}
		}
		
		// replace the partition element
		String tmp = arr[min];
		arr[min] = arr[r];
		arr[r] = tmp;
		return r;
	}

	public static void radixSort(String[] arr) {
		int greatestNumDigits = 0;
		Queue<String>[] digitQueues = (Queue<String>[])(new Queue[10]);
		// fill digitQueues with Queue objects
		for(int digVal = 0; digVal < 10; digVal++) {
			digitQueues[digVal] = new Queue<String>();
		}
		
		// find the highest place value
		for(int i = 0; i < arr.length; i++) {
			if(arr[i].length() > greatestNumDigits) {
				greatestNumDigits = arr[i].length();
			}
		}
		
		for(int pos = 0; pos < greatestNumDigits; pos++) {
			int placeVal = (int)(Math.pow(10, pos));
			for(int i = 0; i < arr.length; i++) {
				int digit = 0;
				// isolate the desired digit
				if(arr[i].length() > pos) {
					digit = (Integer.valueOf(arr[i]) % (placeVal * 10)) / placeVal;
				}
				
				System.out.println(arr[i] + " " + (pos + 1));
				System.out.println(digit);
				// add to the correct queue
				digitQueues[digit].enqueue(arr[i]);
				
			}
			System.out.println();
			// replace numbers into intArr in the new order
			int index = 0;
			for(int digit = 0; digit < 10; digit++) {
				while(!digitQueues[digit].isEmpty()) {
					arr[index] = digitQueues[digit].dequeue();
					index++;
				}
			}
		}
	}
}
