package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 33. Search in Rotated Sorted Array
 * 
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * 
 * You may assume no duplicate exists in the array.
 * 
 * @author Hxkandwal
 */
public class SearchInRotatedSortedArray extends AbstractCustomTestRunner {
	
	private static SearchInRotatedSortedArray _instance = new SearchInRotatedSortedArray();
	
	private SearchInRotatedSortedArray() {}
	
	public int _search(int[] nums, int target) {
		int low = 0, high = nums.length - 1;
        
        while (low <= high) {
        	int mid = (low + high + 1) >>> 1;
        	if (nums [mid] > target) {
        		if (nums [low] < target) high = mid - 1;
        		else if (nums [low] > target) low = mid + 1;
        		else return low;
        	} else if (nums [mid] < target) {
        		if (nums [high] > target) low = mid + 1;
        		else if (nums [high] < target) high = mid - 1; 
        		else return high;
        	} else return mid;
        }
		
        return -1;
    }

	// driver method
	public static void main(String[] args) {
//		_instance.runTest(new int [] { 4, 5, 6, 7, 0, 1, 2 }, 1, 5);
//		_instance.runTest(new int [] {}, 5, -1);
//		_instance.runTest(new int [] { 1, 3 }, 0, -1);
//		_instance.runTest(new int [] { 1, 3 }, 3, 1);
//		_instance.runTest(new int [] { 1, 3 }, 1, 0);
		_instance.runTest(new int [] { 7, 8, 1, 2, 3, 4, 5, 6 }, 2, 3);
	}

	public void runTest(final int[] nums, final int target, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums, target });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	

}