package com.example.silver_tracker;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.MutableDateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class budget extends AppCompatActivity {
        private FloatingActionButton fab;
        private DatabaseReference budgetRef;
        private FirebaseAuth mAuth;
        private TextView total_budget;
 private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        mAuth = FirebaseAuth.getInstance();
        budgetRef = FirebaseDatabase.getInstance().getReference().child("budget").child(mAuth.getCurrentUser().getUid());
 total_budget = findViewById(R.id.total_budget);
 recyclerView = findViewById(R.id.recycler_view);
        fab = findViewById(R.id.floating);


        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                additem();
            }


        });
    }

    private void additem() {
        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater =LayoutInflater.from(this);
        View myView = inflater.inflate(R.layout.input_layout, null);
        myDialog.setView(myView);

        final AlertDialog dialog = myDialog.create();
        dialog.setCancelable(false);
        final Spinner itemSpinner = myView.findViewById(R.id.items_spinner);
        final EditText amount= myView.findViewById(R.id.amount_btv);
        final Button cancel = myView.findViewById(R.id.cancel);
        final Button save = myView.findViewById(R.id.save);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String budgetAmount = amount.getText().toString();
                String budgetItem = itemSpinner.getSelectedItem().toString();

                if(TextUtils.isEmpty(budgetAmount)){
                    amount.setError("Amount is required");
                    return;

                }
                if(budgetItem.equals("Select Items")){
                    Toast.makeText(budget.this, "Select Item", Toast.LENGTH_SHORT).show();
                }
                else {
                    String id = budgetRef.push().getKey();
                    DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
                    Calendar cal = Calendar.getInstance();
                    String date =  dateFormat.format(cal.getTime());
                    MutableDateTime epoch = new MutableDateTime();
                    epoch.setDate(0);
                    DateTime now = new DateTime();
                    Months months = Months.monthsBetween(epoch, now);


                    Data data = new Data(budgetItem,date, id, Integer.parseInt(budgetAmount), months.getMonths());
                    budgetRef.child(id).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                             if(task.isSuccessful()){
                                 Toast.makeText(budget.this, "Budget Item Added", Toast.LENGTH_SHORT).show();

                             } else {
                                 Toast.makeText(budget.this, task.getException().toString(),Toast.LENGTH_SHORT).show();
                             }
                        }
                    });

                }
                dialog.dismiss();
            }
        });
 cancel.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         dialog.dismiss();
     }
 });
    dialog.show();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference budgetRef = database.getReference("budgets").child("jwMt685zQAPNtvcTUmYjC4fp9vr2");
    budgetRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    });
    }
    @Override
    protected void onStart() {
        super.onStart();

    }
   public class MyViewHolder extends RecyclerView.ViewHolder{
        View mview;

           public MyViewHolder(@NonNull View itemView){
               super(itemView);
           }


           public void setItemName (String itemName){
               TextView item = mview.findViewById(R.id.itemName);

           }
    public void setItemAmount (String itemAmount){
        TextView amount = mview.findViewById(R.id.amount);

    }
    public void setItemDate (String itemDate){
        TextView item = mview.findViewById(R.id.date);

   }
   }

}