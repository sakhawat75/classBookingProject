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


                System.out.print("Enter your choice: ");
                Scanner scanner = new Scanner(System.in);
                userId = scanner.nextInt();
                if (userId < 1 || userId > 10) {
                    System.out.print("Invalid input ");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input");
                continue;
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

}
