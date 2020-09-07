import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

public class SearchAndSort {
  public static int bs(int[] nums, int value, int min, int max) {
    if (min > max)
      return -1;
    int mid = (max + min) / 2;
    if (nums[mid] == value)
      return mid;
    else if (nums[mid] > value)
      return bs(nums, value, min, mid - 1);
    else
      return bs(nums, value, mid + 1, max);
  }

  public static void swap(int[] a, int i, int j) {
    if (i == j)
      return;
    int t = a[i];
    a[i] = a[j];
    a[j] = t;
  }

  public static void selectionSort(int[] nums) {
    for (int i = 0; i < nums.length - 1; ++i) {
      int minIdx = i;
      for (int j = i + 1; j < nums.length; ++j) {
        if (nums[j] < nums[minIdx])
          minIdx = j;
      }
      swap(nums, i, minIdx);
    }
  }

  public static void insertionSort(int[] nums) {
    // [2, 3, 1]
    // [2, 3, 3]
    // [2, 2, 3]
    // [1, 2, 3]
    for (int i = 0; i < nums.length; ++i) {
      int key = nums[i];
      int j = i - 1;
      while (j >= 0 && key < nums[j]) {
        nums[j + 1] = nums[j];
        j--;
      }
      nums[j + 1] = key;
    }
  }

  public static String printArray(int[] a) {
    return Arrays.toString(a);
  }

  public static void merge(int[] nums, int[] a, int[] b) {
    System.out.println("merging: " + printArray(a) + " and " + printArray(b));
    int p1 = 0, p2 = 0;
    for (int i = 0; i < nums.length; ++i) {
      if (p1 < a.length && p2 < b.length) {
        if (a[p1] <= b[p2]) {
          nums[i] = a[p1];
          p1++;
        } else {
          nums[i] = b[p2];
          p2++;
        }
      } else if (p1 < a.length) {
        nums[i] = a[p1];
        p1++;
      } else {
        nums[i] = b[p2];
        p2++;
      }
    }
  }

  public static void mergeSort(int[] nums) {
    if (nums.length <= 1)
      return;
    int[] left = Arrays.copyOfRange(nums, 0, nums.length / 2); // [0, mid[
    int[] right = Arrays.copyOfRange(nums, nums.length / 2, nums.length); // [mid, N[
    mergeSort(left);
    mergeSort(right);
    merge(nums, left, right);
  }

  public static void main(String[] args) {
    int[] nums1 = {1, 4, 21, 100, 124};
    int idx = bs(nums1, 1, 0, nums1.length - 1);
    System.out.println(idx);
    int[] nums2 = {2, 1, 10, 5};
    insertionSort(nums2);
    System.out.println(Arrays.toString(nums2));
    int[] res = new int[nums1.length + nums2.length];
    merge(res, nums1, nums2);
    System.out.println(Arrays.toString(res));
    int[] nums3 = {10, 11, 5, 20, 100};
    mergeSort(nums3);
    System.out.println(Arrays.toString(nums3));
  }
}
