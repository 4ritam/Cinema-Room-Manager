package cinema;

import java.util.Scanner;

public class Cinema {

    // Main Method
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        int[][] cinema = new int[rows][seats];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = 0;
            }
        }

        choices(cinema);

    }

    // Choice Method
    public static void choices(int[][] cinema) {

        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: displaySeats(cinema);
                    break;
                case 2: buyTicket(cinema);
                    break;
                case 3: statistics(cinema);
                    break;
                case 0: isRunning = false;
                    break;
                default: break;
            }

        }
    }

    // Seat Display
    public static void displaySeats(int[][] cinema) {

        System.out.println("\nCinema:");

        for (int i = 0; i <= cinema.length; i++) {
            for (int j = 0; j <= cinema[0].length; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("  ");
                }
                else if (i == 0 || j == 0) {
                    System.out.print((i+j)+" ");
                }
                else if (cinema[i-1][j-1] != 0){
                    System.out.print("B ");
                } else {
                    System.out.print("S ");
                }
            }
            System.out.println();
        }
    }

    // Buy Tickets
    public static void buyTicket(int[][] cinema) {

        Scanner scanner = new Scanner(System.in);
        boolean isSelecting = true;
        int halfRow = cinema.length/2;
        int[] seatCoordination = new int[2];

        while (isSelecting) {
            System.out.println("\nEnter a row number:");
            seatCoordination[0] = scanner.nextInt();

            System.out.println("Enter a seat number in that row:");
            seatCoordination[1] = scanner.nextInt();

            if ( seatCoordination[1] > cinema[0].length ||
                    seatCoordination[1] < 1 ||
                    seatCoordination[0] > cinema.length ||
                    seatCoordination[0] < 1) {
                System.out.println("Wrong input!");
                continue;
            }

            if (cinema[seatCoordination[0] - 1][seatCoordination[1] - 1] != 0) {
                System.out.println("That ticket has already been purchased");
                continue;
            }

            isSelecting = false;
        }

        int row = seatCoordination[0];
        int seat = seatCoordination[1];


        int ticket;
        if(cinema.length * cinema[0].length <= 60) {
            ticket = 10;
        } else {
            if (row <= halfRow) {
                ticket = 10;
            } else {
                ticket = 8;
            }
        }

        System.out.println("Ticket Price: $"+ticket);
        cinema[row - 1][seat - 1] = ticket;

    }

    // Statistics
    public static void statistics(int[][] cinema) {

        int current = 0;
        int numberOfTickets = 0;

        for (int[] perRow : cinema) {
            for (int perTicket : perRow) {
                if (perTicket != 0) {
                    current += perTicket;
                    numberOfTickets += 1;
                }
            }
        }

        if (numberOfTickets != 0) {
            System.out.println("\nNumber of purchased tickets: " + numberOfTickets);
            float percent = (float)numberOfTickets / (cinema.length * cinema[0].length) * 100;
            System.out.printf("Percentage: %.2f", percent);
            System.out.println("%");
        } else {
            System.out.println("Number of purchased tickets: " + 0);
            System.out.println("Percentage: 0.00%");
        }

        System.out.println("Current income: $" + current);

        int halfRow = cinema.length/2;

        int total = cinema[0].length * (halfRow  * 10 + (cinema.length - halfRow) * 8);

        System.out.println("Total income: $" + total);

    }

}