package com.dev.ipati.ioiousb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbAccessory;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.widget.Toast;

/**
 * Created by ipati on 27/4/2560.
 */

public class BoardCast extends BroadcastReceiver {
    private static final String ACTION_USB_PERMISSION =
            "com.android.example.USB_PERMISSION";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (ACTION_USB_PERMISSION.equals(action)) {
            synchronized (this) {
                UsbAccessory accessory = (UsbAccessory) intent.getParcelableExtra(UsbManager.EXTRA_ACCESSORY);
                if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                    Toast.makeText(context, "ReadyPortUsbCommand", Toast.LENGTH_SHORT).show();
                    if (accessory != null) {
                        Toast.makeText(context, "Complete", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //Todo:DisConnection...
                    Toast.makeText(context, "Please try again", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}
