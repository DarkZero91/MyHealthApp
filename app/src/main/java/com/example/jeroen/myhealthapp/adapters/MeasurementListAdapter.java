package com.example.jeroen.myhealthapp.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jeroen.myhealthapp.R;
import com.example.jeroen.myhealthapp.models.BloodPressure;
import com.example.jeroen.myhealthapp.models.ECG;
import com.example.jeroen.myhealthapp.models.Measurement;
import com.example.jeroen.myhealthapp.models.Pulse;
import com.example.jeroen.myhealthapp.network.MyHealthService;
import com.example.jeroen.myhealthapp.listeners.OnMeasurementClickListener;

import java.util.List;

/**
 * Created by Jeroen on 17-11-2015.
 */
public class MeasurementListAdapter extends ArrayAdapter<Measurement> {
    private OnMeasurementClickListener listener;
    private Context context;

    public MeasurementListAdapter(Context context, int resource, List<Measurement> items,
                                  MyHealthService service) {
        super(context, resource, items);
        listener = new OnMeasurementClickListener(items, service, context);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.measurement_list_row, null);
        }

        Measurement m = getItem(position);

        if (m != null) {
            ImageView img = (ImageView) convertView.findViewById(R.id.list_image);
            TextView label = (TextView) convertView.findViewById(R.id.text_label);
            TextView date = (TextView) convertView.findViewById(R.id.text_date);

            if (img != null) { setImage(img, m); }
            if (label != null) { label.setText(m.toString()); }
            if (date != null) { date.setText("" + m.getTimestamp()); }

            ImageButton button = (ImageButton) convertView.findViewById(R.id.sync_btn);
            button.setTag(position);

            if(m.isSynchronized()) {
                button.setColorFilter(ContextCompat.getColor(context, R.color.green_500));
            } else {
                button.setColorFilter(ContextCompat.getColor(context, R.color.grey_200));
            }

            button.setOnClickListener(listener);
        }

        return convertView;
    }

    private void setImage(ImageView view, Measurement m) {
        if(m instanceof Pulse) {
            view.setImageResource(R.drawable.pulse1);
        } else if(m instanceof ECG) {
            view.setImageResource(R.drawable.electrocardiogram1);
        } else if(m instanceof BloodPressure) {
            view.setImageResource(R.drawable.blood12);
        }
    }
}
