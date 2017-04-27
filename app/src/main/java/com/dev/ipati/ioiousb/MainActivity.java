package com.dev.ipati.ioiousb;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.ToggleButton;

import ioio.lib.api.DigitalOutput;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.android.IOIOActivity;


public class MainActivity extends IOIOActivity {
    ToggleButton mToggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToggleButton = (ToggleButton) findViewById(R.id.openStatus);

    }

    class Looper extends BaseIOIOLooper {
        DigitalOutput digitalOutputPort0;

        @Override
        protected void setup() throws ConnectionLostException, InterruptedException {
            super.setup();
            digitalOutputPort0 = ioio_.openDigitalOutput(2);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Connection", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void loop() throws ConnectionLostException, InterruptedException {
            super.loop();
            digitalOutputPort0.write(mToggleButton.isChecked());
        }

    }

    protected IOIOLooper createIOIOLooper() {
        return new Looper();
    }
}
