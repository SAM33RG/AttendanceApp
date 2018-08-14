package app.attendanceapp.attendanceapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ShowAttendanceAdapter extends RecyclerView.Adapter<ShowAttendanceAdapter.ViewHolder> {

    private Context context;
    private List<StudentData> listItems;


    public ShowAttendanceAdapter(Context c, List list){
        context = c;
        this.listItems = list;
    }

    @Override
    public ShowAttendanceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_show_attendance, parent, false);

        return new ShowAttendanceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ShowAttendanceAdapter.ViewHolder holder, final int position) {
        holder.name.setText(listItems.get(position).name);
        holder.rollNO.setText(listItems.get(position).rollno);
        holder.attendance.setText(listItems.get(position).status);

    }

    @Override
    public int getItemCount() {
        return listItems.size();

    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name,rollNO, attendance;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            rollNO = (TextView) itemView.findViewById(R.id.rollno);
            attendance = (TextView) itemView.findViewById(R.id.attendance);

        }
    }
}
