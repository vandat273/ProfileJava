package lesson12;

public class Day13 {
    public static void reverseNumber(int n) {
        if (n < 10) {
            System.out.print(n);
            return;
        }
        System.out.print(n % 10);
        reverseNumber(n / 10);
    }

    public static void main(String[] args) {
        int number = 12345;
        System.out.print("Số nguyên dương đảo ngược: ");
        reverseNumber(number);
    }
}
