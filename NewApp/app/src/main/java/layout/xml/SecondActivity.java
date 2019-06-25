package layout.xml;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.grand_000.newapp.MainActivity;
import com.example.grand_000.newapp.R;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private static Button button_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hello how are you?", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        onButtonReturnClickListener();
    }

    public void onButtonReturnClickListener(){
        button_return = (Button) findViewById(R.id.button3);
        button_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(SecondActivity.this, "Returning to Main Activity...", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
