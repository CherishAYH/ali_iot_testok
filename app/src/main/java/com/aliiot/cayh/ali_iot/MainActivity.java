package com.aliiot.cayh.ali_iot;
//该版本GET访问测试正常，其他细节暂未完成
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;//================自己添加
import android.widget.*;//================自己添加


import com.aliyun.iot.sign.*;



public class MainActivity extends AppCompatActivity {
    private Button Button01 = null;//================自己添加
    private Button button1 = null;
    private EditText editText1 =null;//================自己添加
    private EditText editText3 =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定Button资源//================自己添加
        Button01 = (Button) findViewById(R.id.Button01);//查找控件ID并绑定
        //设置Button监听//================自己添加
        Button01.setOnClickListener(new MyButtonListener());//================自己添加
        button1 = (Button) findViewById(R.id.button1);//查找控件ID并绑定Button资源
        button1.setOnClickListener(new button1Listener());//设置Button监听
        //绑定EditText资源//================自己添加
        editText1=(EditText) findViewById(R.id .editText1); //查找控件ID
        editText3=(EditText) findViewById(R.id .editText3); //查找控件ID
    }
    // 实现OnClickListener接口//================自己添加
    private class MyButtonListener implements View.OnClickListener {//测试按钮被点击
        @Override
        public void onClick(View view) {
            Toast.makeText(MainActivity.this,"测试Signature",Toast.LENGTH_LONG).show();//弹出提示信息
            String signature= 设备管理.设置设备属性("SetDeviceProperty","test","a1******","{\"Status\":1}");
                editText3.setText(signature,null);//调用获取签名，并将结果放到编辑框
        }//================自己添加
    }//================自己添加

    // 实现button1Listener监听接口/
    private class button1Listener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Toast.makeText(MainActivity.this,"计算Signature",Toast.LENGTH_LONG).show();//弹出提示信息
            try {
                editText1.setText(签名工具入口.GetSignature(),null);//调用获取签名，并将结果放到编辑框
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
