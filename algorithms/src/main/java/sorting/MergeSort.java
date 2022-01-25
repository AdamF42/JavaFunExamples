package sorting;

public class MergeSort {

    private static int[] merge(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;
        int[] c = new int[n + m];
        int i = 0, j = 0, k = 0;
        while (i < n && j < m) {
            if (a[i] <= b[j]) {
                c[k] = a[i];
                k = k + 1;
                i = i + 1;
            } else {
                c[k] = b[j];
                k = k + 1;
                j = j + 1;
            }
        }
        if (i > n) {
            while (j <= m) {
                c[k] = b[j];
                k = k + 1;
                j = j + 1;
            }
        } else if (j > m) {
            while (i <= n) {
                c[k] = a[i];
                k = k + 1;
                i = i + 1;
            }
        }
        return c;
    }

    public static int[] sort(int[] elements) {
        return mergeSort(elements, 0, elements.length - 1);
    }

    private static int[] mergeSort(int[] elements, int p, int q) {
        if (p < q) {
            int m = (p + q) / 2;

            int[] left = new int[q - m];
            for (int i = 0; i < m; i++) {
                left[i] = elements[i];
            }

            int[] right = new int[elements.length - m];
            for (int i = m; i < elements.length; i++) {
                right[i - m] = elements[i];
            }

            left = mergeSort(left, p, m);
            right = mergeSort(right, m + 1, q);

            return merge(left, right);
        }

        return elements;
    }

}
