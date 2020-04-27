public class Excercise {
    int id;
    public String name;
    public double price;
    int availableSeats;
    String time;//moring afternoon evening
    String day;//sunday or saturday
    int [] bookedUserId;//users who booked this excercise
    int rating[];//will contain rating 0 to 5 on every index
    String date;
    public Excercise() {

    }

    public Excercise(int id, String name, double price, int availableSeats, String time, String day, int[] bookedUserId, int[] rating,String date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.availableSeats = availableSeats;
        this.time = time;
        this.day = day;
        this.bookedUserId = bookedUserId;
        this.rating = rating;
        this.date=date;


    }


}
