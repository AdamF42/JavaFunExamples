package searching;

public class BinarySearch {

    public static int search(int elem, int[] elements) {
        return binSearch(elem, elements, 0, elements.length - 1);
    }

    private static int binSearch(int elem, int[] elements, int start, int end) {
        if (start > end) {
            return -1;
        }
        int middle = (start + end) / 2;
        if (elem == elements[middle]) {
            return middle;
        } else if (elem > elements[middle]) {
            return binSearch(elem, elements, middle + 1, end);
        } else {
            return binSearch(elem, elements, start, middle - 1);
        }
    }

}
