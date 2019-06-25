package comjonathanvanwin.github.sqlhomeworkarie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter{

    private ArrayList<Student> students;
    private Context context;

    public StudentAdapter(ArrayList<Student> students, Context context) {
        this.students = students;
        this.context = context;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Student s = students.get(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.student_layout, null);
        TextView tvId = convertView.findViewById(R.id.tvSelectId);
        TextView tvName = convertView.findViewById(R.id.tvSelectName);
        TextView tvAge = convertView.findViewById(R.id.tvSelectAge);
        tvId.setText(""+s.getId());
        tvName.setText(s.getName());
        tvAge.setText(""+s.getAge());
        return convertView;
    }
}
