package comjonathanvanwin.github.sqlhomeworkarie;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MarkActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etId, etCode, etMark;
    Button btAdd, btShowTable;
    SQLiteDatabase markDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark);
        markDatabase = openOrCreateDatabase("marks", MODE_PRIVATE, null);
        markDatabase.execSQL("CREATE TABLE IF NOT EXISTS marks(id INTEGER, code INTEGER, mark INTEGER)");

        etId = findViewById(R.id.etMarkId);
        etCode = findViewById(R.id.etMarkCode);
        etMark = findViewById(R.id.etMarkMark);
        btShowTable = findViewById(R.id.btMarkShowTable);
        btShowTable.setOnClickListener(this);
        btAdd = findViewById(R.id.btMarkAdd);
        btAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btAdd) {
            try {
                markDatabase.execSQL("INSERT INTO marks VALUES(" + etId.getText().toString() + "," +
                        etCode.getText().toString() + "," +
                        etMark.getText().toString() + ")");
                Toast.makeText(this, "Adding to Mark database...", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Something wrong occurred while adding to table 'Marks'", Toast.LENGTH_SHORT).show();
            }
            etId.setText("");
            etCode.setText("");
            etMark.setText("");
        }
        if (v == btShowTable) {
            Cursor cursor = markDatabase.rawQuery("SELECT * FROM marks", null);
            if (cursor.moveToFirst()) {
                String tb = "";
                do {
                    tb += "Id: " + cursor.getInt(0) + ", Code: " + cursor.getInt(1) + ", Mark: " + cursor.getInt(2) + "\n";
                } while (cursor.moveToNext());
                Toast.makeText(this, tb, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "The table 'Marks' is empty", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
