package com.example.mobileapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TrainSchedule implements Parcelable {
    private String startingPoint;
    private String destination;
    private String trainName;
    private String departureTime;
    private String arrivalTime;
    private int availableSeats;
    private String availableClasses;
    private String stations;

    // Constructors, getters, setters, and Parcelable implementation here

    public TrainSchedule() {
        this.startingPoint = "station 1";
        this.destination = "station 2";
        this.trainName = "trainName";
        this.departureTime = "13.00 PM";
        this.arrivalTime = "20.00 PM";
        this.availableSeats = 10;
        this.availableClasses = "1, 2 , 3";
        this.stations = "station1, station 2";
    }

    public TrainSchedule(String startingPoint, String destination, String trainName, String startTime, String departureTime, int availableSeats, String availableClasses, String stations) {
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.trainName = trainName;
        this.departureTime = departureTime;
        this.arrivalTime = startTime;
        this.availableSeats = availableSeats;
        this.availableClasses = availableClasses;
        this.stations = stations;
    }

    // Parcelable implementation
    protected TrainSchedule(Parcel in) {
        startingPoint = in.readString();
        destination = in.readString();
        trainName = in.readString();
        departureTime = in.readString();
        arrivalTime = in.readString();
        availableSeats = in.readInt();
        availableClasses = in.readString();
        stations = in.readString();
    }

    public static final Creator<TrainSchedule> CREATOR = new Creator<TrainSchedule>() {
        @Override
        public TrainSchedule createFromParcel(Parcel in) {
            return new TrainSchedule(in);
        }

        @Override
        public TrainSchedule[] newArray(int size) {
            return new TrainSchedule[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(startingPoint);
        dest.writeString(destination);
        dest.writeString(trainName);
        dest.writeString(arrivalTime);
        dest.writeString(departureTime);
        dest.writeInt(availableSeats);
        dest.writeString(availableClasses);
        dest.writeString(stations);
    }

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void setAvailableClasses(String availableClasses) {
        this.availableClasses = availableClasses;
    }

    public void setStations(String stations) {
        this.stations = stations;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public String getDestination() {
        return destination;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getAvailableClasses() {
        return availableClasses;
    }

    public String getStations() {
        return stations;
    }
}
