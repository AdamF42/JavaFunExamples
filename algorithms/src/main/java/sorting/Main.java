package sorting;


public class Main {
    public static void main(String[] args) {
        int[] a = {8, 10, 22, 27, 37, 44, 49, 55, 69}; // given array
        int[] res = MergeSort.sort(a);
        System.out.println("Sorted array: " + res);
    }
}
