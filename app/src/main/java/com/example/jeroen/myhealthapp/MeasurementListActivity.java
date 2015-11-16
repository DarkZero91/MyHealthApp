package com.example.jeroen.myhealthapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jeroen.myhealthapp.dao.BloodPressureDao;
import com.example.jeroen.myhealthapp.dao.Dao;
import com.example.jeroen.myhealthapp.dao.DaoFactory;
import com.example.jeroen.myhealthapp.dao.ECGDao;
import com.example.jeroen.myhealthapp.dao.PulseDao;
import com.example.jeroen.myhealthapp.models.BloodPressure;
import com.example.jeroen.myhealthapp.models.ECG;
import com.example.jeroen.myhealthapp.models.Measurement;
import com.example.jeroen.myhealthapp.models.Pulse;

import java.util.List;

public class MeasurementListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement_list);
        int type = getIntent().getIntExtra("measurement_type", DaoFactory.PULSE);

        //populateDb();

        Dao dao = DaoFactory.getDao(type, this);
        dao.open();

        List<Measurement> values = dao.getAll();
        ArrayAdapter<Measurement> adapter = new ArrayAdapter<>(this, R.layout.measurement_list_row, R.id.text1, values);

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

    private void populateDb() {
        // ECG Data
        Dao dao = ECGDao.getDao(this);
        dao.open();

        ECG ecg = new ECG();
        int[] data = {100, 1000, 200, 300, 150};
        for(int i = 0; i < 10; i++) { ecg.addData(data); }
        dao.save(ecg);

        dao.close();

        // Pulse data
        dao = PulseDao.getDao(this);
        dao.open();

        Pulse pulse = new Pulse();
        pulse.setHeartRate((new Double(Math.random() * (100 - 60) + 60)).intValue());
        dao.save(pulse);

        dao.close();

        // Blood pressure data
        dao = BloodPressureDao.getDao(this);
        dao.open();

        BloodPressure pressure = new BloodPressure();
        pressure.setOver((new Double(Math.random() * (190 - 110) + 110)).intValue());
        pressure.setUnder((new Double(Math.random() * (120 - 70) + 70)).intValue());
        dao.save(pressure);

        dao.close();
    }
}
