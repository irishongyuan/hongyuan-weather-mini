package cn.edu.pku.hongyuanfu.hongyuan_weather_mini;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.edu.pku.hongyuanfu.app.MyApplication;
import cn.edu.pku.hongyuanfu.bean.City;

public class SelectCity extends Activity implements View.OnClickListener{

    private ImageView mBackBtn;
    private ListView mListView;
    private List<City> mCityList;
    private List<String> mCityName = new ArrayList();
    private List<String> mCityNum = new ArrayList();;
    private TextView mTitlename;
    private String mCurCityNum;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.select_city);

        initViews();

        mTitlename = (TextView) findViewById(R.id.title_name);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("MySelectCity", "start");
                mCurCityNum = mCityNum.get(position);
                mTitlename.setText("当前城市："+mCityName.get(position));
                Toast toast = Toast.makeText(SelectCity.this, "你单击了:" + mCityName.get(position),
                        Toast.LENGTH_SHORT);
            }
        });
    }

    private void initViews() {
        mBackBtn = (ImageView) findViewById(R.id.title_back);
        mBackBtn.setOnClickListener(this);

        /*mEditText = (EditText) findViewById(R.id.search_city);*/

        mListView = (ListView) findViewById(R.id.title_list);
        MyApplication myApplication = (MyApplication) getApplication();
        mCityList = myApplication.getCityList();

        for (City city : mCityList) {
            mCityName.add(city.getCity());
            mCityNum.add(city.getNumber());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                SelectCity.this, android.R.layout.simple_list_item_1, mCityName);
        mListView.setAdapter(adapter);

        Log.d("MySelectCity", "setAdapter");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                Intent i = new Intent();
                Log.d("MySelectCity", mCurCityNum);
                i.putExtra("cityCode",mCurCityNum);
                setResult(RESULT_OK, i);
                finish();
                break;
            default:
                break;
        }
    }
}
