import java.util.Arrays;

public class SearchAndSort {
	public static void main(String[] args) {
		int[] array = {10, 80, 30, 90, 40, 50, 70};
//		System.out.println(binarySearch(array, 10));
//		System.out.println(findPeek(array));
//		System.out.println(recFindPeek(array, 0, array.length - 1));
//		bubbleSort(array);
//		selectionSort(array);
//		System.out.println(Arrays.toString(array));
//		insertionSort(array);
//		System.out.println(Arrays.toString(array));
//		mergeSort(array, 0, array.length - 1);
//		quickSort(array, 0, array.length - 1);
		System.out.println(Arrays.toString(array));
	}
	
	public static int binarySearch(int[] array, int target) { // O(logn)
	    int min = 0;
	    int max = array.length - 1;
	    while (min <= max) {
	        int mid = (min + max) / 2;
	        if (array[mid] < target)
	            min = mid + 1;
	        else if (array[mid] > target)
	            max = mid - 1;
	        else
	            return mid;
	    }
	    return -1;
	}
	
	// If array[n / 2] < array[n / 2 - 1] then look at left half
	// Else if array[n / 2] < array[n / 2 + 1] then look at right half
	// Else array[n / 2] is a peek
	public static int findPeek(int[] array) { // O(logn)
		int start = 0, end = array.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if ((mid == 0 || array[mid] >= array[mid - 1]) && 
					(mid == array.length - 1 || array[mid] >= array[mid + 1]))
				return mid;
			else if (mid > 0 && array[mid - 1] > array[mid])
				end = mid - 1;
			else
				start = mid + 1;
		}
		return -1;
	}
	
	public static int recFindPeek(int[] array, int start, int end) { // O(logn)
		int mid = (start + end) / 2;
		if ((mid == 0 || array[mid] >= array[mid - 1]) && 
				(mid == array.length - 1 || array[mid] >= array[mid + 1]))
			return mid;
		else if (mid > 0 && array[mid - 1] > array[mid])
			return recFindPeek(array, (mid - 1), end);
		else
			return recFindPeek(array, (mid + 1), end);
	}
	
	public static void bubbleSort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 1; j < array.length - i; j++) {
				if (array[j - 1] < array[j]) {
					int temp = array[j - 1];
		        	array[j - 1] = array[j];
		        	array[j] = temp;
		        }
		    }
		}
	}
	
	public static void selectionSort(int[] array) { // O(n^2)
		for (int i = 0; i < array.length - 1; i++) {
			int minIdx = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[minIdx])
					minIdx = j;
			}
			int temp = array[minIdx];
			array[minIdx] = array[i];
			array[i] = temp;
		}
	}

	public static void insertionSort(int[] array) { // O(n^2)
		for (int i = 1; i < array.length; i++) {
			int key = array[i];
			int j = i - 1;
			while (j >= 0 && key < array[j]) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = key;
		}
	}
	
	public static void merge(int[] array, int start, int mid, int end) {
		int a = mid - start + 1;
		int b = end - mid;
		int[] leftArray = new int[a];
		int[] rightArray  = new int[b];
		for (int i = 0; i < a; i++) {
			leftArray[i] = array[start + i];
		}
		for (int j = 0; j < b; j++) {
			rightArray[j] = array[mid + 1 + j];
		}
		int i = 0, j = 0, k = start;
		while (i < a && j < b) {
			if (leftArray[i] <= rightArray[j]) {
				array[k] = leftArray[i];
				i++;
			} else {
				array[k] = rightArray[j];
				j++;
			}
			k++;
		}
		while (i < a) {
			array[k] = leftArray[i];
			i++;
			k++;
		}
		while (j < b) {
			array[k] = rightArray[j];
			j++;
			k++;
		}
	}
	
	public static void mergeSort(int[] array, int start, int end) { //O(nlogn)
		if (start < end) {
			int mid = (start + end) / 2;
		    mergeSort(array, start, mid);
		    mergeSort(array, mid + 1, end);
		    merge(array, start, mid, end);
		}
	}
	
	public static int partition(int[] array, int low, int high) {
		int pivot = array[high];
		int i = low - 1;
		for (int j = low; j < high; j++) {
			if (array[j] <= pivot) {
				i++;
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}
		int temp = array[i + 1];
		array[i + 1] = array[high];
		array[high] = temp;
		return i + 1;
	}
	
	public static void quickSort(int[] array, int low, int high) {
		if (low < high) {
			int p = partition(array, low, high);
			quickSort(array, low, p - 1);
			quickSort(array, p + 1, high);
		}
	}
	
	
}
