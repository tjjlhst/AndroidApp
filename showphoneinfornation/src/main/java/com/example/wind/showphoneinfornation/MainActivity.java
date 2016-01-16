package com.example.wind.showphoneinfornation;

import android.content.ContentResolver;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ListView lv ;
    public TelephonyManager tm ;
    public ContentResolver cr ;
    public Button checkbtn ;
    public List<String> name = new ArrayList<String>();
    public List<String> list = new ArrayList<String>();
    public WifiManager isWifi ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView)this.findViewById(R.id.lv);
        tm = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        cr = MainActivity.this.getContentResolver();
        checkbtn = (Button)findViewById(R.id.checkBtn);
        isWifi = (WifiManager)this.getSystemService(WIFI_SERVICE);
        String str = null;
        name.add("手机号码：");
        name.add("电信网络国别：");
        name.add("电信公司代码：");
        name.add("电信公司名称：");
        name.add("SIM码：");
        name.add("手机通信类型：");
        name.add("手机网络类型 ：");
        name.add("手机是否漫游：");
        name.add("蓝牙状态：");
        name.add("WIFI状态：");
        if(tm.getLine1Number()!=null){
            list.add(tm.getLine1Number());
        }else{
            list.add("无法查到你的手机号码");
        }
        if(!tm.getNetworkCountryIso().equals("")){
            list.add(tm.getNetworkCountryIso());
        }else{
            list.add("无法查到你的电信号码");
        }
        if(!tm.getNetworkOperator().equals("")){
            list.add(tm.getNetworkOperatorName());
        }else{
            list.add("无法查到电信公司网络");
        }
        if(tm.getNetworkOperatorName()!=null){
            list.add(tm.getLine1Number());
        }else{
            list.add("无法获取电信公司名称");
        }
        if(tm.getSimSerialNumber()!=null){
            list.add(tm.getSimSerialNumber());
        }else{
            list.add("无法获取手机sim码");
        }
        if(tm.getPhoneType()==TelephonyManager.PHONE_TYPE_GSM)//手机行动通信类型
        {
            list.add("GSM");
        }
        else if(tm.getPhoneType()==TelephonyManager.PHONE_TYPE_CDMA)  //需用真机演示
        {
        	list.add("CDMA");
        }
        else
        {
            list.add("无法获取手机通信类型");
        }
        if(tm.getNetworkType()==TelephonyManager.NETWORK_TYPE_EDGE)//获取手机网络类型
        {
            list.add("EDGE");
        }else if(tm.getNetworkType()==TelephonyManager.NETWORK_TYPE_GPRS)
        {
            list.add("GPRS");
        }else if(tm.getNetworkType()==TelephonyManager.NETWORK_TYPE_UMTS)
        {
            list.add("UMTS");
        }
        else if(tm.getNetworkType()==TelephonyManager.NETWORK_TYPE_HSDPA) //需用真机演示
        {
        	list.add("HSDPA");
        }
        else
        {
            list.add("无法获取手机网络类型");
        }

        if(tm.isNetworkRoaming())//手机是否漫游
        {
            list.add("手机漫游中");
        }else
        {
            list.add("手机无漫游");
        }
        str = android.provider.Settings.System.getString(cr, Settings.System.BLUETOOTH_ON);
        if(str.equals("1")){
            list.add("蓝牙已打开");
        }else{
            list.add("蓝牙未打开");
        }
       /* str = android.provider.Settings.System.getString(cr, Settings.System.WIFI_ON);*/
        if(isWifi.isWifiEnabled()){
            list.add("wifi已经打开");
        }else{
            list.add("wifi未打开");
        }
        checkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseAdapter bp = new BaseAdapter() {
                    @Override
                    public int getCount() {
                        Log.i("C1111", String.valueOf(list.size()));
                        return list.size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return null;
                    }

                    @Override
                    public long getItemId(int position) {
                        return 0;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        LinearLayout ll = new LinearLayout(MainActivity.this);
                        ll.setPadding(5,5,5,5);
                        ll.setOrientation(LinearLayout.HORIZONTAL);

                        TextView tvName = new TextView(MainActivity.this);
                        tvName.setText(name.get(position));
                        tvName.setPadding(5, 5, 5, 5);
                        tvName.setGravity(Gravity.LEFT);
                        ll.addView(tvName);

                        TextView tvList = new TextView(MainActivity.this);
                        tvList.setText(list.get(position));
                        tvList.setPadding(5, 5, 5, 5);
                        tvList.setGravity(Gravity.LEFT);
                        ll.addView(tvList);

                        return ll;
                    }
                };
               /* BaseAdapter ba=new BaseAdapter()//创建适配器
                {

                    public int getCount() {
                        return list.size();
                    }

                    public Object getItem(int position) {
                        return null;
                    }

                    public long getItemId(int position) {
                        return 0;
                    }

                    public View getView(int arg0, View arg1, ViewGroup arg2) {
                        LinearLayout ll=new LinearLayout(MainActivity.this);
                        ll.setOrientation(LinearLayout.HORIZONTAL);
                        ll.setPadding(5, 5, 5, 5);

                        TextView tv=new TextView(MainActivity.this);//初始化TextView
                        tv.setTextColor(Color.BLACK);//设置字体颜色
                        tv.setPadding(5,5,5,5);
                        tv.setText(name.get(arg0));//添加任务名字
                        tv.setGravity(Gravity.LEFT);//左对齐
                        tv.setTextSize(18);//字体大小  			
                        ll.addView(tv);//LinearLayout添加TextView

                        TextView tvv=new TextView(MainActivity.this);//初始化TextView
                        tvv.setTextColor(Color.BLACK);//设置字体颜色
                        tvv.setPadding(5,5,5,5);
                        tvv.setText(list.get(arg0));//添加任务名字
                        tvv.setGravity(Gravity.LEFT);//左对齐
                        tvv.setTextSize(18);//字体大小  			
                        ll.addView(tvv);//LinearLayout添加TextView
                        return ll;
                    }
                };*/
                lv.setAdapter(bp);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(MainActivity.this,name.get(position)+" "+list.get(position),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
