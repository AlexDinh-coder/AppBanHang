package com.manager.appbanhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.manager.appbanhang.Model.LoaiSp;
import com.manager.appbanhang.R;

import java.util.List;

public class LoaiSpAdapter extends BaseAdapter {
    List<LoaiSp> array;
    Context context;

    public LoaiSpAdapter(List<LoaiSp> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ViewHolder{
        TextView textTenSp;
        ImageView imageHinhAnh;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_sanpham, null);
            viewHolder.textTenSp = view.findViewById(R.id.item_tensp);
            viewHolder.imageHinhAnh = view.findViewById(R.id.item_image);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textTenSp.setText(array.get(i).getTensanpham());
        Glide.with(context).load(array.get(i).getHinhanh()).into(viewHolder.imageHinhAnh);

        return view;
    }
}
