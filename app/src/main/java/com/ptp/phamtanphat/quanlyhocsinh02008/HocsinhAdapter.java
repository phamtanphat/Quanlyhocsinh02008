package com.ptp.phamtanphat.quanlyhocsinh02008;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
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

/**
 * Created by KhoaPhamPC on 4/10/2017.
 */

public class HocsinhAdapter extends BaseAdapter {
    Context context;
    ArrayList<Hocsinh> hocsinhArrayList;

    public HocsinhAdapter(Context context, ArrayList<Hocsinh> hocsinhArrayList) {
        this.context = context;
        this.hocsinhArrayList = hocsinhArrayList;
    }

    @Override
    public int getCount() {
        return hocsinhArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return hocsinhArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    class ViewHolder{
        TextView txthoten,txtnamsinh,txtdiachi;
        ImageView imgdelete,imgedit;
    }
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.dong_hocsinh,null);

            viewHolder = new ViewHolder();
            viewHolder.txthoten = view.findViewById(R.id.textviewhoten);
            viewHolder.txtdiachi = view.findViewById(R.id.textviewdiachi);
            viewHolder.txtnamsinh = view.findViewById(R.id.textviewnamsinh);
            viewHolder.imgdelete = view.findViewById(R.id.imagebuttondelete);
            viewHolder.imgedit = view.findViewById(R.id.imagebuttonedit);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final Hocsinh hocsinh = (Hocsinh) getItem(i);
        viewHolder.txtnamsinh.setText("Nam Sinh : " + hocsinh.getNamsinh()+"");
        viewHolder.txthoten.setText(hocsinh.getHoten());
        viewHolder.txtdiachi.setText(hocsinh.getDiachi());
        viewHolder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,SuaActivity.class);
                intent.putExtra("hocsinh",hocsinh);
                context.startActivity(intent);
            }
        });
        viewHolder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.DeleteData, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success")){
                            Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                            hocsinhArrayList.remove(i);
                            notifyDataSetChanged();
                        }else {
                            Toast.makeText(context, "That bai", Toast.LENGTH_SHORT).show();
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
                        hashMap.put("id",hocsinh.getId() + "");
                        return hashMap;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
        return view;
    }
}
