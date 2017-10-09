package com.ptp.phamtanphat.quanlyhocsinh02008;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import java.util.HashMap;
import java.util.Map;

public class SuaActivity extends AppCompatActivity {

    EditText edtsuahoten,edtsuanamsinh,edtsuadiachi;
    Button btncancel,btnchinhsua;
    Hocsinh hocsinh;
    String hoten = "";
    String namsinh = "";
    String diachi = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua);
        anhxa();
        Intent intent = getIntent();
        if (intent != null){
            hocsinh = intent.getParcelableExtra("hocsinh");
            edtsuahoten.setText(hocsinh.getHoten());
            edtsuanamsinh.setText(hocsinh.getNamsinh()+"");
            edtsuadiachi.setText(hocsinh.getDiachi());
            btnchinhsua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hoten = edtsuahoten.getText().toString();
                    diachi = edtsuadiachi.getText().toString();
                    namsinh = edtsuanamsinh.getText().toString();
                    if (!hoten.matches("") && !diachi.matches("") && !namsinh.matches("")){
                        RequestQueue requestQueue = Volley.newRequestQueue(SuaActivity.this);
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.UpdateData, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("success")){
                                    Toast.makeText(SuaActivity.this, "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                                    finish();
                                }else {
                                    Toast.makeText(SuaActivity.this, "Cap nhat that bai", Toast.LENGTH_SHORT).show();
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
                                hashMap.put("id",hocsinh.getId()+"");
                                hashMap.put("hoten",hoten);
                                hashMap.put("diachi",diachi);
                                hashMap.put("namsinh",namsinh);
                                return hashMap;
                            }
                        };
                        requestQueue.add(stringRequest);
                    }

                }
            });
        }
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void anhxa() {
        edtsuahoten = (EditText) findViewById(R.id.edittextsuahoten);
        edtsuanamsinh = (EditText) findViewById(R.id.edittextsuanamsinh);
        edtsuadiachi = (EditText) findViewById(R.id.edittextsuadiachi);
        btncancel = (Button) findViewById(R.id.buttonhuychinhsua);
        btnchinhsua = (Button) findViewById(R.id.buttonchinhsua);
    }
}
