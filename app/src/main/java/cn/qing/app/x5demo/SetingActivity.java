package cn.qing.app.x5demo;

import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Created by dell on 2016/6/18.
 */
public class SetingActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seting);
        Button btn1 = (Button) findViewById(R.id.apply);
        Button btn2 = (Button) findViewById(R.id.close);

        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                EditText et = (EditText)findViewById(R.id.edit_query);//获取edittext组件
                String str=et.getText().toString();
//                try {
//                    writeFileSdcardFile("ss.txt",str);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                String aaa = Environment.getExternalStorageDirectory() + File.separator + "aaa.txt";
                File file = new File(aaa);
                try {
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter pw = new FileWriter(file, false);
                    pw.write(str);
                    pw.flush();
                    pw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            }
        });
    }
    //写数据到SD中的文件
    public void writeFileSdcardFile(String fileName,String write_str) throws IOException{
        try{

            FileOutputStream fout = new FileOutputStream(fileName);
            byte [] bytes = write_str.getBytes();

            fout.write(bytes);
            fout.close();
        }

        catch(Exception e){
            e.printStackTrace();
        }
    }
}
