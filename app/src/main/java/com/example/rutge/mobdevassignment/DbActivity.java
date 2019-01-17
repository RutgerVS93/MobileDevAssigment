package com.example.rutge.mobdevassignment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DbActivity extends AppCompatActivity {

    EditText editTextID, editTextTitle, editTextDescription;
    Button btnAddData, btnViewData, btnUpdate, btnDelete;
    DbHelper myDbHelper;

    float x1, x2, y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        //Calls constructor of db helper class
        myDbHelper = new DbHelper(this);

        editTextID = findViewById(R.id.editTextID);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnAddData = findViewById(R.id.addDataButton);
        btnViewData = findViewById(R.id.getDataButton);
        btnUpdate = findViewById(R.id.updateDataButton);
        btnDelete = findViewById(R.id.deleteDataButton);

        addData();
        viewAll();
        updateData();
        deleteData();
    }

    public void addData(){
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDbHelper.insertData(editTextTitle.getText().toString(), editTextDescription.getText().toString());
                if (isInserted == true){
                    Toast.makeText(DbActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(DbActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void viewAll(){
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDbHelper.getAllData();
                if (res.getCount() == 0) {
                    showMessage("Error", "No Data Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID:" + res.getString(0) + "\n");
                    buffer.append("Title: " + res.getString(1) + "\n");
                    buffer.append("Description: " + res.getString(2) + "\n\n");
                }

                showMessage("Data", buffer.toString());
            }
        });
    }

    public void updateData(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated = myDbHelper.updateData(
                        editTextID.getText().toString(),
                        editTextTitle.getText().toString(),
                        editTextDescription.getText().toString());
                if(isUpdated == true){
                    Toast.makeText(DbActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(DbActivity.this, "Data Not Updated", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void deleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDbHelper.deleteData(editTextID.getText().toString());

                if(deletedRows > 0){
                    Toast.makeText(DbActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(DbActivity.this, "Data Not Deleted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public boolean onTouchEvent(MotionEvent touchEvent){
        switch (touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;

            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if (x1 > x2){
                    Intent i = new Intent(DbActivity.this, ExtraActivity.class);
                    startActivity(i);
                }
                else if(x2 > x1){
                    Intent i = new Intent(DbActivity.this, MainActivity.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }
}
