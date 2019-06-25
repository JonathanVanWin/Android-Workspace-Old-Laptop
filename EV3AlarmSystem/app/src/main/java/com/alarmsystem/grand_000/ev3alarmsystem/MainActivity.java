package com.alarmsystem.grand_000.ev3alarmsystem;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    public static final String pwd_file_name = "file";
    public static FileHandler file_handler = new FileHandler(pwd_file_name);

    private Button button_start;
    private Button button_change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();

    }

    public void addListenerOnButton() {
        button_start = (Button) findViewById(R.id.button2);
        button_change = (Button) findViewById(R.id.button4);
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if (!getFileStreamPath(pwd_file_name).exists()) {
                    startActivity(new Intent(MainActivity.this, CreatePwdActivity.class));

                }
                else {
                    Toast.makeText(MainActivity.this, button_start.getText() + "ing...", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(MainActivity.this, PasswordActivity.class);
                    if (mBluetoothAdapter != null && !mBluetoothAdapter.isEnabled()) {
                        mBluetoothAdapter.enable();
                    }
                    startActivity(myIntent);
                }
            }
        });

        button_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreatePwdActivity.class));
            }
        });

    }

}