package ar.com.hotel.controller;

import ar.com.hotel.dao.GuestDAO;
import ar.com.hotel.factory.ConnectionFactory;
import ar.com.hotel.model.Guest;
import java.util.List;

public class GuestController {

    private final GuestDAO guestDAO;

    public GuestController() {
        this.guestDAO = new GuestDAO(new ConnectionFactory().getConnection());
    }

    public Guest create(Guest guest, Integer reservationId) {
        guest.setReservationID(reservationId);
        return guestDAO.create(guest);
    }

    public List<Guest> read() {
        return guestDAO.read();
    }

    public int update(Guest guest) {
        return guestDAO.update(guest);
    }

    public int delete(Integer id) {
        return guestDAO.delete(id);
    }

}
