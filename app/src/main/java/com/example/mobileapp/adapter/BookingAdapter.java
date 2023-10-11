package com.example.mobileapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mobileapp.R;
import com.example.mobileapp.data.model.Reservation;

import java.util.List;

public class BookingAdapter extends ArrayAdapter<Reservation> {
    private Context context;
    private int resource; // The layout resource ID for each item (card layout)
    private List<Reservation> bookingList;

    public BookingAdapter(Context context, int resource, List<Reservation> bookingList) {
        super(context, resource, bookingList);
        this.context = context;
        this.resource = resource;
        this.bookingList = bookingList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        // Get the Reservation object at the current position
        Reservation reservation = bookingList.get(position);

        // Find and populate the TextViews in the card layout
        TextView reservationNumberTextView = convertView.findViewById(R.id.cardReservationIdTextView);
        TextView dateTextView = convertView.findViewById(R.id.cardDateTextView);
        TextView fromToTextView = convertView.findViewById(R.id.cardFromToTextView);
        TextView statusTextView = convertView.findViewById(R.id.cardStatusTextView);

        // Set the data from the Reservation object into the TextViews
        reservationNumberTextView.setText("Reservation ID: " + reservation.getReservation_number());
        dateTextView.setText("Date: " + reservation.getReservation_date());
        fromToTextView.setText("Route: " + reservation.getFrom() + " to " + reservation.getTo());
        statusTextView.setText("Status: " + reservation.getStatus());
        // Populate other TextViews similarly

        return convertView;
    }

}
