import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.time.LocalDate;

public class CinemaSetup {
    private static int bookingCount = 0;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int rows = 0;
        int cols = 0, options;
        String seatCode;
        String[] doubleSeats;
        String[] singleSeats = new String[0];
        char letter = 'A';
        String[][] hallSetup = null;
        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String[][] bookingHistory = new String[0][0];
        do {
            System.out.println("-----------------------------------------------");
            System.out.println("|              CHILL VIB CINEMA               |");
            System.out.println("-----------------------------------------------");
            System.out.println("| [1]. Set up seats in hall                   |");
            System.out.println("| [2]. Book a Cinema seat                     |");
            System.out.println("| [3]. Cancel booking seat                    |");
            System.out.println("| [4]. View Hall Layout                       |");
            System.out.println("| [5]. View Booking History Booking of Seat   |");
            System.out.println("| [6]. Exit the application                   |");
            System.out.println("-----------------------------------------------");
            while (true){
                System.out.print(" => Choose one option: ");
                if (input.hasNextInt()){
                    options = input.nextInt();
                    if (options >= 1 && options <= 6){
                        break;
                    } else {
                        System.out.println("    Invalid input! Please enter only a  positive number between 1 and 5.");
                    }
                } else {
                    System.out.println("    Invalid input! Please enter only a positive number.");
                    input.next();
                }
            }
            switch (options) {
                case 1:{
                    System.out.println("+---------------------------------------------+");
                    System.out.println("|              Cinema Hall Setup              |");
                    System.out.println("+---------------------------------------------+");
                    while (true){
                        System.out.print(" => Insert the number of rows: ");
                        if (input.hasNextInt()){
                            rows = input.nextInt();
                            if (rows >= 1 && rows <= 100){
                                break;
                            } else {
                                System.out.println("    Invalid input! Please enter only a positive number between 1 and 100.");
                            }
                        } else {
                            System.out.println("    Invalid input! Please enter only a positive number.");
                            input.next();
                        }
                    }

                    while (true){
                        System.out.print(" => Insert the number of columns: ");
                        if (input.hasNextInt()){
                            cols = input.nextInt();
                            if (cols >= 1 && cols <= 100){
                                break;
                            } else {
                                System.out.println("    Invalid input! Please enter only a positive number between 1 and 100.");
                            }
                        } else {
                            System.out.println("    Invalid input! Please enter only a positive number.");
                            input.next();
                        }
                    }
                    hallSetup = new String[rows][cols];
                    System.out.println("+------------------------------------------------------------+");
                    System.out.println("|                        Hall Layout                         |");
                    System.out.println("+------------------------------------------------------------+");
                    for (int i = 0; i < rows; i++) {
                        System.out.print("[");
                        for (int j = 0; j < cols; j++) {
                            hallSetup[i][j] = letter + "-" + (j + 1) + ": " + "AV" ;
                            System.out.print("\u001B[34m" + hallSetup[i][j] + "\u001B[0m");
                            if (j < cols - 1) {
                                System.out.print(", ");
                            }
                        }
                        System.out.println("]");
                        letter++;
                    }
                    System.out.println("+------------------------------------------------------------+");
                    break;
                }
                case 2: {
                    if (hallSetup == null){
                        System.out.println("->> Please Set up hall first in option 1 ...");
                        break;
                    }
                    System.out.println("+---------------------------------------------+");
                    System.out.println("|             Booking Seat Option             | ");
                    System.out.println("+---------------------------------------------+");
                    System.out.println("|  [1].Booking single seat                    |");
                    System.out.println("|  [2].Booking multiple seats                 |");
                    System.out.println("|  [3].Return to Main menu                    |");
                    System.out.println("+---------------------------------------------+");
                    while (true) {
                        System.out.print(" => Choose one option: ");
                        if (input.hasNextInt()) {
                            options = input.nextInt();
                            if (options >= 1 && options <= 3) {
                                break;
                            } else {
                                System.out.println("    Invalid input! Please enter only a  positive number between 1 and 3.");
                            }
                        } else {
                            System.out.println("    Invalid input! Please enter only a positive number.");
                            input.next();
                        }
                    }
                    while (true) {

                        switch (options) {
                            case 1: {
                                System.out.println("+--------------------------------------------------------------------------+");
                                System.out.println("|                               Booking Seat                               | ");
                                System.out.println("+--------------------------------------------------------------------------+");
                                for (int i = 0; i < rows; i++) {
                                    System.out.print("[");
                                    for (int j = 0; j < cols; j++) {
                                        System.out.print("\u001B[34m" + hallSetup[i][j] + "\u001B[0m");
                                        if (j < cols - 1) {
                                            System.out.print(", ");
                                        }
                                    }
                                    System.out.println("]");
                                }
                                System.out.println("+--------------------------------------------------------------------------+");
                                System.out.print("Enter the seat code to booking single seat or type 'b' to return: ");
                                input.nextLine();
                                seatCode = input.nextLine();

                                if (seatCode.equalsIgnoreCase("b")) {
                                    System.out.println("Returning to the menu...");
                                    break;
                                }

                                boolean isFound = false;
                                bookingHistory = new String[rows * cols][2];

                                for (int i = 0; i < rows; i++) {
                                    for (int j = 0; j < cols; j++) {
                                        singleSeats = hallSetup[i][j].split(":");
                                        if (seatCode.equals(singleSeats[0])) {
                                            if (singleSeats[1].trim().equals("AV")) {
                                                isFound = true;

                                                hallSetup[i][j] = "\u001B[31m" + singleSeats[0] + ": BO" + "\u001B[0m";
                                                bookingHistory[bookingCount][0] = singleSeats[0];
                                                bookingHistory[bookingCount][1] = formattedDate;
                                                bookingCount++;

                                                System.out.println("- Seat " + singleSeats[0] + " successfully booked on " +  formattedDate);

                                            }
                                        }
                                    }
                                }
                                if (!isFound) {
                                    System.out.println("Invalid Seat Code");
                                }
                                break;
                            }
                            case 2: {
                                System.out.println("+--------------------------------------------------------------------------+");
                                System.out.println("|                              Booking Seat                                | ");
                                System.out.println("+--------------------------------------------------------------------------+");
                                for (int i = 0; i < rows; i++) {
                                    System.out.print("[");
                                    for (int j = 0; j < cols; j++) {
                                        System.out.print("\u001B[34m" + hallSetup[i][j] + "\u001B[0m");
                                        if (j < cols - 1) {
                                            System.out.print(", ");
                                        }
                                    }
                                    System.out.println("]");
                                }
                                System.out.println("+--------------------------------------------------------------------------+");
                                System.out.print("Enter the seat code to booking multiple seat or type 'b' to return: ");
                                input.nextLine();
                                seatCode = input.nextLine();
                                if (seatCode.equalsIgnoreCase("b")) {
                                    System.out.println("Returning to the menu...");
                                    break;
                                }

                                bookingHistory = new String[rows * cols][2];
                                boolean isFound = false;
                                for (int i = 0; i < rows; i++) {
                                    for (int j = 0; j < cols; j++) {
                                        singleSeats = hallSetup[i][j].split(":");
                                        doubleSeats = seatCode.split(",");
                                        for (String doubleSeat : doubleSeats) {
                                            if (singleSeats[0].equals(doubleSeat)) {
                                                if (singleSeats[1].trim().equals("AV")) {
                                                    isFound = true;
                                                    hallSetup[i][j] = "\u001B[31m" + singleSeats[0] + ": BO" + "\u001B[0m";

                                                    bookingHistory[bookingCount][0] = singleSeats[0];
                                                    bookingHistory[bookingCount][1] = formattedDate;
                                                    bookingCount++;

                                                    System.out.println("- Seat " + singleSeats[0] + " successfully booked on " + formattedDate);
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!isFound) {
                                    System.out.println("Invalid Seat Code");
                                }
                                break;
                            }
                            case 3: {
                                System.out.println("Back to Menu....!!");
                                break;
                            }
                            default:
                                System.out.println(" Invalid option....!!");
                                break;
                        }
                        String choose;
                        System.out.print("Do you want to book more(Yes/No)? ");
                        choose = input.next();
                        if (choose.equalsIgnoreCase("no")) {
                            break;
                        }
                    }
                }
                break;
                case 3: {
                    if (bookingCount == 0){
                        System.out.println("->> Please Book seat in option 2 first....");
                        break;
                    }
                    System.out.println("+---------------------------------------------+");
                    System.out.println("|            Cancel Booking Option            | ");
                    System.out.println("+---------------------------------------------+");
                    System.out.println("|  [1].Canceling single seat                  |");
                    System.out.println("|  [2].Canceling multiple seats               |");
                    System.out.println("|  [3].Return to Main menu                    |");
                    System.out.println("+---------------------------------------------+");
                    while (true) {
                        System.out.print(" => Choose one option: ");
                        if (input.hasNextInt()) {
                            options = input.nextInt();
                            if (options >= 1 && options <= 3) {
                                break;
                            } else {
                                System.out.println("    Invalid input! Please enter only a  positive number between 1 and 3.");
                            }
                        } else {
                            System.out.println("    Invalid input! Please enter only a positive number.");
                            input.next();
                        }
                    }
                    while (true) {
                        switch (options) {
                            case 1: {
                                System.out.println("+--------------------------------------------------------------------------+");
                                System.out.println("|                              Cancel Seat Booking                         | ");
                                System.out.println("+--------------------------------------------------------------------------+");
                                for (int i = 0; i < rows; i++) {
                                    System.out.print("[");
                                    for (int j = 0; j < cols; j++) {
                                        System.out.print("\u001B[34m" + hallSetup[i][j] + "\u001B[0m");
                                        if (j < cols - 1) {
                                            System.out.print(", ");
                                        }
                                    }
                                    System.out.println("]");
                                }
                                System.out.println("+--------------------------------------------------------------------------+");
                                System.out.print("Enter the seat code to canceling single seat or type 'b' to return: ");
                                input.nextLine();
                                seatCode = input.nextLine();
                                if (seatCode.equalsIgnoreCase("b")) {
                                    System.out.println("Returning to the menu...");
                                    break;
                                }

                                boolean isFound = false;
                                for (int i = 0; i < rows; i++) {
                                    for (int j = 0; j < cols; j++) {
                                        singleSeats = hallSetup[i][j].replaceAll("\u001B\\[[0-9;]*m", "").split(":");
                                        if (seatCode.equals(singleSeats[0])) {
                                            if (singleSeats[1].trim().equals("BO")) {
                                                isFound = true;
                                                hallSetup[i][j] = singleSeats[0] + ": AV";
                                                System.out.println("Seat " + singleSeats[0] + " successfully cancelled on " + formattedDate);
                                            }
                                        }
                                    }
                                }
                                if (!isFound) {
                                    System.out.println("Invalid Seat Code");
                                }
                                break;
                            }
                            case 2: {
                                System.out.println("+--------------------------------------------------------------------------+");
                                System.out.println("|                              Cancel Seat Booking                         | ");
                                System.out.println("+--------------------------------------------------------------------------+");
                                for (int i = 0; i < rows; i++) {
                                    System.out.print("[");
                                    for (int j = 0; j < cols; j++) {
                                        System.out.print("\u001B[34m" + hallSetup[i][j] + "\u001B[0m");
                                        if (j < cols - 1) {
                                            System.out.print(", ");
                                        }
                                    }
                                    System.out.println("]");
                                }
                                System.out.println("+--------------------------------------------------------------------------+");
                                System.out.print("Enter the seat code to canceling multiple seat or type 'b' to return: ");
                                input.nextLine();
                                seatCode = input.nextLine();
                                if (seatCode.equalsIgnoreCase("b")) {
                                    System.out.println("Returning to the menu...");
                                    break;
                                }

                                boolean isFound = false;
                                for (int i = 0; i < rows; i++) {
                                    for (int j = 0; j < cols; j++) {
                                        singleSeats = hallSetup[i][j].replaceAll("\u001B\\[[0-9;]*m", "").split(":");
                                        doubleSeats = seatCode.replaceAll("\u001B\\[[0-9;]*m", "").split(",");
                                        for (String doubleSeat : doubleSeats) {
                                            if (singleSeats[0].equals(doubleSeat)) {
                                                if (singleSeats[1].trim().equals("BO")) {
                                                    isFound = true;
                                                    hallSetup[i][j] = singleSeats[0] + ": AV";
                                                    System.out.println("Seat " + singleSeats[0] + " successfully cancelled on " + formattedDate);
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!isFound) {
                                    System.out.println("Invalid Seat Code");
                                }
                                break;
                            }
                            case 3: {
                                System.out.println(" Back to menu...");
                                break;
                            }
                            default:
                                System.out.println("Invalid Option...!!");
                                break;
                        }
                        String choose;
                        System.out.print("Do you want to book more(Yes/No)? ");
                        choose = input.next();
                        if (choose.equalsIgnoreCase("no")) {
                            break;
                        }
                    }
                    break;
                }
                case 4: {
                    System.out.println("+----------------------------------------------+");
                    System.out.println("|                  Hall Layout                 |");
                    System.out.println("+----------------------------------------------+");
                    for (int i = 0; i < rows; i++) {
                        System.out.print("[");
                        for (int j = 0; j < cols; j++) {
                            System.out.print("\u001B[34m" + hallSetup[i][j] + "\u001B[0m");
                            if (j < cols - 1) {
                                System.out.print(", ");
                            }
                        }
                        System.out.println("]");
                    }
                    System.out.println("+----------------------------------------------+");
                    break;
                }
                case 5: {
                    System.out.println("+----------------------------------------------+");
                    System.out.println("|     View Booking History Booking of Seat     | ");
                    System.out.println("+----------------------------------------------+");

                    if (bookingCount == 0) {
                        System.out.println("No booking yet.");
                    } else {
                        for (int i = 0; i < bookingCount ; i++) {
                            System.out.println(" - Seat: " + bookingHistory[i][0] + ", Date: " + bookingHistory[i][1]);
                        }
                        System.out.println("+----------------------------------------------+");
                        System.out.println(" - Total Booking Count: " + bookingCount);
                    }
                    break;
                }
                case 6:
                    System.out.println("+----------------------------------------------+");
                    System.out.println("|               Exit program....!!             | ");
                    System.out.println("+----------------------------------------------+");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + options);
            }
            if (options !=6){
                System.out.print("->> Press key Enter to continue...!");
                input.nextLine();
                input.nextLine();
            }
        } while (options != 6);
    }

}
