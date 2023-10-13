package com.example.mobileapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class representing train schedule information.
 * Implements Parcelable to allow for object transfer between components.
 */
public class TrainSchedule implements Parcelable {
    private String id;  // Unique identifier
    private String train_number;  // Train number (Unique)
    private String train_name;  // Train name
    private String train_type;  // Train type
    private String train_description;  // Train description
    private String departure_station;  // Departure station
    private String arrival_station;  // Arrival station
    private String departure_time;  // Departure time
    private String arrival_time;  // Arrival time
    private String travel_duration;  // Duration of the travel
    private List<String> intermediate_stops;  // List of intermediate stops
    private List<String> seat_classes;  // List of available seat classes
    private List<String> number_of_seats;  // List of the number of seats available for each class
    private Integer isActive;  // Active status

    // Constructors, getters, setters, and Parcelable implementation

    /**
     * Default constructor.
     */
    public TrainSchedule() {
    }

    /**
     * Parameterized constructor to initialize a TrainSchedule object.
     */
    public TrainSchedule(String id, String train_number, String train_name, String train_type, String train_description, String departure_station, String arrival_station, String departure_time, String arrival_time, String travel_duration, List<String> intermediate_stops, List<String> seat_classes, List<String> number_of_seats, Integer isActive) {
        this.id = id;
        this.train_number = train_number;
        this.train_name = train_name;
        this.train_type = train_type;
        this.train_description = train_description;
        this.departure_station = departure_station;
        this.arrival_station = arrival_station;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
        this.travel_duration = travel_duration;
        this.intermediate_stops = intermediate_stops;
        this.seat_classes = seat_classes;
        this.number_of_seats = number_of_seats;
        this.isActive = isActive;
    }

    // Parcelable implementation
    protected TrainSchedule(Parcel in) {
        // Read data from Parcel and assign to class members
        id = in.readString();
        train_number = in.readString();
        train_name = in.readString();
        train_type = in.readString();
        train_description = in.readString();
        departure_station = in.readString();
        arrival_station = in.readString();
        departure_time = in.readString();
        arrival_time = in.readString();
        travel_duration = in.readString();
        isActive = in.readInt();

        // Read intermediateStops List
        intermediate_stops = new ArrayList<>();
        in.readList(intermediate_stops, String.class.getClassLoader());

        // Read seatClasses List
        seat_classes = new ArrayList<>();
        in.readList(seat_classes, String.class.getClassLoader());

        // Read numberOfSeats List
        number_of_seats = new ArrayList<>();
        in.readList(number_of_seats, String.class.getClassLoader());
    }

    public static final Creator<TrainSchedule> CREATOR = new Creator<TrainSchedule>() {
        @Override
        public TrainSchedule createFromParcel(Parcel in) {
            // Create a TrainSchedule object from a Parcel
            return new TrainSchedule(in);
        }

        @Override
        public TrainSchedule[] newArray(int size) {
            // Create an array of TrainSchedule objects
            return new TrainSchedule[size];
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
        dest.writeString(train_number);
        dest.writeString(train_name);
        dest.writeString(train_type);
        dest.writeString(train_description);
        dest.writeString(departure_station);
        dest.writeString(arrival_station);
        dest.writeString(departure_time);
        dest.writeString(arrival_time);
        dest.writeString(travel_duration);
        dest.writeInt(isActive);

        // Write intermediateStops List
        dest.writeList(intermediate_stops);

        // Write seatClasses List
        dest.writeList(seat_classes);

        // Write numberOfSeats List
        dest.writeList(number_of_seats);
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrain_number() {
        return train_number;
    }

    public void setTrain_number(String train_number) {
        this.train_number = train_number;
    }

    public String getTrain_name() {
        return train_name;
    }

    public void setTrain_name(String train_name) {
        this.train_name = train_name;
    }

    public String getTrain_type() {
        return train_type;
    }

    public void setTrain_type(String train_type) {
        this.train_type = train_type;
    }

    public String getTrain_description() {
        return train_description;
    }

    public void setTrain_description(String train_description) {
        this.train_description = train_description;
    }

    public String getDeparture_station() {
        return departure_station;
    }

    public void setDeparture_station(String departure_station) {
        this.departure_station = departure_station;
    }

    public String getArrival_station() {
        return arrival_station;
    }

    public void setArrival_station(String arrival_station) {
        this.arrival_station = arrival_station;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getTravel_duration() {
        return travel_duration;
    }

    public void setTravel_duration(String travel_duration) {
        this.travel_duration = travel_duration;
    }

    public List<String> getIntermediate_stops() {
        return intermediate_stops;
    }

    public void setIntermediate_stops(List<String> intermediate_stops) {
        this.intermediate_stops = intermediate_stops;
    }

    public List<String> getSeat_classes() {
        return seat_classes;
    }

    public void setSeat_classes(List<String> seat_classes) {
        this.seat_classes = seat_classes;
    }

    public List<String> getNumber_of_seats() {
        return number_of_seats;
    }

    public void setNumber_of_seats(List<String> number_of_seats) {
        this.number_of_seats = number_of_seats;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
}
