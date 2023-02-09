package ar.com.hotel.controller;

import ar.com.hotel.dao.ReservationDAO;
import ar.com.hotel.factory.ConnectionFactory;
import ar.com.hotel.model.Reservation;
import java.util.List;

public class ReservationController {
    
    private final ReservationDAO reservationDAO;

    public ReservationController() {
        this.reservationDAO = new ReservationDAO(new ConnectionFactory().getConnection());
    }

    public Reservation create(Reservation reservation) {
        return reservationDAO.create(reservation);
    }

    public List<Reservation> read() {
        return reservationDAO.read();
    }

    public int update(Reservation reservation) {
        return reservationDAO.update(reservation);
    }

    public int delete(Integer id) {
        return reservationDAO.delete(id);
    }
}
