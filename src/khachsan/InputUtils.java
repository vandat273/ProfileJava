package khachsan;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InputUtils {
    private static InputUtils instance;

    public static InputUtils getInstance() {
        if (instance == null) {
            instance = new InputUtils();
        }

        return instance;
    }

    private static final Scanner scanner = new Scanner(System.in);
    private Connection connection;


    public static Date nhapLichDate(String message) {
        Date date = null;
        while (true) {
            System.out.println(message);
            String userInput = scanner.nextLine().trim();
            if (!userInput.isEmpty()) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    dateFormat.setLenient(false);
                    date = dateFormat.parse(userInput);
                    // Nếu ngày đã nhập phù hợp với định dạng, thoát khỏi vòng lặp
                    break;
                } catch (ParseException e) {
                    // Nếu ngày không phù hợp với định dạng, thông báo lỗi và yêu cầu nhập lại
                    System.out.println("Ngày không hợp lệ. Vui lòng nhập lại theo định dạng yyyy-MM-dd.");
                }
            } else {
                System.out.println("Không được để trống.");
            }
        }
        return date;
    }


    public static int nhapSo(String message) {
        int number = 0;
        while (true) {
            try {
                System.out.println(message);
                number = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Số không hợp lệ. Vui lòng nhập lại.");
            }
        }
        return number;
    }

    public static String nhapChuoi(String message) {
        System.out.println(message);
        return scanner.nextLine().trim();
    }

    public static String nhapChuoi1(String message) {
        String input;
        while (true) {
            System.out.println(message);
            input = scanner.nextLine().trim();
            if (!input.isEmpty() && input.matches("^[a-zA-Z0-9]*$")) {
                break;
            }
            System.out.println("Du lieu da sai hoac khong được để trống.");
        }
        return input;
    }

    public static int nhapSoNguyen(String message) {
        int input = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(message);
            String userInput = scanner.nextLine().trim();
            if (!userInput.isEmpty()) {
                try {
                    input = Integer.parseInt(userInput);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Giá trị không hợp lệ. Vui lòng nhập một số nguyên.");
                }
            } else {
                System.out.println("Không được để trống.");
            }
        }
        return input;
    }


}
