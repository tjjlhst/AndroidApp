package com.example.wind.opendatabasetocheckstudentimformation;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase sld;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) this.findViewById(R.id.openDatabase);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createOrOpenDatabase();
            }
        });

        btn = (Button) this.findViewById(R.id.closeDatabase);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDatabase();
            }
        });

        btn = (Button) this.findViewById(R.id.add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

        btn = (Button) this.findViewById(R.id.delete);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

        btn = (Button) this.findViewById(R.id.update);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

        btn = (Button) this.findViewById(R.id.select);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select();
            }
        });

    }

    public void appendMessage(String msg){
        EditText et = (EditText) this.findViewById(R.id.et);
        et.append(msg+"\n");
    }

    public void createOrOpenDatabase(){
        try {
            sld = SQLiteDatabase.openDatabase("/data/data/com.example.wind.opendatabasetocheckstudentimformation/mydb",
                    null,
                    SQLiteDatabase.OPEN_READWRITE | SQLiteDatabase.CREATE_IF_NECESSARY);
            Toast.makeText(this,"数据库已经成功打开！",Toast.LENGTH_SHORT).show();
            String sql = "create table if not exists student(sno char(5),stuname varchar(20),sage integer,sclass char(5))";
            sld.execSQL(sql);
            Toast.makeText(this,"数据库已经成功创建！",Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(this,"数据库错误："+e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    public void closeDatabase(){
        try{
            sld.close();
            Toast.makeText(this,"数据库已经成功关闭！",Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(
                    this,"数据库没有成功关闭："+e.toString(),Toast.LENGTH_SHORT
            ).show();
        }
    }

    public void add(){
        EditText etName = (EditText) this.findViewById(R.id.etName);
        EditText etAge = (EditText) this.findViewById(R.id.etAge);
        if(etName.getText().toString().equals("")&&etAge.getText().toString().equals("")){
            Toast.makeText(this,"请把姓名或年龄填写完整",Toast.LENGTH_SHORT).show();
        }else{
            try {
                String insert = "insert into student values('10001','"+etName.getText().toString()+"',"+Integer.parseInt(etAge.getText().toString())+",'102')";
                sld.execSQL(insert);
                Toast.makeText(this,"成功插入数据库！",Toast.LENGTH_SHORT).show();
                etName.setText("");
                etAge.setText("");
            }catch(Exception e){
                Toast.makeText(this,"数据没有插入成功："+e.toString(),Toast.LENGTH_SHORT).show();
                Log.e("c1117",e.toString());
            }
        }

    }

    public void delete(){
        try {
            String sql = "delete from student;";
            sld.execSQL(sql);
            Toast.makeText(this,"成功删除所有数据！",Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(this,"数据库错误："+e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    public void update(){
        try {
            String sql = "update student set stuname='李勇'";
            sld.execSQL(sql);
            Toast.makeText(this,"成功更改数据库！",Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(this,"数据没有更改成功："+e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    public void select(){
        try{
            String sql = "select * from student where sage>?";
            Cursor cur = sld.rawQuery(sql,new String[]{"20"});
            appendMessage("学号\t\t姓名\t\t年龄\t\t班级");
            while(cur.moveToNext()){
                String sno = cur.getString(0);
                String sname = cur.getString(1);
                int sage = cur.getInt(2);
                String sclass = cur.getString(3);
                appendMessage(sno+"\t"+sname+"\t\t"+sage+"\t"+sclass);
            }
            cur.close();
        }catch (Exception e){
            Toast.makeText(this,"数据库错误："+e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
