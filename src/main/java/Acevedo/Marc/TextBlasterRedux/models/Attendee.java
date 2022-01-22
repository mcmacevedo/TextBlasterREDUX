package Acevedo.Marc.TextBlasterRedux.models;

public record Attendee (String firstName, String lastName, String email, String phoneNumber, Integer seatNumber) {
        public String getFullName(){return String.format("%s %s", firstName, lastName);}
}