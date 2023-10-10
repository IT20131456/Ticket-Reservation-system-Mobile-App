package com.example.mobileapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mobileapp.R;
import com.example.mobileapp.data.model.TrainSchedule;

import java.util.List;

public class ScheduleAdapter extends ArrayAdapter<TrainSchedule> {
    private Context context;
    private int resource; // The layout resource ID for each item
    private List<TrainSchedule> scheduleList;

    public ScheduleAdapter(Context context, int resource, List<TrainSchedule> scheduleList) {
        super(context, resource, scheduleList);
        this.context = context;
        this.resource = resource;
        this.scheduleList = scheduleList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        // Get the TrainSchedule object at the current position
        TrainSchedule schedule = scheduleList.get(position);

        // Find and populate the TextViews in the list item layout
        TextView trainNameTextView = convertView.findViewById(R.id.schedulesTrainNameTextView);
        TextView departureTimeTextView = convertView.findViewById(R.id.schedulesDepartureTimeTextView);
        TextView fromToTextView = convertView.findViewById(R.id.schedulesFromTo);

        // Set the data from the TrainSchedule object into the TextViews
        trainNameTextView.setText(schedule.getTrain_name());
        fromToTextView.setText(schedule.getDeparture_station() + " -> " + schedule.getArrival_station());
        departureTimeTextView.setText("Departure Time: " + schedule.getDeparture_time());
        // Populate other TextViews similarly

        return convertView;
    }
}
