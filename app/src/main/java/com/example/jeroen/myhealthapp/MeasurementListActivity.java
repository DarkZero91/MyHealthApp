package com.example.jeroen.myhealthapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jeroen.myhealthapp.dao.PulseDao;
import com.example.jeroen.myhealthapp.models.Measurement;
import com.example.jeroen.myhealthapp.models.Pulse;

import java.util.ArrayList;
import java.util.List;

public class MeasurementListActivity extends AppCompatActivity {
    public static int ECG_TYPE = 0;
    public static int PULSE_TYPE = 1;
    public static int BLOOD_PRESSURE_TYPE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement_list);
        int type = getIntent().getIntExtra("measurement_type", ECG_TYPE);

        PulseDao dao = PulseDao.getDao(this);
        dao.open();
        //populate(dao);

        List<Pulse> values = dao.getAll();
        ArrayAdapter<Pulse> adapter = new ArrayAdapter<Pulse>(this, R.layout.measurement_list_row, R.id.text1, values);

        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

        dao.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_measurement_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void populate(PulseDao dao) {
        Pulse pulse = new Pulse();
        int[] data = {100, 1000, 200, 300, 150};
        for(int i = 0; i < 10; i++) { pulse.addData(data); }

        dao.save(pulse);
    }
}
