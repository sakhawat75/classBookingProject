import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
        public static ArrayList<String> userList = new ArrayList();
        public static Excercise[][][] monthlyTimeTableArray = new Excercise[300][3][];
        public static int userId;
        public static void main(String[] arg) {
            loadUsersFromList();
            loadExercisesFromFile();
            getUserId();//current user id


            MenuGenerator.mainMenu();

        }

    public static double getClassMonthlyIncome(String month, String className) {
        double amount = 0;
        for (int i = 0; i < 300; i++) {
            for (int j = 0; monthlyTimeTableArray[i][0] != null && j < 3; j++) {
                for (int k = 0; k < 3; k++) {

                    //System.out.println(monthlyTimeTableArray[i][j][k].date.substring(5, 7));

                    //month matched and name matched
                    if (monthlyTimeTableArray[i][j][k].date.substring(3, 5).equals(month) && monthlyTimeTableArray[i][j][k].name.equals(className)) {//18/04/2020

                        int students = 0;
                        for (int l = 0; monthlyTimeTableArray[i][0] != null && l < monthlyTimeTableArray[i][j][k].bookedUserId.length; l++) {
                            if (monthlyTimeTableArray[i][j][k].bookedUserId[l] != 0) {
                                students++;
                            }
                        }
                        amount = amount + monthlyTimeTableArray[i][j][k].price * students;
                    }
                }
            }

        }
        return amount;
    }


    public static void getUserId() {
        while (true) {
            System.out.println("\nEnter your user id from below\nId           Name");
            for (int i = 0; i < userList.size(); i++) {
                System.out.println(i + 1 + "           " + userList.get(i));
            }

            try {


                System.out.print("Enter your choice (0 to exit): ");
                Scanner scanner = new Scanner(System.in);
                userId = scanner.nextInt();
                if (userId < 0 || userId > 10) {
                    System.out.print("Invalid input ");
                    continue;
                }
                if(userId==0){
                    System.out.println("Program ended");
                    System.exit(userId);
                    return;
                    
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input");
                continue;
            }
        }
    }

    public static void changeBooking() {
        boolean found = false;
        System.out.println("Id     Class name      date        time     day          available seats     price");
        //obtaining classes names
        for (int i = 0; i < 300; i++) {
            //System.out.println("Available Classes for "+monthlyTimeTableArray[i][0][0].date+" on "+ monthlyTimeTableArray[i][0][0].day);
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 4; l++) {
                        if (monthlyTimeTableArray[i][0] != null && monthlyTimeTableArray[i][j][k].bookedUserId[l] == userId) {
                            found = true;
                            System.out.println(Integer.toString(i) + Integer.toString(j) + Integer.toString(k) + "      " + monthlyTimeTableArray[i][j][k].name + "        " + monthlyTimeTableArray[i][j][k].date + "          " + monthlyTimeTableArray[i][j][k].time + "        " + monthlyTimeTableArray[i][j][k].day + "           " + monthlyTimeTableArray[i][j][k].availableSeats + "                   £" + monthlyTimeTableArray[i][j][k].price);
                        }
                    }
                }

            }
        }
        if (!found) {
            System.out.println("No booked class found");
            return;
        }
        while (true) {
            System.out.print("Enter Class Id to Change Booking(-1 to mainMenu): ");

            Scanner scanner = new Scanner(System.in);
            String in = scanner.nextLine();

            if (in.equals("-1")) {
                MenuGenerator.mainMenu();
                return;
            }

            int i = 0, j = 0, k = 0;
            try {
                if (in.length() == 4) {
                    i = Integer.parseInt(in.substring(0, 2));
                    j = Integer.parseInt(in.substring(2, 3));
                    k = Integer.parseInt(in.substring(3, 4));
                }
                if (in.length() == 3) {
                    i = Integer.parseInt(in.substring(0, 1));
                    j = Integer.parseInt(in.substring(1, 2));
                    k = Integer.parseInt(in.substring(2, 3));

                }
            } catch (Exception e) {
                System.out.println("Invalid input");

                continue;
            }


            for (int l = 0; l < monthlyTimeTableArray[i][j][k].bookedUserId.length; l++) {
                if (monthlyTimeTableArray[i][j][k].bookedUserId[l] == userId) {
                    if (MenuGenerator.bookGroupExcerciseMenu(true)) {//booked
                        monthlyTimeTableArray[i][j][k].bookedUserId[l] = 0;
                        
                        monthlyTimeTableArray[i][j][k].availableSeats++;
                        System.out.println("You have successfully changed your booking to the cal ......");
                        MenuGenerator.mainMenu();
                    }
                    System.out.println("Your booking not found for cancellation");
                }
            }

        }


    }
    public static void classBooker(boolean bookByName, boolean bookByDate, boolean changeBooking) {
        if (bookByName) {
            bookbyClassName(changeBooking);
        } else if (bookByDate) {
            bookbyDate(changeBooking);
        }

    }
     public static void attendBookedClass() {
        printCurrentUserBookedClasses();
        reviewTheClass();

    }
     public static void printCurrentUserBookedClasses() {
        System.out.println("Id     Class name       date      time     day          available seats     price");
        for (int i = 0; i < 300; i++) {
            for (int j = 0; monthlyTimeTableArray[i][0] != null && j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; monthlyTimeTableArray[i][0] != null && l < monthlyTimeTableArray[i][j][k].bookedUserId.length; l++) {
                        if (monthlyTimeTableArray[i][j][k].bookedUserId[l] == userId) {
                            
                            //return true;
                            System.out.println(Integer.toString(i) + Integer.toString(j) + Integer.toString(k) + "      " + monthlyTimeTableArray[i][j][k].name + "        " + monthlyTimeTableArray[i][j][k].date + "          " + monthlyTimeTableArray[i][j][k].time + "        " + monthlyTimeTableArray[i][j][k].day + "           " + monthlyTimeTableArray[i][j][k].availableSeats + "                   £" + monthlyTimeTableArray[i][j][k].price);

                        }
                    }
                }
            }
        }
    }

    public static void reviewTheClass() {
        while (true) {
            try {
                System.out.print("Enter Class Id to review (-1 to go back to Main Menu): ");

                Scanner scanner = new Scanner(System.in);
                String in = scanner.nextLine();

                if(in.equals("-1")){
                    MenuGenerator.mainMenu();
                    return;
                }
                System.out.println("Enter Your Message Feedback");
                String msg = scanner.nextLine();

                System.out.println("Now please give a rating 1 to 5 in integer number:");
                int rat = scanner.nextInt();

                int i = 0, j = 0, k = 0;
                if (in.length() == 4) {
                    i = Integer.parseInt(in.substring(0, 2));
                    j = Integer.parseInt(in.substring(2, 3));
                    k = Integer.parseInt(in.substring(3, 4));
                }
                if (in.length() == 3) {
                    i = Integer.parseInt(in.substring(0, 1));
                    j = Integer.parseInt(in.substring(1, 2));
                    k = Integer.parseInt(in.substring(2, 3));

                }

                monthlyTimeTableArray[i][j][k].rating[rat - 1]++;
                
                for (int l = 0; monthlyTimeTableArray[i][0] != null && l < monthlyTimeTableArray[i][j][k].bookedUserId.length; l++) {
                        if (monthlyTimeTableArray[i][j][k].bookedUserId[l] == userId) {
                            monthlyTimeTableArray[i][j][k].bookedUserId[l] =-1;
                            break;
                            
                        }
                    }
               

                System.out.println("Your review saved successfully.");
                break;
            } catch (Exception e) {
                System.out.println("Invalid Input. Try again");
            }
        }
    }
    
    public static boolean bookbyDate(boolean changeBooking) {
        boolean found = false;
        while (!found) {
            System.out.println("\nPlease give the date between Feb 2020 to April 2020, Saturday and Sunday only date format dd/mm/yy");
            System.out.println("-1 to main menu");
            Scanner scanner = new Scanner(System.in);
            String date = scanner.nextLine();
            if(date.equals("-1")){
                MenuGenerator.mainMenu();
            }
            System.out.println("Id     Class name     date      time     day          available seats     price");

            //checking if date exists or not
            //and printing the available classes on that date
            for (int i = 0; i < 300 && !found; i++) {

                for (int j = 0; j < 3; j++) {
                    if (monthlyTimeTableArray[i][j] != null && monthlyTimeTableArray[i][j][0].date.equals(date)) {

                        for (int k = 0; k < 3; k++) {
                            //printing the available classes on this date
                            System.out.println(Integer.toString(i) + Integer.toString(j) + Integer.toString(k) + "      " + monthlyTimeTableArray[i][j][k].name + "        " + monthlyTimeTableArray[i][j][k].date + "          " + monthlyTimeTableArray[i][j][k].time + "        " + monthlyTimeTableArray[i][j][k].day + "          " + monthlyTimeTableArray[i][j][k].availableSeats + "                   £" + monthlyTimeTableArray[i][j][k].price);
                        }
                        found = true;

                    }

                }

            }

            //after date found and printing
            if (found) {//date found

                return bookTheClassNow(changeBooking);

            } else {
                System.out.println("please check the condition and try again");
            }

        }
        return false;
    }

    public static void loadExercisesFromFile() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("data.txt"));
            String line = reader.readLine();

            int dayIndex = 0;
            int dayTimeIndex = 0;
            while (line != null) {
                Excercise e1, e2, e3;
                if (line.equals("#")) {
                    dayIndex++;
                    dayTimeIndex = 0;
                    line = reader.readLine();
                    continue;

                }
                //02/02/2020#sat#M#1#Yoga#40#4#1#0#4#5#4#1#2
                //18/04/2020#sat#M#1#Yoga#40#4#0#0#0#0#0
                StringTokenizer st = new StringTokenizer(line, "#");

                String date = st.nextToken();
                String day = st.nextToken();
                String time = st.nextToken();
                int id = Integer.parseInt(st.nextToken());
                String name = st.nextToken();
                double price = Integer.parseInt(st.nextToken());
                int availableSeats = Integer.parseInt(st.nextToken());

                int[] rating = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
                int[] bookedUserId = new int[4];

                //reading userIds who booked this excercise
                int index = 0;
                while (st.hasMoreTokens() && index < 4) {
                    bookedUserId[index] = Integer.parseInt(st.nextToken());
                    index++;
                }
                //one line readed
                e1 = new Excercise(id, name, price, availableSeats, time, day, bookedUserId, rating, date);


                // read next line
                line = reader.readLine();
                st = new StringTokenizer(line, "#");


                date = st.nextToken();
                day = st.nextToken();
                time = st.nextToken();
                id = Integer.parseInt(st.nextToken());
                name = st.nextToken();
                price = Integer.parseInt(st.nextToken());
                availableSeats = Integer.parseInt(st.nextToken());

                rating = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
                bookedUserId = new int[4];

                //reading userIds who booked this excercise
                index = 0;
                while (st.hasMoreTokens() && index < 4) {
                    bookedUserId[index] = Integer.parseInt(st.nextToken());
                    index++;
                }
                //one line readed
                e2 = new Excercise(id, name, price, availableSeats, time, day, bookedUserId, rating, date);


                // read next line
                line = reader.readLine();
                st = new StringTokenizer(line, "#");


                date = st.nextToken();
                day = st.nextToken();
                time = st.nextToken();
                id = Integer.parseInt(st.nextToken());
                name = st.nextToken();
                price = Integer.parseInt(st.nextToken());
                availableSeats = Integer.parseInt(st.nextToken());

                rating = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
                bookedUserId = new int[4];

                //reading userIds who booked this excercise
                index = 0;
                while (st.hasMoreTokens() && index < 4) {
                    bookedUserId[index] = Integer.parseInt(st.nextToken());
                    index++;
                }
                //one line readed
                e3 = new Excercise(id, name, price, availableSeats, time, day, bookedUserId, rating, date);


                //creating array of 3 size
                Excercise excercises[] = new Excercise[3];
                excercises[0] = e1;
                excercises[1] = e2;
                excercises[2] = e3;


                //adding array to timeTAbleArray
                monthlyTimeTableArray[dayIndex][dayTimeIndex] = excercises;
                dayTimeIndex++;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public static void loadUsersFromList() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "users.txt"));
            String line = reader.readLine();
            while (line != null) {
                StringTokenizer st = new StringTokenizer(line, "#");

                int id = Integer.parseInt(st.nextToken());
                String name = st.nextToken();
                userList.add(name);


                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



public static boolean bookbyClassName(boolean changeBooking) {
        printAvailableExcercises();
        return bookTheClassNow(changeBooking);

    }
    public static boolean bookTheClassNow(boolean changeBooking) {
        while (true) {
            System.out.print("Enter Class Id to Book Booking: ");

            Scanner scanner = new Scanner(System.in);
            String in = scanner.nextLine();

            if (in.equals("0")) {
                //printMainMenu
                return false;
            }


            int i = 0, j = 0, k = 0;
            try {
                if (in.length() == 4) {
                    i = Integer.parseInt(in.substring(0, 2));
                    j = Integer.parseInt(in.substring(2, 3));
                    k = Integer.parseInt(in.substring(3, 4));
                }
                if (in.length() == 3) {
                    i = Integer.parseInt(in.substring(0, 1));
                    j = Integer.parseInt(in.substring(1, 2));
                    k = Integer.parseInt(in.substring(2, 3));

                }
            } catch (Exception e) {
                System.out.println("Invalid input");

                continue;
            }

            if (changeBooking) {
                for (int l = 0; l < monthlyTimeTableArray[i][j][k].bookedUserId.length; l++) {
                    if (monthlyTimeTableArray[i][j][k].bookedUserId[l] == 0) {
                        monthlyTimeTableArray[i][j][k].bookedUserId[l] = userId;
                        //decrease capacity
                        monthlyTimeTableArray[i][j][k].availableSeats--;

                        System.out.println("You booked to the class (class " + monthlyTimeTableArray[i][j][k].name + ", " + monthlyTimeTableArray[i][j][k].date + ", " + monthlyTimeTableArray[i][j][k].time + ")");
                        return true;
                    }
                }
                System.out.println("Sorry, 1 class can take only 4 student");
                return false;
            }

            if (!conflict(i, j)) {
                for (int l = 0; l < monthlyTimeTableArray[i][j][k].bookedUserId.length; l++) {
                    if (monthlyTimeTableArray[i][j][k].bookedUserId[l] == 0) {
                        monthlyTimeTableArray[i][j][k].bookedUserId[l] = userId;
                        //decrease capacity
                        monthlyTimeTableArray[i][j][k].availableSeats--;
                        System.out.println("You booked to the class (class " + monthlyTimeTableArray[i][j][k].name + ", " + monthlyTimeTableArray[i][j][k].date + ", " + monthlyTimeTableArray[i][j][k].time + ")");
                        MenuGenerator.mainMenu();
                        return true;
                    }
                }
                System.out.println("Sorry, 1 class can take only 4 student");

            } else {
                System.out.println("You already have another booking in same time.");
            }
            return false;
        }

    }

    public static boolean conflict(int i, int j) {
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < monthlyTimeTableArray[i][j][k].bookedUserId.length; l++) {
                if (monthlyTimeTableArray[i][j][k].bookedUserId[l] == userId) {
                    return true;
                }
            }
        }
        return false;
    }
    public static void printAvailableExcercises() {
        System.out.println("Id     Class name      date        time     day          available seats     price");
        //obtaining classes names
        for (int i = 0; i < 300; i++) {
            //System.out.println("Available Classes for "+monthlyTimeTableArray[i][0][0].date+" on "+ monthlyTimeTableArray[i][0][0].day);
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (monthlyTimeTableArray[i][0] != null) {
                        System.out.println(Integer.toString(i) + Integer.toString(j) + Integer.toString(k) + "      " + monthlyTimeTableArray[i][j][k].name + "        " + monthlyTimeTableArray[i][j][k].date + "          " + monthlyTimeTableArray[i][j][k].time + "        " + monthlyTimeTableArray[i][j][k].day + "           " + monthlyTimeTableArray[i][j][k].availableSeats + "                   £" + monthlyTimeTableArray[i][j][k].price);
                    }
                }
            }

        }
    }
}