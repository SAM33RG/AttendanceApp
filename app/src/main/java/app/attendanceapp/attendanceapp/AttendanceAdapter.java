package app.attendanceapp.attendanceapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.ViewHolder>{

    private Context context;
    private List<AttendanceData> listItems;
    private String activity;


    public AttendanceAdapter(Context c, List list,String activity){
        context = c;
        this.listItems = list;
        this.activity = activity;
    }

    @Override
    public AttendanceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_attendance, parent, false);

        return new AttendanceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AttendanceAdapter.ViewHolder holder, final int position) {
        holder.name.setText(listItems.get(position).name);
        holder.rollNO.setText(listItems.get(position).rollno);
        if (listItems.get(position).status.compareTo("present")==0){
            holder.attendaceSwitch.setChecked(true);
        }
        holder.attendaceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    holder.attendaceSwitch.setText("present");
                    listItems.get(position).status="present";
                }else {
                    holder.attendaceSwitch.setText("absent");
                    listItems.get(position).status="absent";
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();

    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name,rollNO;
        public Switch attendaceSwitch;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.list_row_attendance_name);
            rollNO = (TextView) itemView.findViewById(R.id.list_row_attendance_roll_no);
            attendaceSwitch = (Switch) itemView.findViewById(R.id.list_row_attendance_attendance_switch);

        }
    }
}
