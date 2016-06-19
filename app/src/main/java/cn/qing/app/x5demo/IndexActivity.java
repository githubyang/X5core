package cn.qing.app.x5demo;

/**
 * Created by dell on 2016/6/18.
 */
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class IndexActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        setTitle("X5Core调试工具");
        GridView gridview = (GridView) findViewById(R.id.gridview);
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
        String[] arr = {"打开浏览器","设置"};
        for(int i=0;i<2;i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", R.drawable.ic_launcher);
            map.put("ItemText", ""+String.valueOf(arr[i]));
            lstImageItem.add(map);
        }
        SimpleAdapter saImageItems = new SimpleAdapter(this,
                lstImageItem,
                R.layout.ngiht_item,

                new String[] {"ItemImage","ItemText"},

                new int[] {R.id.ic_launcher_png,R.id.ItemText});
        gridview.setAdapter(saImageItems);
        gridview.setOnItemClickListener(new ItemClickListener());
    }

    class  ItemClickListener implements OnItemClickListener
    {
        public void onItemClick(AdapterView<?> arg0,//The AdapterView where the click happened
                                View arg1,//The view within the AdapterView that was clicked
                                int arg2,//The position of the view in the adapter
                                long arg3//The row id of the item that was clicked
        ) {
            Log.i("s:",""+arg2);
            if (arg2==0){
                Intent intent = new Intent(IndexActivity.this, MainActivity.class);
                startActivity(intent);
            }else if(arg2==1){
                Intent intent = new Intent(IndexActivity.this, SetingActivity.class);
                startActivity(intent);
            }
//            HashMap<String, Object> item=(HashMap<String, Object>) arg0.getItemAtPosition(arg2);
//            setTitle((String)item.get("ItemText"));

        }

    }

}
