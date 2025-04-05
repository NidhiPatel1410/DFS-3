// In square we have to form 4 sides, so first we calc the side of square. For that we calc the total sum and divide by 4. If we calc
// the sum and we see that sum%4 != 0 then simply return false, because we cannot break matchsticks, we have to use all matchsticks.
// Then we sort the array so that the higher value we get in first, so when we call dfs, it will break immediately if the value is 
// greater than max side value that we can have. Else, we carry an array of size 4, then we keep on adding at each position one by one
// till the value at that index becomes == side. we only move forward to fill the other index if the current index is filled. Otherwise
// if we backtrack.

// Time Complexity : O(4^n) 
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Solution {
    public boolean makesquare(int[] matchsticks) {
        // Base case
        if (matchsticks == null || matchsticks.length < 4) {
            return false;
        }
        int sum = 0;
        // Compute sum
        for (int m : matchsticks) {
            sum = sum + m;
        }
        // If not divisible by 4, we cannot form square
        if (sum % 4 != 0) {
            return false;
        }
        // Calc side
        int side = sum / 4;
        // Sort
        Arrays.sort(matchsticks);
        // And reverse
        matchsticks = reverse(matchsticks);
        // Call backtrack method
        return backtrack(matchsticks, 0, new int[4], side);
    }

    // For reverse
    private int[] reverse(int[] matchsticks) {
        // Take pointers at start and at end, and run loop and swap positions and
        // changes indices
        int l = 0;
        int r = matchsticks.length - 1;
        while (l < r) {
            swap(matchsticks, l, r);
            l++;
            r--;
        }
        return matchsticks;
    }

    // Swap
    private void swap(int[] matchsticks, int l, int r) {
        int temp = matchsticks[l];
        matchsticks[l] = matchsticks[r];
        matchsticks[r] = temp;
    }

    private boolean backtrack(int[] matchsticks, int index, int[] square, int side) {
        // Base case
        if (index == matchsticks.length) {
            return true;
        }
        // Logic
        // For all positions in array
        for (int i = 0; i < 4; i++) {
            // Check if adding the current matchstick to the index val does not exceeds the
            // side, only then perform the steps
            if (square[i] + matchsticks[index] <= side) {
                // Action
                square[i] = square[i] + matchsticks[index];
                // Recurse
                if (backtrack(matchsticks, index + 1, square, side)) {
                    return true;
                }
                // Backtrack
                square[i] = square[i] - matchsticks[index];
            }
        }
        return false;
    }

}