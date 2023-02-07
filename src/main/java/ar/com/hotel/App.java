package ar.com.hotel;

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
        setFrameDefaultConfig(new LoginView());
    }

    public static void openHotelNavigation() {
        setFrameDefaultConfig(new HotelNavigationView());
    }

    public static void openReservation() {
        setFrameDefaultConfig(new ReservationView());
    }

    public static void openGuest(JFrame reservationFrame) {
        setFrameDefaultConfig(new GuestView(reservationFrame));
    }

    public static void openSearch() {
        setFrameDefaultConfig(new SearchView());
    }
}
