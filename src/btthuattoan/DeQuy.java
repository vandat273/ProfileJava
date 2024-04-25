package btthuattoan;

import java.util.OptionalInt;

public class timKiemTuanTu {
    public static void main(String[] args) {
        int[] a = {1, 2, 2, 3, 4, 4};
        int k = 4;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == k) {
                System.out.println(i);
            }
        }
    }
}
