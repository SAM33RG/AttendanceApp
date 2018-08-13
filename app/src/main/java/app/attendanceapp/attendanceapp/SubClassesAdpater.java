package app.attendanceapp.attendanceapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SubClassesAdpater extends RecyclerView.Adapter<SubClassesAdpater.ViewHolder> {

    private Context context;
    private List<DateTitle> listItems;
    private String activity;


    public SubClassesAdpater(Context c, List list){
        context = c;
        this.listItems = list;
        this.activity = activity;
    }

    @Override
    public SubClassesAdpater.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_sub_classes, parent, false);

        return new SubClassesAdpater.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(SubClassesAdpater.ViewHolder holder, final int position) {
        holder.date.setText(listItems.get(position).date);
        holder.title.setText(listItems.get(position).title);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activity.compareToIgnoreCase("add student")==0) {
                    context.startActivity(new Intent(context,AddStudentDataActivity.class).putExtra("className",listItems.get(position).id));
                }else  if (activity.compareToIgnoreCase("take attendance")==0) {
                    context.startActivity(new Intent(context,TakeStudentAttendanceActivity.class).putExtra("className",listItems.get(position).id));
                }else  if (activity.compareToIgnoreCase("view attendance")==0) {
                    context.startActivity(new Intent(context,TakeStudentAttendanceActivity.class).putExtra("className",listItems.get(position).id));
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();

    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView date, title;
        public CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date);
            title = (TextView) itemView.findViewById(R.id.title);
            cardView =  (CardView) itemView.findViewById(R.id.main_card_view);
        }
    }
}
