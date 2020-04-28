import java.util.Scanner;

public class MenuGenerator {
    public static void mainMenu() {
        boolean exits = false;

        while (!exits) {
            Scanner scan = new Scanner(System.in);
            System.out.println(
                    "\n\n1.\tBook group exercise \n" +
                            "2.\tAttend your boked class\n" +
                            "3.\tChange Previous booking\n" +
                            "4.\tMonthly class report\n" +
                            "5.\tMonthly champion class report\n" +
                            "6.\tExit\n");

            System.out.println("Enter your choice::");
            int choice = -1;
            try {
                choice = scan.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid Input");
                continue;
            }


            switch (choice) {
                case 1://Book group excercise
                    bookGroupExcerciseMenu(false);
                    break;
                case 2://attend your booked class/review
                    
                    break;
                case 3://change previous booking
                    
                    break;
                case 4://monthly report
                    
                    break;
                case 5:
                    
                    break;
                case 6:
                    exits = true;
                    break;

                default:
                    System.out.println("Incorrect input!!! Please re-enter choice from our menu");
            }

            if (exits)
                break;
        }
        System.out.println("Program ended .. .");
        System.exit(1);


    }
    public static boolean bookGroupExcerciseMenu(boolean changeBooking) {

        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println(
                    "\n\n1.\tBooked by Date\n" +
                            "2.\tBooked by class name\n" +
                            "3.\tfor Main menu\n");

            System.out.println("Enter your choice::");
            int choice;
            try {
                choice = scan.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid Input");
                continue;
            }


            switch (choice) {
                case 1://Book by date
                    if(changeBooking){
                        return Main.bookbyDate(changeBooking);
                    }
                    else {
                        Main.bookbyDate(changeBooking);
                    }

                    break;
                case 2://book by class name
                    if(changeBooking){
                        return Main.bookbyClassName(changeBooking);
                    }
                    Main.bookbyClassName(changeBooking);
                    break;
                case 3://main menu
                    mainMenu();
                    break;

                default:
                    System.out.println("Incorrect input!!! Please re-enter choice from our menu");
            }
        }

    }

}
