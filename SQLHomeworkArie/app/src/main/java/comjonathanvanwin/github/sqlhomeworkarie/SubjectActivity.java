package comjonathanvanwin.github.sqlhomeworkarie;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SubjectActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etCode, etSubject, etTeacher;
    Button btAdd, btShowTable;
    SQLiteDatabase subjectDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        subjectDatabase = openOrCreateDatabase("subjects", MODE_PRIVATE, null);
        subjectDatabase.execSQL("CREATE TABLE IF NOT EXISTS subjects(code INTEGER PRIMARY KEY, subject TEXT, teacher TEXT)");

        etCode = findViewById(R.id.etSubjectCode);
        etSubject = findViewById(R.id.etSubjectSubject);
        etTeacher = findViewById(R.id.etSubjectTeacher);
        btShowTable = findViewById(R.id.btSubjectShowTable);
        btShowTable.setOnClickListener(this);
        btAdd = findViewById(R.id.btSubjectAdd);
        btAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btAdd) {
            try {
                subjectDatabase.execSQL("INSERT INTO subjects VALUES(" + etCode.getText().toString() + ",'" +
                        etSubject.getText().toString() + "','" +
                        etTeacher.getText().toString() + "')");
                Toast.makeText(this, "Adding to Subject database...", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Something wrong occurred while adding to table 'Subjects'", Toast.LENGTH_SHORT).show();
            }
            etCode.setText("");
            etSubject.setText("");
            etTeacher.setText("");
        }
        if (v == btShowTable) {
            Cursor cursor = subjectDatabase.rawQuery("SELECT * FROM subjects", null);
            if (cursor.moveToFirst()) {
                String tb = "";
                do {
                    tb += "Code: " + cursor.getInt(0) + ", Subject: " + cursor.getString(1) + ", Teacher: " + cursor.getString(2) + "\n";
                } while (cursor.moveToNext());
                Toast.makeText(this, tb, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "The table 'Subjects' is empty", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
