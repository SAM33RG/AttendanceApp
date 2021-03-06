package app.attendanceapp.attendanceapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class AllClassesAdapter extends RecyclerView.Adapter<AllClassesAdapter.ViewHolder> {

    private Context context;
    private List<String> listItems;
    private String activity;


    public AllClassesAdapter(Context c, List list,String activity){
        context = c;
        this.listItems = list;
        this.activity = activity;
    }

    @Override
    public AllClassesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_classes, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(AllClassesAdapter.ViewHolder holder, final int position) {
        holder.name.setText(listItems.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activity.compareToIgnoreCase("add student")==0) {
                    context.startActivity(new Intent(context,AddStudentDataActivity.class).putExtra("className",listItems.get(position)));
                }else  if (activity.compareToIgnoreCase("take attendance")==0) {
                    context.startActivity(new Intent(context,TakeStudentAttendanceActivity.class).putExtra("className",listItems.get(position)));
                }else  if (activity.compareToIgnoreCase("view attendance")==0) {
                    context.startActivity(new Intent(context,ViewAttendanceShowSubClasses.class).putExtra("className",listItems.get(position)));
                }
                else  if (activity.compareToIgnoreCase("delete students")==0) {
                    context.startActivity(new Intent(context,DeleteStudentForClass.class).putExtra("className",listItems.get(position)));
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();

    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.class_name);
            cardView =  (CardView) itemView.findViewById(R.id.main_card_view);
        }
    }

}

