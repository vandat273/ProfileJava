package khachsan;


import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.atomic.DoubleAccumulator;

public class Main {
    private static final String URL = "jdbc:sqlserver://localhost:1433;" + "databaseName=KhachSan;encrypt=true;trustServerCertificate=true;";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "123456789";

    private static void closeConnection() {
        Connection c = null;
        try {
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<BOOKINGROOM> danhSachBooking = new ArrayList<>();
    private static ArrayList<GUEST> danhSachGuest = new ArrayList<>();
    private static ArrayList<GUEST> danhSachGuestBooking = new ArrayList<>();


    public static void menu() {
        System.out.println("||===========================================================================================================================||");
        System.out.println("||***************************************************************************************************************************||");
        System.out.println("||*                                          CHÀO MỪNG BẠN ĐẾN PHẦN MỀM QUẢN LÝ KHÁCH SẠN                                   *||");
        System.out.println("||*                                                                                                                         *||");
        System.out.println("||*  01.Kiểm tra danh sách phòng trống                                                                                      *||");
        System.out.println("||*  02.Tạo đơn đặt phòng                                                                                                   *||");
        System.out.println("||*  03.Hiển thị thông tin danh sách đặt phòng                                                                              *||");
        System.out.println("||*  04.Sửa thông tin đơn đặt phòng                                                                                         *||");
        System.out.println("||*  05.Xoá thông tin đơn đặt phòng                                                                                         *||");
        System.out.println("||*  06.Cập nhật lại thông tin booking                                                                                      *||");
        System.out.println("||*  07.Tách phòng                                                                                                          *||");
        System.out.println("||*  08.Gộp phòng                                                                                                           *||");
        System.out.println("||*  09.Tính tổng tiền theo phòng                                                                                           *||");
        System.out.println("||*  10.Liệt kê tên loại phòng được nhiều khách book nhất trong năm 2023                                                    *||");
        System.out.println("||*  11.Liệt kê tháng có nhiều khách book nhất trong năm 2023                                                               *||");
        System.out.println("||*  12.Liệt kê tháng có tổng doanh thu lớn nhất trong khoảng thời gian 1/2024 - 3/2024                                     *||");
        System.out.println("||*  13.Liệt kê thông tin các phòng có ngày check in bằng checkout trong tháng 2/2024 và tổng thanh toán ít nhất là 500000  *||");
        System.out.println("||*  14.Liệt kê thông tin top 3 khách hàng book nhiều nhất tháng 9/2023                                                     *||");
        System.out.println("||*  0.Thoát chương trình                                                                                                   *||");
        //System.out.println("||*    Chọn                                                                                                                 *||");
        System.out.println("||***************************************************************************************************************************||");
        System.out.println("||===========================================================================================================================||");
        System.out.println("chọn chức năng");
    }

    public static void main(String[] args) {
        Function function = new Function();
        Scanner scanner = new Scanner(System.in);
        int chon;
        do {
            menu();
            chon = Integer.parseInt(scanner.nextLine());
            switch (chon) {
                case 1:
                    kiemTra();
                    break;
                case 2:
                    function.checkRoomAvailability();
                    ThemThongTinDatPhong();
                    break;
                case 3:
                    hienThiThongTinDatPhong();
                    break;
                case 4:
                    //suaThongTinDatPhong();
                    break;
                case 5:
                    deleteBoking();
                    break;
                case 6:
                    chuyenPhong();
                    break;
                case 7:
                    ;
                    break;
                case 8:
                    gopPhong();
                    break;
                case 9:
                    ;
                    break;
                case 10:
                    lietKe2023();
                    break;
                case 11:
                    lietKeThangnhieuBookNhat2023();
                    break;
                case 12:
                    lietKeThangtongdoanhthulonnhat();
                    break;
                case 13:
                    lietKePhongCheckInCheckOutThang2_2024();
                    break;
                case 14:
                    ;
                    break;
                case 0:
                    closeConnection();
                    System.out.println("Tạm biệt quý khách");
                    break;
                default:
                    System.out.println("Lựa chọn không phù hợp.Vui lòng chọn lại");
            }
        } while (chon != 0);

    }

    private static void kiemTra() {
        System.out.println("Danh sách phòng:");
        System.out.printf("%-10s %-10s %-20s %-30s %-10s\n", "", "ID_Room", "ID_Room_Type", "Price", "Status");
        final String SQL_SELECT_EMPTY_ROOMS = "SELECT * FROM ROOM r "
                + "INNER JOIN BOOKING_ROOM b ON r.ID_Room = b.ID_Room "
                + "WHERE r.Status = 'trong' ";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_EMPTY_ROOMS);
             ResultSet resultSet = statement.executeQuery()) {

            int emptyRoomsCount = 0;
            while (resultSet.next()) {
                while (resultSet.next()) {
                    String ID_Room = resultSet.getString("ID_Room");
                    String ID_Room_Type = resultSet.getString("ID_Room_Type");
                    String Price = resultSet.getString("Price");
                    String Status = resultSet.getString("Status");


                    System.out.printf("%-10s %-10s %-20s %-30s %-10s\n", "",
                            ID_Room, ID_Room_Type, Price, Status);
                }
            }

            // System.out.println("Tổng số phòng trống: " + emptyRoomsCount);
        } catch (SQLException e) {
            System.out.println("Lỗi khi kiểm tra phòng trống: " + e.getMessage());
        }
    }

