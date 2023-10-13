package com.example.mobileapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model class representing a train reservation.
 * Implements Parcelable to allow for object transfer between components.
 */
public class Reservation implements Parcelable {
    private String id;  // Object ID
    private String reservation_number;  // Unique reservation number
    private String reference_id;    // NIC of the user
    private String train_id;    // ID of the booked train
    private String train_name;  // Name of the booked train
    private String travel_route;    // Route
    private String from;    // Departure station
    private String to;  // Arrival station
    private String booking_date;    // Date created
    private String reservation_date;    // Reservation date
    private Integer ticket_class;   // Class if the ticket
    private Integer number_of_tickets;  // Number of tickets reserved
    private Integer total_price;    // Total price for reservation
    private String status;  // Status of the reservation

    // Constructors, getters, setters, and Parcelable implementation

    /**
     * Default constructor.
     */
    public Reservation() {
    }

    /**
     * Parameterized constructor to initialize a Reservation object.
     */
    public Reservation(String id, String reservation_number, String reference_id, String train_id, String train_name, String travel_route, String from, String to, String booking_date, Integer ticket_class, Integer number_of_tickets, Integer total_price, String status, String reservation_date) {
        this.id = id;
        this.reservation_number = reservation_number;
        this.reference_id = reference_id;
        this.train_id = train_id;
        this.train_name = train_name;
        this.travel_route = travel_route;
        this.from = from;
        this.to = to;
        this.booking_date = booking_date;
        this.ticket_class = ticket_class;
        this.number_of_tickets = number_of_tickets;
        this.total_price = total_price;
        this.status = status;
        this.reservation_date = reservation_date;
    }

    // Parcelable implementation
    protected Reservation(Parcel in) {
        // Read data from Parcel and assign to class members
        id = in.readString();
        reservation_number = in.readString();
        reference_id = in.readString();
        train_id = in.readString();
        train_name = in.readString();
        travel_route = in.readString();
        from = in.readString();
        to = in.readString();
        booking_date = in.readString();
        ticket_class = in.readInt();
        number_of_tickets = in.readInt();
        total_price = in.readInt();
        status = in.readString();
        reservation_date = in.readString();
    }

    public static final Creator<Reservation> CREATOR = new Creator<Reservation>() {
        @Override
        public Reservation createFromParcel(Parcel in) {
            // Create a Reservation object from a Parcel
            return new Reservation(in);
        }

        @Override
        public Reservation[] newArray(int size) {
            // Create an array of Reservation objects
            return new Reservation[size];
        }
    };

    @Override
    public int describeContents() {
        // Required method for Parcelable, but not used in this case
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // Write class members to Parcel for data transfer
        dest.writeString(id);
        dest.writeString(reservation_number);
        dest.writeString(reference_id);
        dest.writeString(train_id);
        dest.writeString(train_name);
        dest.writeString(travel_route);
        dest.writeString(from);
        dest.writeString(to);
        dest.writeString(booking_date);
        dest.writeInt(ticket_class);
        dest.writeInt(number_of_tickets);
        dest.writeInt(total_price);
        dest.writeString(status);
        dest.writeString(reservation_date);
    }

    // Getters and Setters
    public String getReservation_number() {
        return reservation_number;
    }

    public void setReservation_number(String reservation_number) {
        this.reservation_number = reservation_number;
    }

    public String getReference_id() {
        return reference_id;
    }

    public void setReference_id(String reference_id) {
        this.reference_id = reference_id;
    }

    public String getTrain_id() {
        return train_id;
    }

    public void setTrain_id(String train_id) {
        this.train_id = train_id;
    }

    public String getTrain_name() {
        return train_name;
    }

    public void setTrain_name(String train_name) {
        this.train_name = train_name;
    }

    public String getTravel_route() {
        return travel_route;
    }

    public void setTravel_route(String travel_route) {
        this.travel_route = travel_route;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public Integer getTicket_class() {
        return ticket_class;
    }

    public void setTicket_class(Integer ticket_class) {
        this.ticket_class = ticket_class;
    }

    public Integer getNumber_of_tickets() {
        return number_of_tickets;
    }

    public void setNumber_of_tickets(Integer number_of_tickets) {
        this.number_of_tickets = number_of_tickets;
    }

    public Integer getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Integer total_price) {
        this.total_price = total_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(String reservation_date) {
        this.reservation_date = reservation_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String Id) {
        id = Id;
    }
}
