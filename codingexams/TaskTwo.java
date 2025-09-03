public class TaskTwo {

    /*
     * Part 1: Task is to implement a method that given two arrays
     * (arr1 & arr2), return the first index where arr2 is sub-array
     * of arr1
     * */

    /*
     * SOLUTION: My approach would be the simplest one - an iterative
     * approach
     */

    public static int getSubsetFirstIndex(int[] arr1,  int[] arr2) {
        if(arr1.length >= arr2.length) {
            for (int i = 0; i <= arr1.length - arr2.length; i++) {
                int arrSize = 0;
                int j = i;
                for(int arr: arr2) {
                    if(arr1[j] != arr) {
                        break;
                    }
                    j++;
                    arrSize++;
                }
                if(arrSize == arr2.length) return i;
            }
        }

        return -1;
    }

    // Part 2: unit test the result
    public static void main(String[] args) {
        int[] arr1 = {4, 9, 3, 7, 8};
        int[] arr2 = {3, 7};
        int expected = 2;
        System.out.println("Result 1: "
                + (getSubsetFirstIndex(arr1, arr2) == expected));

        expected = -1;
        System.out.println("Result 2: "
                + (getSubsetFirstIndex(arr2, arr1) == expected));

        int[] arr3 = {9, 8};
        expected = -1;
        System.out.println("Result 3: "
                + (getSubsetFirstIndex(arr1, arr3) == expected));

        int[] arr4 = {4, 9, 3, 7, 8};
        expected = 0;
        System.out.println("Result 4: "
                + (getSubsetFirstIndex(arr1, arr4) == expected));

    }
}
