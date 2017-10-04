package com.ptp.phamtanphat.quanlyhocsinh02008;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

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
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.dong_hocsinh,null);
            viewHolder = new ViewHolder();
            viewHolder.txthoten = view.findViewById(R.id.textviewhoten);
            viewHolder.txtdiachi = view.findViewById(R.id.textviewdiachi);
            viewHolder.txtnamsinh = view.findViewById(R.id.textviewnamsinh);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Hocsinh hocsinh = (Hocsinh) getItem(i);
        viewHolder.txtnamsinh.setText("Nam Sinh : " + hocsinh.getNamsinh()+"");
        viewHolder.txthoten.setText(hocsinh.getHoten());
        viewHolder.txtdiachi.setText(hocsinh.getDiachi());
        return view;
    }
}
