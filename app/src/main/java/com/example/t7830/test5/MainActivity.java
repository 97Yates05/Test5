package com.example.t7830.test5;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private String str;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);
    }
    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onClick(View view){
        switch(view.getId()){
            case R.id.button_date:
                final DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Toast.makeText(MainActivity.this,year+"年"+(month+1)+"月"
                                +dayOfMonth+"日",Toast.LENGTH_SHORT).show();
                    }
                },2018,2,29);
                datePickerDialog.getDatePicker().setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        datePickerDialog.setTitle(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                    }
                });
                datePickerDialog.setTitle("2018-03-29");
                datePickerDialog.show();
                break;
            case R.id.button_single_choice:
                final String[] items = {"男","女","性别未知","你猜"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this,"你选择的性别为: "+items[which],Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
                break;
            case R.id.button_multi_choice:
                str = "个人爱好:";
                final String[] items_multi = {"编程","LOL","旅游","篮球"};
                final boolean[] items_boolean = {false,false,false,false};
                final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("选择个人爱好")
                        .setMultiChoiceItems(items_multi, items_boolean, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i=0;i<items_boolean.length;i++){
                            if(items_boolean[i]){
                                str=str.concat(items_multi[i]+'\n');
                            }
                        }
                        textView.setText(str);
                    }
                });
                builder1.show();
                break;
        }
    }
}
