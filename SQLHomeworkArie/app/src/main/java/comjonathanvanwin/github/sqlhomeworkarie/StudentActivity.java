package comjonathanvanwin.github.sqlhomeworkarie;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etName, etAge;
    Button btAdd, btShowTable;
    SQLiteDatabase studentDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        studentDatabase = openOrCreateDatabase("students", MODE_PRIVATE, null);
        studentDatabase.execSQL("CREATE TABLE IF NOT EXISTS students(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER)");

        etName = findViewById(R.id.etStudentName);
        etAge = findViewById(R.id.etStudentAge);
        btAdd = findViewById(R.id.btStudentAdd);
        btShowTable = findViewById(R.id.btStudentShowTable);
        btShowTable.setOnClickListener(this);
        btAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btAdd) {
            try {
                studentDatabase.execSQL("INSERT INTO students(name, age) VALUES('"+etName.getText().toString() + "'," +
                        etAge.getText().toString() + ")");
                Toast.makeText(this, "Adding to Student database...", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Something wrong occurred while adding to table 'Students'", Toast.LENGTH_SHORT).show();
            }
            etName.setText("");
            etAge.setText("");
        }
        if (v == btShowTable) {
            Cursor cursor = studentDatabase.rawQuery("SELECT * FROM students", null);
            if(cursor.moveToFirst()) {
                String tb = "";
                do {
                    tb += "Id: " + cursor.getInt(0) + ", Name: " + cursor.getString(1) + ", Age: " + cursor.getInt(2) + "\n";
                } while (cursor.moveToNext());
                Toast.makeText(this, tb, Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "The table 'Students' is empty", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
