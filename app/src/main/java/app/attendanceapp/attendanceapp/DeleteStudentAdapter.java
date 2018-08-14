package app.attendanceapp.attendanceapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DeleteStudentAdapter extends RecyclerView.Adapter<DeleteStudentAdapter.ViewHolder> {

    private Context context;
    private ArrayList<StudentData> listItems;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String className;



    public DeleteStudentAdapter(Context c, ArrayList list, String className){
        context = c;
        this.listItems = list;
        this.className = className;
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("students");
    }

    @Override
    public DeleteStudentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_delete_students, parent, false);

        return new DeleteStudentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DeleteStudentAdapter.ViewHolder holder, final int position) {
        holder.name.setText(listItems.get(position).name);
        holder.rollNO.setText(listItems.get(position).rollno);
        /*holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.child("users").child(FirebaseAuth.getInstance().getUid()).child("class").child(className).child("data").child(listItems.get(position).rollno).removeValue();
            }
        });*/
        holder.delete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    listItems.get(position).toBeDeleted = true;
                }else {
                    listItems.get(position).toBeDeleted = false;
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
        public CheckBox delete;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            rollNO = (TextView) itemView.findViewById(R.id.rollno);
            delete = (CheckBox) itemView.findViewById(R.id.delete);
        }
    }
    public ArrayList<StudentData> getList(){
        return listItems;
    }
}
