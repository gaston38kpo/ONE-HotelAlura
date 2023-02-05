
package ar.com.hotel.model;

import java.sql.Date;

public class Reservation {
    private Integer id;
    private Date entryDate;
    private Date exitDate;
    private Integer value;
    private String paymentMethod;

    public Reservation(Integer id, Date entryDate, Date exitDate, Integer value, String paymentMethod) {
        this.id = id;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.value = value;
        this.paymentMethod = paymentMethod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getExitDate() {
        return exitDate;
    }

    public void setExitDate(Date exitDate) {
        this.exitDate = exitDate;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", entryDate=" + entryDate + ", exitDate=" + exitDate + ", value=" + value + ", paymentMethod=" + paymentMethod + '}';
    }
    
    
}
