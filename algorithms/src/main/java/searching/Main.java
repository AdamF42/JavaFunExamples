package searching;

public class Main {
    public static void main(String  [] args) {
        int[] a = {8, 10, 22, 27, 37, 44, 49, 55, 69}; // given array
        int val = 69; // value to be searched
        int res = BinarySearch.search(val,a);
        System.out.println("Element to be searched is: " + val);
        System.out.println("Index of element: " + res);
    }
}
