package ar.com.hotel;

import ar.com.hotel.controller.GuestController;
import ar.com.hotel.controller.ReservationController;
import ar.com.hotel.controller.UserController;
import ar.com.hotel.model.Reservation;
import ar.com.hotel.view.*;
import javax.swing.JFrame;

public class App {

    public static GuestController guestController = new GuestController();
    public static ReservationController reservationController = new ReservationController();
    public static UserController userController = new UserController();

    public static void main(String[] args) {
        openWelcome();
    }

    public static JFrame setFrameDefaultConfig(JFrame Frame) {
        Frame.setLocationRelativeTo(null); // La centra en la pantalla
        Frame.setResizable(false); // Hace que mantenga su tamaño
        Frame.setVisible(true); // La hace visible 
        return Frame;
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

    public static void openRegister() {
        setFrameDefaultConfig(new RegisterView());
    }

    public static void openHotelNavigation() {
        setFrameDefaultConfig(new HotelNavigationView());
    }

    public static void openReservation() {
        setFrameDefaultConfig(new ReservationView());
    }

    public static void openGuest(JFrame reservationFrame, Reservation newReservation) {
        setFrameDefaultConfig(new GuestView(reservationFrame, newReservation));
    }

    public static void openSearch() {
        setFrameDefaultConfig(new SearchView());
    }
}
