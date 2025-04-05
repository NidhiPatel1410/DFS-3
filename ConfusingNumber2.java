
// In this problem, we have stored all the numbers that gives valid digit after 180 rotation in a hashmap. Then performing a dfs
// and one by one checking if the current number is a confusing number, if yes adding 1 to the count. Then generating other 5 numbers
// which might be confusing and calling dfs on them.

// Time Complexity : O(5^L) because for every number we are generating 5 new numbers i.e 5 dfs calls, and L is the number of digits 
// in number, that is length
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
import java.util.*;

class ConfusingNumber {
    int count;
    HashMap<Integer, Integer> map;

    public int countConfusingNumber(int n) {
        // Base case
        if (n == 0 || n == 1) {
            return 0;
        }
        // Put all numbers between 0 to 9 that gives valid digit after 180 rotation in
        // map, and the value as their value after rotation
        map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        map.put(6, 9);
        map.put(8, 8);
        map.put(9, 6);
        // Call dfs, start from 0
        dfs(0, n);
        // Return the count
        return count;
    }

    private void dfs(long curr, int n) {
        // Base - If the number becomes greater than n, return
        if (curr > n) {
            return;
        }
        // Logic
        // Check if the number is a confusing number, increment count
        if (isConfusingNumber(curr)) {
            count++;
        }
        // Now, generate new 5 numbers by adding all the keys as the next digit to
        // current Eg. curr=1 and map has one of the key 6, so newNum=16
        for (long key : map.keySet()) {
            // Compute the newNum
            long newNum = curr * 10 + key;
            // If it is not 0 call dfs, because since first curr=0 and first map value also
            // 0 so newNum=0*10 + 0 which is 0, it will go in infinite loop and give stack
            // overflow
            if (newNum != 0) {
                // Call dfs on newNum
                dfs(newNum, n);
            }
        }
    }

    private boolean isConfusingNumber(long num) {
        // For checking if it is confusing or not, just reverse the number and replace
        // all it's digits with the value in map
        long temp = num;
        long rem = 0;
        while (temp > 0) {
            int last = (int) temp % 10;
            rem = rem * 10 + map.get(last);
            temp = temp / 10;
        }
        return num != rem;
    }

    public static void main(String[] args) {
        ConfusingNumber c = new ConfusingNumber();
        System.out.println(c.countConfusingNumber(10));
    }
}