    private static void ThemThongTinDatPhong() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập mã đặt phòng: ");
        String iDBooking = scanner.nextLine();
        String iDRoom = "";
        boolean bookingExists = false;
        while (!bookingExists) {
            System.out.print("Nhập mã phòng: ");
            iDRoom = scanner.nextLine();

            try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                String sql = "SELECT * FROM ROOM WHERE ID_Room = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, iDRoom);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    System.out.println("Mã phòng có tồn tại trong cơ sở dữ liệu.");
                    bookingExists = true; // Đặt cờ thành true để thoát khỏi vòng lặp
                } else {
                    System.out.println("Mã phòng không tồn tại trong cơ sở dữ liệu. Vui lòng nhập lại.");
                }
            } catch (SQLException e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
        System.out.print("Nhập thời gian check in : ");
        String dateCheckInStr = scanner.nextLine();
        System.out.print("Nhập thời gian check out: ");
        String dateCheckOutStr = scanner.nextLine();
        System.out.print("Nhập số người ở: ");
        int quantity_Guest;
        while (true) {
            if (scanner.hasNextInt()) {
                quantity_Guest = scanner.nextInt();
                break; // Thoát khỏi vòng lặp nếu số người ở là một số nguyên
            } else {
                System.out.println("Sai cú pháp nhập. Vui lòng nhập một số nguyên.");
                scanner.next(); // Đọc và loại bỏ giá trị không phải số từ đầu vào
            }
        }
        scanner.nextLine();
        System.out.print("Tổng tiền thanh toán: ");
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "CREATE OR ALTER TRIGGER CalculateTotalPayment\n" +
                    "ON BOOKING_ROOM\n" +
                    "AFTER INSERT, UPDATE\n" +
                    "AS\n" +
                    "BEGIN\n" +
                    "    UPDATE BR\n" +
                    "    SET Total_payment = DATEDIFF(DAY, BR.Date_check_in, BR.Date_check_out) * R.Price\n" +
                    "    FROM BOOKING_ROOM BR\n" +
                    "    INNER JOIN ROOM R ON BR.ID_Room = R.ID_Room\n" +
                    "END;";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Tổng tiền thanh toán đã được thêm.");
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        System.out.print("Nhập mã khách hàng : ");
        String iDGuest = scanner.nextLine();
        System.out.print("Nhập tên khách hàng: ");
        String nameGuest;
        while (true) {
            nameGuest = scanner.nextLine();
            if (!nameGuest.matches(".*\\d+.*")) {
                System.out.println("Tên khách hàng hợp lệ: " + nameGuest);
                break; // Thoát khỏi vòng lặp nếu tên khách hàng hợp lệ
            } else {
                System.out.println("Tên khách hàng không hợp lệ. Vui lòng nhập lại:");
            }
        }
        System.out.print("Nhập số điện thoại: ");
        String phoneNumber;
        while (true) {
            phoneNumber = scanner.nextLine();
            if (phoneNumber.matches("\\d+")) {
                System.out.println("Số điện thoại hợp lệ: " + phoneNumber);
                break; // Thoát khỏi vòng lặp nếu số điện thoại hợp lệ
            } else {
                System.out.println("Số điện thoại không hợp lệ. Vui lòng nhập lại:");
            }
        }
        System.out.print("Nhập địa chỉ: ");
        String address = scanner.nextLine();
        System.out.print("Nhập CCCD: ");
        String cCCD;
        while (true) {
            cCCD = scanner.nextLine();
            if (cCCD.matches("\\d+")) {
                System.out.println("Số CCCD hợp lệ: " + cCCD);
                break; // Thoát khỏi vòng lặp nếu số điện thoại hợp lệ
            } else {
                System.out.println("Số CCCD không hợp lệ. Vui lòng nhập lại:");
            }
        }
        System.out.print("Chọn trưởng phòng (có/không): ");
        String isCaptainStr = scanner.nextLine();

        // Chuyển đổi chuỗi thành giá trị boolean
        boolean isCaptain = true;
        if (isCaptainStr.equalsIgnoreCase("có")) {
            isCaptain = true;
        } else if (isCaptainStr.equalsIgnoreCase("không")) {
            isCaptain = false;
        } else {
            System.out.println("Giá trị không hợp lệ. Vui lòng nhập true hoặc false.");
            // Xử lý tùy thuộc vào yêu cầu của bạn, có thể yêu cầu người dùng nhập lại hoặc thoát chương trình, vv.
        }
        Date dateCheckIn;
        try {
            dateCheckIn = new SimpleDateFormat("dd/MM/yyyy").parse(dateCheckInStr);
        } catch (ParseException e) {
            System.out.println("Định dạng ngày tháng không hợp lệ.");
            return;
        }
        Date dateCheckOut;
        try {
            dateCheckOut = new SimpleDateFormat("dd/MM/yyyy").parse(dateCheckOutStr);
        } catch (ParseException e) {
            System.out.println("Định dạng ngày tháng không hợp lệ.");
            return;
        }
        BOOKINGROOM bOOKINGROOM = new BOOKINGROOM(iDBooking, iDRoom, dateCheckIn, dateCheckOut, quantity_Guest);
        GUEST gUEST = new GUEST(iDGuest, nameGuest, phoneNumber, address, cCCD);
        danhSachBooking.add(bOOKINGROOM);
        danhSachGuest.add(gUEST);
        danhSachGuestBooking.add(gUEST);
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO BOOKING_ROOM (ID_Booking, ID_Room, Date_check_in, Date_check_out, Quantity_Guest) VALUES (?, ?, ?, ?, ?);\r\n"
                    + "MERGE INTO GUEST AS target\r\n"
                    + "USING (VALUES (?, ?, ?, ?, ?)) AS source (ID_Guest, Name_Guest, Phone_Number, Address, CCCD)\r\n"
                    + "    ON target.ID_Guest = source.ID_Guest\r\n"
                    + "WHEN MATCHED THEN \r\n"
                    + "    UPDATE SET target.Address = source.Address\r\n"
                    + "WHEN NOT MATCHED THEN \r\n"
                    + "    INSERT (ID_Guest, Name_Guest, Phone_Number, Address, CCCD) VALUES (source.ID_Guest, source.Name_Guest, source.Phone_Number, source.Address, source.CCCD);\r\n"
                    + "UPDATE GUEST SET Address = Address WHERE ID_Guest = ?;\r\n"
                    + "INSERT INTO GUEST_BOOKING (ID_Guest,ID_Booking,Is_captain)VALUES (?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, iDBooking);
            statement.setString(2, iDRoom);
            statement.setDate(3, new java.sql.Date(dateCheckIn.getTime()));
            statement.setDate(4, new java.sql.Date(dateCheckOut.getTime()));
            statement.setInt(5, quantity_Guest);
            statement.setString(6, iDGuest);
            statement.setString(7, nameGuest);
            statement.setString(8, phoneNumber);
            statement.setString(9, address);
            statement.setString(10, cCCD);
            statement.setString(11, iDGuest);
            statement.setString(12, iDGuest);
            statement.setString(13, iDBooking);
            statement.setBoolean(14, isCaptain);
            statement.executeUpdate();
            System.out.println("Thêm đơn đặt phòng thành công.");
        } catch (SQLException e) {
            System.out.println("Lỗi" + e.getMessage());
        }
    }

    private static void hienThiThongTinDatPhong() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM BOOKING_ROOM";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("+------------+---------+---------------+----------------+--------------+");
            System.out.println("| ID Booking | ID Room | Date check in | Date check out | Total payment|");
            System.out.println("+------------+---------+---------------+----------------+--------------+");
            while (resultSet.next()) {
                String idBooking = resultSet.getString("ID_Booking");
                String idRoom = resultSet.getString("ID_Room");
                Date checkin = resultSet.getDate("Date_check_in");
                Date checkout = resultSet.getDate("Date_check_out");
                Double totalPayment = resultSet.getDouble("Total_payment");
                System.out.printf("| %-5s | %-10s | %-15s | %-15s | %-7s |\n", idBooking, idRoom, dateFormat.format(checkin), dateFormat.format(checkout), totalPayment);
            }
            System.out.println("+-------+------------+-----------------+---------+---------+---------+");
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi khi hiển thị dữ liệu sản phẩm từ CSDL: " + e.getMessage());
        }
    }

    private static void lietKe2023() {
        final String SQL_SELECT_POPULAR_ROOM_TYPES = "SELECT ROOM_TYPES.ID_Room_type, ROOM_TYPES.Name_Room_type, COUNT(*) AS Booking_Count " +
                "FROM ROOM_TYPES " +
                "INNER JOIN ROOM ON ROOM_TYPES.ID_Room_type = ROOM.ID_Room_type " +
                "INNER JOIN BOOKING_ROOM ON ROOM.ID_Room = BOOKING_ROOM.ID_Room " +
                "WHERE YEAR(BOOKING_ROOM.Date_check_in) = 2023 " +
                "GROUP BY ROOM_TYPES.ID_Room_type, ROOM_TYPES.Name_Room_type " +
                "ORDER BY Booking_Count DESC ";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_POPULAR_ROOM_TYPES);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                String ID_Room_Type = resultSet.getString("ID_Room_type");
                String Name_Room_Type = resultSet.getString("Name_Room_type");
                int Booking_Count = resultSet.getInt("Booking_Count");

                System.out.printf("Những loại phòng được booking nhiều nhất năm 2023: %s (ID: %s) được %d bookings.\n",
                        Name_Room_Type, ID_Room_Type, Booking_Count);
            } else {
                System.out.println("Không có phòng nào đuợc booking trong năm  2023.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void lietKeThangnhieuBookNhat2023() {
        final String SQL_SELECT_POPULAR_MONTH = "SELECT MONTH(BOOKING_ROOM.Date_check_in) AS Month, COUNT(*) AS Booking_Count " +
                "FROM BOOKING_ROOM " +
                "WHERE YEAR(BOOKING_ROOM.Date_check_in) = 2023 " +
                "GROUP BY MONTH(BOOKING_ROOM.Date_check_in) ";
        //"ORDER BY Booking_Count DESC ";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_POPULAR_MONTH);
             ResultSet resultSet = statement.executeQuery()) {

            int maxBookingCount = 0;
            boolean hasData = false;

            while (resultSet.next()) {
                int Month = resultSet.getInt("Month");
                int Booking_Count = resultSet.getInt("Booking_Count");

                if (!hasData) {
                    maxBookingCount = Booking_Count;
                    hasData = true;
                }

                if (Booking_Count == maxBookingCount) {
                    System.out.printf("Tháng có nhiều booking nhất trong năm 2023 là tháng %d với %d bookings.\n",
                            Month, Booking_Count);
                } else {
                    break;
                }
            }

            if (!hasData) {
                System.out.println("Không có dữ liệu cho năm 2023.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void lietKeThangtongdoanhthulonnhat() {

        String batDau = "01/04/2024";
        String ketThuc = "31/07/2024";

        Date ngayDatBatDau;
        Date ngayDatKetThuc;
        try {
            ngayDatBatDau = new SimpleDateFormat("dd/MM/yyyy").parse(batDau);
            ngayDatKetThuc = new SimpleDateFormat("dd/MM/yyyy").parse(ketThuc);

            if (ngayDatKetThuc.before(ngayDatBatDau)) {
                System.out.println("Ngày kết thúc không được nhỏ hơn ngày bắt đầu.");
                return;
            }
        } catch (ParseException e) {
            System.out.println("Định dạng ngày tháng không hợp lệ.");
            return;
        }

        final String SQL_SELECT_TOTAL_REVENUE_BY_MONTH = "SELECT MONTH(BOOKING_ROOM.Date_check_in) AS Month, " +
                "SUM(BOOKING_ROOM.Total_payment) AS Total_Revenue " +
                "FROM BOOKING_ROOM " +
                "WHERE BOOKING_ROOM.Date_check_in BETWEEN ? AND ? " +
                "GROUP BY MONTH(BOOKING_ROOM.Date_check_in) " +
                "ORDER BY Total_Revenue DESC ";


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_TOTAL_REVENUE_BY_MONTH)) {
            statement.setDate(1, new java.sql.Date(ngayDatBatDau.getTime()));
            statement.setDate(2, new java.sql.Date(ngayDatKetThuc.getTime()));

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int thang = resultSet.getInt("Month");
                    double totalRevenue = resultSet.getDouble("Total_Revenue");
                    System.out.printf("Tháng có tổng doanh thu lớn nhất trong khoảng thời gian %s ~ %s là tháng %d, với tổng doanh thu là: %.2f VND.\n",
                            batDau, ketThuc, thang, totalRevenue);
                } else {
                    System.out.printf("Không có dữ liệu trong khoảng thời gian %s ~ %s.\n",
                            batDau, ketThuc);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //    private static void lietKeThangtongdoanhthulonnhat() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Nhập ngày tháng bắt đầu (dd/MM/yyyy): ");
//        String batDau = scanner.nextLine();
//        System.out.print("Nhập ngày tháng kết thúc (dd/MM/yyyy): ");
//        String ketThuc = scanner.nextLine();
//        Date ngayDatBatDau;
//        Date ngayDatKetThuc;
//        try {
//            ngayDatBatDau = new SimpleDateFormat("dd/MM/yyyy").parse(batDau);
//            ngayDatKetThuc = new SimpleDateFormat("dd/MM/yyyy").parse(ketThuc);
//
//            if (ngayDatKetThuc.before(ngayDatBatDau)) {
//                System.out.println("Ngày kết thúc không được nhỏ hơn ngày bắt đầu.");
//                return;
//            }
//        } catch (ParseException e) {
//            System.out.println("Định dạng ngày tháng không hợp lệ.");
//            return;
//        }
//        final String SQL_SELECT_TOTAL_REVENUE = "SELECT SUM(BOOKING_ROOM.Total_payment) AS Total_Revenue " +
//                "FROM BOOKING_ROOM " +
//                "WHERE BOOKING_ROOM.Date_check_in BETWEEN ? AND ?";
//
//        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_TOTAL_REVENUE)) {
//            statement.setDate(1, new java.sql.Date(ngayDatBatDau.getTime()));
//            statement.setDate(2, new java.sql.Date(ngayDatKetThuc.getTime()));
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    double totalRevenue = resultSet.getDouble("Total_Revenue");
//                    System.out.printf("Tổng doanh thu trong khoảng thời gian %s ~ %s là: %.2f VND.\n",
//                            batDau, ketThuc, totalRevenue);
//                } else {
//                    System.out.printf("Không có dữ liệu trong khoảng thời gian %s ~ %s.\n",
//                            batDau, ketThuc);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    private static void deleteBoking() {
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Nhập ID Booking cần xóa: ");
            long deleteID = scanner.nextLong();
            boolean result = false;
            String selectSQL = "SELECT ID_Booking FROM BOOKING_ROOM";
            try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                 PreparedStatement selectStatement = conn.prepareStatement(selectSQL)) {
                ResultSet resultSet = selectStatement.executeQuery();
                while (resultSet.next()) {
                    if (deleteID == resultSet.getLong("ID_Booking")) {
                        String deleteSQL = "DELETE FROM BOOKING_ROOM WHERE ID_Booking = ?";
                        try (PreparedStatement deleteStatement = conn.prepareStatement(deleteSQL)) {
                            deleteStatement.setLong(1, deleteID);
                            deleteStatement.executeUpdate();
                            System.out.println("Đã xóa Booking thành công.");
                        }
                        result = true;
                    }
                }
                if (!result) {
                    System.out.println("Không tồn tại ID Booking này !");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (true);
    }


    private static void lietKePhongCheckInCheckOutThang2_2024() {
        final String SQL_SELECT_ROOMS = "SELECT BOOKING_ROOM.ID_Room, BOOKING_ROOM.Date_check_in, BOOKING_ROOM.Date_check_out, BOOKING_ROOM.Total_payment " +
                "FROM BOOKING_ROOM " +
                "WHERE BOOKING_ROOM.Date_check_in = BOOKING_ROOM.Date_check_out " +
                "AND MONTH(BOOKING_ROOM.Date_check_in) = 5 " +
                "AND YEAR(BOOKING_ROOM.Date_check_in) = 2023 " +
                "AND BOOKING_ROOM.Total_payment >= 500000";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ROOMS);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("Thông tin các phòng có ngày check in = checkout trong tháng 2/2024 và tổng thanh toán ít nhất là 500000:");
            System.out.printf("%-10s %-10s %-10s %-30s %-30s %-10s\n", "",
                    "ID_Booking", "ID_Room", "Date_check_in", "Date_check_in", "Total_payment");

            while (resultSet.next()) {
                String roomId = resultSet.getString("ID_Room");
                Date checkIn = resultSet.getDate("Date_check_in");
                Date checkOut = resultSet.getDate("Date_check_out");
                double totalPayment = resultSet.getDouble("Total_payment");

                System.out.printf("%-10s %-10s %-10s %-30s %-10s\n", "",
                        roomId, checkIn, checkOut, totalPayment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void chuyenPhong() {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            // Kết nối vào cơ sở dữ liệu SQL Server
            ;
            // Ngày check-in và check-out từ người dùng
            Scanner scanner = new Scanner(System.in);
            System.out.println("Nhập ngày Check in");
            String checkinDate = scanner.nextLine();
            System.out.println("Nhập ngày Check Out");
            String checkoutDate = scanner.nextLine();

            // Câu truy vấn SQL để tìm phòng đã đặt
            String query = "SELECT R.ID_ROOM \n" +
                    "FROM ROOM AS R \n" +
                    "LEFT JOIN BOOKING_ROOM BR ON R.ID_Room = BR.ID_Room \n" +
                    "WHERE R.ID_Room IN (\n" +
                    "    SELECT ID_ROOM \n" +
                    "    FROM BOOKING_ROOM \n" +
                    "    WHERE \n" +
                    "    (\n" +
                    "        (? >= Date_check_in AND ? <= Date_check_out)\n" +
                    "        OR \n" +
                    "        (? >= Date_check_in AND ? < Date_check_out)\n" +
                    "        OR \n" +
                    "        (? < Date_check_in AND ? >= Date_check_out)\n" +
                    "    )\n" +
                    ");";
            // Thực thi câu truy vấn
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, checkinDate);
            stmt.setString(2, checkinDate);
            stmt.setString(3, checkoutDate);
            stmt.setString(4, checkoutDate);
            stmt.setString(5, checkinDate);
            stmt.setString(6, checkoutDate);
            ResultSet rs = stmt.executeQuery();

            // Hiển thị danh sách phòng trống
            System.out.println("Danh sách các phòng đã đặt:");
            while (rs.next()) {
                System.out.println("Phòng số: " + rs.getString("ID_ROOM"));
            }

            String query1 = "SELECT R.ID_ROOM \n" +
                    "FROM ROOM AS R \n" +
                    "LEFT JOIN BOOKING_ROOM BR ON R.ID_Room = BR.ID_Room \n" +
                    "WHERE R.ID_Room NOT IN (\n" +
                    "    SELECT ID_ROOM \n" +
                    "    FROM BOOKING_ROOM \n" +
                    "    WHERE \n" +
                    "    (\n" +
                    "        (? >= Date_check_in AND ? <= Date_check_out)\n" +
                    "        OR \n" +
                    "        (? >= Date_check_in AND ? < Date_check_out)\n" +
                    "        OR \n" +
                    "        (? < Date_check_in AND ? >= Date_check_out)\n" +
                    "    )\n" +
                    ");";
// Thực thi câu truy vấn
            PreparedStatement stmt1 = conn.prepareStatement(query1);
            stmt1.setString(1, checkinDate);
            stmt1.setString(2, checkinDate);
            stmt1.setString(3, checkoutDate);
            stmt1.setString(4, checkoutDate);
            stmt1.setString(5, checkinDate);
            stmt1.setString(6, checkoutDate);
            ResultSet rs1 = stmt1.executeQuery();
// Hiển thị danh sách phòng trống
            System.out.println("Danh sách các phòng trống:");
            while (rs1.next()) {
                System.out.println("Phòng số: " + rs1.getString("ID_ROOM"));
            }

            System.out.println("Chọn phòng hiện tại");
            String roomToMoveFrom = scanner.nextLine();
            System.out.println("chọn phòng muốn chuyển tới");
            String roomToMoveTo = scanner.nextLine();

            String updateQuery = "UPDATE BOOKING_ROOM SET ID_Room = ? WHERE ID_Room = ?";

            PreparedStatement pstmt = conn.prepareStatement(updateQuery);
            pstmt.setString(1, roomToMoveTo);
            pstmt.setString(2, roomToMoveFrom);
            pstmt.executeUpdate();
            System.out.println("Success!");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void gopPhong() {
        InputUtils inputUtils = new InputUtils();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            while (true) {
                Date checkinDate = inputUtils.nhapLichDate("Nhập ngày checkin");
                Date checkoutDate = inputUtils.nhapLichDate("Nhập ngày check out");
                if (checkoutDate.compareTo(checkinDate) < 0) {
                    System.out.println("Invalid date range. Check out date must be after check in date.");
                } else {
                    java.sql.Date sqlCheckinDate = new java.sql.Date(checkinDate.getTime());
                    java.sql.Date sqlCheckoutDate = new java.sql.Date(checkoutDate.getTime());

                    // Cau truy van SQL
                    String query = "SELECT R.ID_Room " +
                            "FROM ROOM R " +
                            "LEFT JOIN BOOKING_ROOM BR ON R.ID_Room = BR.ID_Room " + // Thêm khoảng trắng ở đây
                            "WHERE R.ID_Room IN (" +
                            "    SELECT BR.ID_Room FROM BOOKING_ROOM BR " +
                            "    WHERE " +
                            "    ( " +
                            "        (? >= BR.Date_check_in AND ? <= BR.Date_check_out) " +
                            "        OR " +
                            "        (? >= BR.Date_check_in AND ? < BR.Date_check_out) " +
                            "        OR " +
                            "        (? < BR.Date_check_in AND ? >= BR.Date_check_out) " +
                            "    )" +
                            ");";

                    PreparedStatement statement = connection.prepareStatement(query);

                    statement.setDate(1, sqlCheckinDate);
                    statement.setDate(2, sqlCheckinDate);
                    statement.setDate(3, sqlCheckoutDate);
                    statement.setDate(4, sqlCheckoutDate);
                    statement.setDate(5, sqlCheckinDate);
                    statement.setDate(6, sqlCheckoutDate);

                    ResultSet resultSet = statement.executeQuery();
                    // Hiển thị danh sách phòng đã đăt
                    System.out.println("Danh sách các phòng đã đặt:");
                    while (resultSet.next()) {
                        System.out.println("Phòng số: " + resultSet.getString("ID_Room"));
                    }

                    String phongMuonGop = InputUtils.nhapChuoi("Chon phong muon gop");
                    String phongGop = InputUtils.nhapChuoi("Chon phong gop vao");
                    // cộng tiền vào phòng bên sau khi gộp
                    String query2 = "UPDATE BOOKING_ROOM SET Total_payment = Total_payment + (SELECT Total_payment FROM BOOKING_ROOM WHERE ID_Room = ?) WHERE ID_Room = ?";
                    PreparedStatement statement2 = connection.prepareStatement(query2);
                    statement2.setString(1, phongMuonGop);
                    statement2.setString(2, phongGop);
                    statement2.executeUpdate();
                    // xóa phòng đã gộp
                    String query1 = "DELETE FROM BOOKING_ROOM WHERE ID_Room = ?";
                    PreparedStatement statement1 = connection.prepareStatement(query1);
                    statement1.setString(1, phongMuonGop);
                    statement1.executeUpdate();

                    // Cập nhật thêm thông tin người ở trong phòng đã gộp

                    ArrayList<String> guestID = new ArrayList<>();
                    int a = InputUtils.nhapSo("Nhập số lượng khách ở phòng cũ");
                    for (int i = 0; i < a; i++) {
                        String idKhach = InputUtils.nhapChuoi("Nhập ID khách");
                        guestID.add(idKhach);
                    }

                    String updateQuery = "INSERT INTO GUEST_BOOKING (ID_Booking, ID_Guest)" +
                            "VALUES (?, ?)";
                    PreparedStatement preparedStatement1 = connection.prepareStatement(updateQuery);
                    for (String idKhach : guestID) {
                        preparedStatement1.setString(1, phongGop);
                        preparedStatement1.setString(2, idKhach);
                        preparedStatement1.executeUpdate();
                    }
                    preparedStatement1.close();

                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
