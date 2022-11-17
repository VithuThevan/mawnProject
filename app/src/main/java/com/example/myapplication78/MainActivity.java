package com.example.myapplication78;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, description, price;
    Button insert, update, delete, view;
    DBHelper DB;
    boolean isAllFieldsChecked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        price = findViewById(R.id.price);
        insert = findViewById(R.id.btninsert);
        update = findViewById(R.id.btnupdate);
        delete = findViewById(R.id.btndelete);
        view = findViewById(R.id.btnview);

        DB = new DBHelper(this);

        price.addTextChangedListener(onTextChangedListener());

        //to redirect page to list view upon clicking view and update button
        view.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, userlist.class)));
        update.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, userlist.class)));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        delete.setOnClickListener(view -> {
            builder.setTitle("Warning").setMessage("Do you want to delete this Item? This action cannot be undone").setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
//                            int result = DB.deleteStudent()
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).show();
        });

        insert.setOnClickListener(view -> {
            String nameTXT = name.getText().toString();
            String descriptionTXT = description.getText().toString();
            String priceTXT = price.getText().toString();

            isAllFieldsChecked = CheckAllFields();

            Boolean checkinsertdata = DB.insertuserdata(nameTXT, descriptionTXT, priceTXT);
            if (checkinsertdata){
                Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private TextWatcher onTextChangedListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                String str = price.getText().toString();
                if (str.isEmpty()) return;

                String str2 = PerfectDecimal(str,2);

                if (!str2.equals(str)){
                    price.setText(str2);
                    price.setSelection(str2.length());
                }

            }
        };
    }

    public String PerfectDecimal(String str, int maxdecimal){
        if(str.charAt(0)=='.') str = "0"+str;
        int max = str.length();

        StringBuilder rFinal = new StringBuilder();
        boolean after = false;
        int i=0, decimal=0; char t;
        while(i<max){
            t=str.charAt(i);
            if(t!='.' && after==false){
            }else if(t=='.'){
                after=true;
            }else{
                decimal++;
                if(decimal>maxdecimal){
                    return rFinal.toString();
                }
            }
            rFinal.append(t);
            i++;
        }return rFinal.toString();
    }

    private boolean CheckAllFields() {
        if (name.length() == 0) {
            name.setError("This field is required");
            return false;
        }

        if (description.length() == 0) {
            description.setError("This field is required");
            return false;
        }

        if (price.length() == 0) {
            price.setError("Email is required");
            return false;
        }

        // after all validation return true.
        return true;
    }
}