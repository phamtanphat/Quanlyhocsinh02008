package com.ptp.phamtanphat.quanlyhocsinh02008;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThemHocSinhActivity extends AppCompatActivity {

    EditText edthoten,edtnamsinh,edtdiachi;
    Button btnhuy,btnthem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_hoc_sinh);
        anhxa();
        event();
    }

    private void event() {
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String hoten = edthoten.getText().toString();
                final String namsinh = edtnamsinh.getText().toString().trim();
                final String diachi = edtdiachi.getText().toString();

                if (TextUtils.isEmpty(hoten) || namsinh.matches("") || diachi.matches("")){
                    Toast.makeText(ThemHocSinhActivity.this, "Ban nhap du thong tin", Toast.LENGTH_SHORT).show();
                }else {
                    RequestQueue requestQueue = Volley.newRequestQueue(ThemHocSinhActivity.this);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.InsertData, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                           if (response.equals("1")){
                               finish();
                           }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<String, String>();
                            hashMap.put("hoten",hoten);
                            hashMap.put("namsinh",namsinh);
                            hashMap.put("diachi",diachi);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
            }
        });
    }

    private void anhxa() {
        edthoten = (EditText) findViewById(R.id.edittexthoten);
        edtnamsinh = (EditText) findViewById(R.id.edittextnamsinh);
        edtdiachi = (EditText) findViewById(R.id.edittextdiachi);
        btnhuy = (Button) findViewById(R.id.buttonhuy);
        btnthem = (Button) findViewById(R.id.buttonthem);
    }
}
