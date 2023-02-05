package ar.com.hotel;

import ar.com.hotel.controller.*;
import ar.com.hotel.view.*;
import javax.swing.JFrame;

public class App {

    public static void main(String[] args) {
        openWelcome();
    }

    private static void setFrameDefaultConfig(JFrame Frame) {
        Frame.setLocationRelativeTo(null); // La centra en la pantalla
        Frame.setResizable(false); // Hace que mantenga su tamaño
        Frame.setVisible(true); // La hace visible 
    }

    public static void openQuestion(JFrame frame) {
        setFrameDefaultConfig(new QuestionView(frame));
    }

    public static void openQuestion(JFrame frame, String message) {
        setFrameDefaultConfig(new QuestionView(frame, message));
    }

    public static void openWelcome() {
        setFrameDefaultConfig(new WelcomeView());
    }

    public static void openLogin() {
        setFrameDefaultConfig(new LoginView(new UserController()));
    }

    public static void openHotelNavigation() {
        setFrameDefaultConfig(new HotelNavigationView());
    }

    public static void openReservation() {
        setFrameDefaultConfig(new ReservationView(new ReservationController()));
    }

    public static void openGuest() {
        setFrameDefaultConfig(new GuestView(new GuestController()));
    }

    public static void openSearch() {
        setFrameDefaultConfig(new SearchView(new ReservationController(), new GuestController()));
    }
}
