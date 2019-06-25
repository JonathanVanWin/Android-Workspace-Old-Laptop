package com.alarmsystem.grand_000.ev3alarmsystem;


import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreatePwdActivity extends AppCompatActivity {

    private Button button_submit;
    private EditText password_field;
    private EditText password_field_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_pwd_activity);
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        button_submit = (Button) findViewById(R.id.button3);
        password_field = (EditText) findViewById(R.id.editText2);
        password_field_2 = (EditText) findViewById(R.id.editText3);


        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (password_field.getText().toString().length() < 4 || password_field_2.getText().toString().length() < 4) {
                    Toast.makeText(CreatePwdActivity.this, "Enter a password with at least 4 digits", Toast.LENGTH_SHORT).show();
                    password_field.setText("", TextView.BufferType.EDITABLE);
                    password_field_2.setText("", TextView.BufferType.EDITABLE);
                } else if (!password_field.getText().toString().equals(password_field_2.getText().toString())) {
                    Toast.makeText(CreatePwdActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                    password_field.setText("", TextView.BufferType.EDITABLE);
                    password_field_2.setText("", TextView.BufferType.EDITABLE);
                } else {
                    MainActivity.file_handler.writeToFile(password_field.getText().toString(), CreatePwdActivity.this);
                    Toast.makeText(CreatePwdActivity.this, "Password successfully created", Toast.LENGTH_SHORT).show();
                    Toast.makeText(CreatePwdActivity.this, "Starting...", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(CreatePwdActivity.this, PasswordActivity.class);
                    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                    if (mBluetoothAdapter != null && !mBluetoothAdapter.isEnabled()) {
                        mBluetoothAdapter.enable();
                    }
                    startActivity(myIntent);
                }
            }
        });
    }
}
