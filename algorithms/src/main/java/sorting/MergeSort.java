package sorting;

public class MergeSort {

    public static int[] mergeSort(int[] elements){
        if(elements.length < 2) {
            return elements;
        }

        int mid = elements.length / 2;

        int [] left = new int[mid];


        for (int i = 0; i < mid; i++){
            left[i] = elements[i];
        }

        int [] right = new int[elements.length - mid];

        for (int i = mid; i < elements.length; i++) {
            right[i - mid] = elements[i];
        }

        mergeSort(left);
        mergeSort(right);

    }

    private static int[] merge(int[] elements, int[] left, int[] right) {
        int leftSize = left.length;
        int rightSize = right.length;

    }

}
