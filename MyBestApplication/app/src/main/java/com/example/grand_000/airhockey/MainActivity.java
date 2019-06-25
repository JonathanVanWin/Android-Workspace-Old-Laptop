package com.example.grand_000.airhockey;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import static com.example.grand_000.airhockey.Pitag.printS;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view) {
        EditText eDay = (EditText) findViewById(R.id.editTextDay);
        EditText eMonth = (EditText) findViewById(R.id.editTextMonth);
        EditText eYear = (EditText) findViewById(R.id.editTextYear);
        EditText output = (EditText) findViewById(R.id.editText);
        TextView t1 = (TextView) findViewById(R.id.textView);
        Button button = (Button)findViewById(R.id.button);
        int day = Integer.parseInt(eDay.getText().toString());
        int month = Integer.parseInt(eMonth.getText().toString());
        int year = Integer.parseInt(eYear.getText().toString());
        Pitag s = new Pitag(day, month, year);
        output.setText(printS(s));
        t1.setText("Here is your scale!");
        Toast.makeText(MainActivity.this,"Hartelijk Gefeliciteerd! Ik hoop dat je mijn cadeau leuk zal finden",Toast.LENGTH_LONG).show();
    }


}
