/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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