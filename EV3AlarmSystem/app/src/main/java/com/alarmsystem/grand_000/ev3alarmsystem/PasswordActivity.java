package com.alarmsystem.grand_000.ev3alarmsystem;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class PasswordActivity extends AppCompatActivity {
    private EditText password_field;
    private Button check_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_activity);
        addListenerOnButton();

    }

    public void addListenerOnButton() {
        check_button = (Button) findViewById(R.id.button);
        password_field = (EditText) findViewById(R.id.editText);

        check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                final String pwd = MainActivity.file_handler.readFromFile(PasswordActivity.this);
                if (pwd.equals(password_field.getText().toString())) {
                    bluetoothAdapter.startDiscovery();

                    Toast.makeText(PasswordActivity.this, "Correct\nTry to connect to your Robot", Toast.LENGTH_SHORT).show();
                    if(bluetoothAdapter.getProfileConnectionState(BluetoothHeadset.HEADSET) == BluetoothHeadset.STATE_CONNECTED){
                        Toast.makeText(PasswordActivity.this, "Connected!", Toast.LENGTH_SHORT).show();
                    }else{
                        String deviceName = "CHESSAJEDREZ";

                        BluetoothDevice result = null;

                        Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
                        if (devices != null) {
                            for (BluetoothDevice device : devices) {
                                if (deviceName.equals(device.getName())) {
                                    result = device;
                                    try {
                                        BluetoothSocket b = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
                                        Object proxy = 1;
                                        b.connect();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                }
                            }
                            try {
                                DataOutputStream os = new DataOutputStream(result.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb")).getOutputStream());
                                os.writeBytes("Hello pc!!!!");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                        //startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));

                    }



                } else {
                    Toast.makeText(PasswordActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                    password_field.setText("", TextView.BufferType.EDITABLE);
                }

            }
        });


    }
}
