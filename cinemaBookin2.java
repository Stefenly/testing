import java.util.Scanner;
import java.awt.Toolkit;
import java.time.LocalDate;

public class cinemaBookin2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int row = 0;
        int col = 0;
        int option = -1;

        String[][] seatSetup = null;
        LocalDate today = LocalDate.now();
        String bookingDate = today.toString();

        do {
            System.out.println("-----------------------------------------------");
            System.out.println("|              CHILL VIB CINEMA               |");
            System.out.println("-----------------------------------------------");
            System.out.println("| [1]. Set up seats in hall                   |");
            System.out.println("| [2]. Book a Cinema seat                     |");
            System.out.println("| [3]. Cancel booking seat                    |");
            System.out.println("| [4]. View Booking History Booking of Seat   |");
            System.out.println("| [5]. Exit the application                   |");
            System.out.println("-----------------------------------------------");

            while (true){
                System.out.print(" => Choose one option: ");
                if (scanner.hasNextInt()){
                    option = scanner.nextInt();
                    if (option >= 1 && option <= 5){
                        break;
                    } else {
                        System.out.println("    Invalid input! Please enter only a  positive number between 1 and 5.");
                    }
                } else {
                    System.out.println("    Invalid input! Please enter only a positive number.");
                    scanner.next();
                }
            }

            System.out.println();
            switch (option) {
                case 1:
                    System.out.println("+---------------------------------------------+");
                    System.out.println("|              Cinema Hall Setup              |");
                    System.out.println("+---------------------------------------------+");
                    while (true){
                        System.out.print(" => Insert the number of rows: ");
                        if (scanner.hasNextInt()){
                            row = scanner.nextInt();
                            if (row >= 1 && row <= 100){
                                break;
                            } else {
                                System.out.println("    Invalid input! Please enter only a positive number between 1 and 5.");
                            }
                        } else {
                            System.out.println("    Invalid input! Please enter only a positive number.");
                            scanner.next();
                        }
                    }

                    while (true){
                        System.out.print(" => Insert the number of columns: ");
                        if (scanner.hasNextInt()){
                            col = scanner.nextInt();
                            if (col >= 1 && col <= 100){
                                break;
                            } else {
                                System.out.println("    Invalid input! Please enter only a positive number between 1 and 5.");
                            }
                        } else {
                            System.out.println("    Invalid input! Please enter only a positive number.");
                            scanner.next();
                        }
                    }

                    seatSetup = new String[row][col];
                    char letter = 'A';
                    for (int i = 0; i < row; i++) {
                        for (int j = 0; j < col; j++) {
                            seatSetup[i][j] = letter + "-" + (j + 1) + ": AV";
                        }
                        letter++;
                    }
                    System.out.println("Seat setup completed successfully!");
                    Toolkit.getDefaultToolkit().beep();
                    break;
                case 2:
                    if (seatSetup == null) {
                        System.out.println("Please set up the cinema hall first!");
                        break;
                    }
                    while (true){
                        System.out.println("+---------------------------------------------+");
                        System.out.println("|                 Booking Seat                | ");
                        System.out.println("+---------------------------------------------+");
                        for (String[] rows : seatSetup){
                            System.out.print("[");
                            for (int j = 0 ; j < rows.length ; j++){
                                System.out.print("\u001B[34m" + rows[j] + "\u001B[0m");
                                if (j < rows.length - 1){
                                    System.out.print(", ");
                                }
                            }
                            System.out.println("]");
                        }
                        System.out.println("+---------------------------------------------+");
                        System.out.print("=> Enter seat to book(A-1) or type 'back' to return: ");
                        String bookToSeat = scanner.next();
                        bookToSeat = bookToSeat.toUpperCase();

                        if (bookToSeat.equalsIgnoreCase("back")) {
                            System.out.println("Back to menu...");
                            break;
                        }

                        boolean booking = false;
                        for (int i = 0; i < row; i++) {
                            for (int j = 0; j < col; j++) {
                                if (seatSetup[i][j].startsWith(bookToSeat) && seatSetup[i][j].endsWith("AV")){

                                    seatSetup[i][j] = "\u001B[31m" + bookToSeat + ": BO " + bookingDate + "\u001B[0m";
                                    System.out.println("->> Seat " + bookToSeat + " has been successfully booked on " + bookingDate + "!");
                                    booking = true;
                                    break;
                                }
                            }
                            if (booking) break;
                        }
                        if (!booking){
                            System.out.println("Seat " + bookToSeat + " is invalid or already booked. Try again!");
                            continue;
                        }
                        String choose;
                        System.out.print("Do you want to book more(Yes/No)? ");
                        choose = scanner.next();
                        if (choose.equalsIgnoreCase("no")){
                            break;
                        }
                    }

                    break;
                case 3:
                    if (seatSetup == null) {
                        System.out.println("Please set up the cinema hall first.");
                        break;
                    }

                    while (true){
                        System.out.println("+----------------------------------------------+");
                        System.out.println("|                Cancel Booking                |");
                        System.out.println("+----------------------------------------------+");

                        for (String[] rows : seatSetup) {
                            System.out.print("[");
                            for (int j = 0; j < rows.length; j++) {
                                System.out.print("\u001B[34m" + rows[j] + "\u001B[0m");
                                if (j < rows.length - 1) {
                                    System.out.print(", ");
                                }
                            }
                            System.out.println("]");
                        }

                        System.out.print("=> Enter the seat to cancel (A-1) or type 'back' to return: ");
                        String seatToCancel = scanner.next();
                        seatToCancel = seatToCancel.toUpperCase();
                        if (seatToCancel.equalsIgnoreCase("back")) {
                            System.out.println("Returning to the main menu...");
                            break;
                        }

                        boolean canceled = false;
                        for (int i = 0; i < row; i++) {
                            for (int j = 0; j < col; j++) {
                                if (seatSetup[i][j].startsWith("\u001B[31m" + seatToCancel) && seatSetup[i][j].endsWith("BO "+bookingDate + "\u001B[0m" )) {
                                    seatSetup[i][j] = seatToCancel + ": AV";
                                    System.out.println("->> Seat " + seatToCancel + " has been successfully canceled!");
                                    canceled = true;
                                    break;
                                }
                            }
                            if (canceled) break;
                        }
                        if (!canceled) {
                            System.out.println("->> Seat " + seatToCancel + " is either invalid or not booked. Try again!");
                            continue;
                        }

                        String choose;
                        System.out.print("Do you want to cancel more(Yes/No)? ");
                        choose = scanner.next();
                        if (choose.equalsIgnoreCase("no")){
                            break;
                        }

                    }
                    break;

                case 4:
                    System.out.println("+----------------------------------------------+");
                    System.out.println("|     View Booking History Booking of Seat     | ");
                    System.out.println("+----------------------------------------------+");

                    for (String[] rows : seatSetup) {
                        System.out.print("[");
                        for (int j = 0; j < rows.length; j++) {
                            System.out.print("\u001B[34m" + rows[j] + "\u001B[0m");
                            if (j < rows.length - 1) {
                                System.out.print(", ");
                            }
                        }
                        System.out.println("]");
                    }

                    System.out.println("+----------------------------------------------+");
                    System.out.println("|              Booked Information              |");
                    System.out.println("+----------------------------------------------+");

                    int totalBook = 0;
                    for (String[] rows: seatSetup){
                        for (String seat: rows){
                            if (seat.contains("BO")){
                                totalBook++;
                                System.out.println("- " + seat);
                            }
                        }
                    }
                    System.out.println("+----------------------------------------------+");
                    if (totalBook == 0){
                        System.out.println("No seats have been booked yet.");
                    } else {
                        System.out.println("- Total Booked Seats: " + totalBook);
                    }
                    System.out.println("- Total == Available Seats: " + ((row * col) - totalBook));
                    System.out.println("+----------------------------------------------+");

                    break;
                case 5:
                    System.out.println("+----------------------------------------------+");
                    System.out.println("|               Exit program....!!             | ");
                    System.out.println("+----------------------------------------------+");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + option);
            }

            if (option !=5){
                System.out.println("->> Press key Enter to continue...!");
                scanner.nextLine();
                scanner.nextLine();
            }
        }while (option !=5);

    }
}
