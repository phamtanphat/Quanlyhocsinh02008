package com.ptp.phamtanphat.quanlyhocsinh02008;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    HocsinhAdapter hocsinhAdapter;
    ArrayList<Hocsinh> manghocsinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listview);
        manghocsinh = new ArrayList<>();
        hocsinhAdapter = new HocsinhAdapter(MainActivity.this,manghocsinh);
        lv.setAdapter(hocsinhAdapter);
        getdata(Server.GetData);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_them,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuthem :
                Intent intent = new Intent(MainActivity.this,ThemHocSinhActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void getdata(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0 ; i<response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        int id = jsonObject.optInt("Id");
                        String hoten = jsonObject.optString("HoTen");
                        int namsinh = jsonObject.optInt("NamSinh");
                        String diachi = jsonObject.optString("DiaChi");

                        manghocsinh.add(new Hocsinh(id,hoten,namsinh,diachi));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                hocsinhAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }


    @Override
    protected void onResume() {
        if (hocsinhAdapter!= null){
            manghocsinh.clear();
            getdata(Server.GetData);
        }
        super.onResume();
    }
}
