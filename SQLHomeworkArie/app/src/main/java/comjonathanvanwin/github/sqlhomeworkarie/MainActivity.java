package comjonathanvanwin.github.sqlhomeworkarie;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Student> list;
    StudentAdapter adapter;
    ViewPager viewPager;
    ViewPagerAdapter vPagerAdapter;
    TabLayout tabLayout;
    Spinner spinner;
    Button btStudent, btMark, btSubject;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //For multiple fragments(tabs)
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.pager);
        vPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(vPagerAdapter);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        spinner = findViewById(R.id.spSelectStudent);
        SQLiteDatabase studentsDb = openOrCreateDatabase("students", MODE_PRIVATE, null);
        studentsDb.execSQL("CREATE TABLE IF NOT EXISTS students(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER)");
        Cursor cursor = studentsDb.rawQuery("SELECT * FROM students", null);
        list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                list.add(new Student(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            } while (cursor.moveToNext());
        }
        adapter = new StudentAdapter(list, this);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tvId = view.findViewById(R.id.tvSelectId);
                TextView tvName = view.findViewById(R.id.tvSelectName);
                TextView tvAge = view.findViewById(R.id.tvSelectAge);
                SQLiteDatabase db = openOrCreateDatabase("marks", MODE_PRIVATE, null);

                Cursor cursor = db.rawQuery("SELECT * FROM marks WHERE id="+tvId.getText().toString()+"", null);
                if (cursor.moveToFirst()) {
                    String tb = "";
                    do {
                        tb += "Name: " + tvName.getText().toString() + ", Code: " + cursor.getInt(1) + ", Mark: " + cursor.getInt(2) + "\n";
                    } while (cursor.moveToNext());
                    Toast.makeText(MainActivity.this, tb, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "The table 'Marks' is empty", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btStudent = findViewById(R.id.btStudent);
        btSubject = findViewById(R.id.btSubject);
        btMark = findViewById(R.id.btMark);
        btStudent.setOnClickListener(this);
        btMark.setOnClickListener(this);
        btSubject.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btStudent) {
            Intent intent = new Intent(this, StudentActivity.class);
            startActivity(intent);
        }
        if (v == btSubject) {
            Intent intent = new Intent(this, SubjectActivity.class);
            startActivity(intent);
        }
        if (v == btMark) {
            Intent intent = new Intent(this, MarkActivity.class);
            startActivity(intent);
        }
    }
}
