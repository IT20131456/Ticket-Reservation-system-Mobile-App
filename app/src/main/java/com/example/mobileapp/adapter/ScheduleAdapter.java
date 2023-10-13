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

/**
 * Custom ArrayAdapter to display a list of TrainSchedule objects in a ListView.
 */
public class ScheduleAdapter extends ArrayAdapter<TrainSchedule> {
    private Context context;
    private int resource; // The layout resource ID for each item
    private List<TrainSchedule> scheduleList;

    /**
     * Constructor for the ScheduleAdapter.
     *
     * @param context     The application context.
     * @param resource    The layout resource ID for each item in the list.
     * @param scheduleList The list of TrainSchedule objects to be displayed.
     */
    public ScheduleAdapter(Context context, int resource, List<TrainSchedule> scheduleList) {
        super(context, resource, scheduleList);
        this.context = context;
        this.resource = resource;
        this.scheduleList = scheduleList;
    }

    /**
     * Override method to populate the view for a single item in the list.
     *
     * @param position    The position of the item in the list.
     * @param convertView  The recycled view to populate, or null if no recycled view is available.
     * @param parent      The parent view that this view will eventually be attached to.
     * @return The View for the item at the specified position.
     */
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

        return convertView;
    }
}
