package com.manager.appbanhang.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.manager.appbanhang.Interface.ImageClickListener;
import com.manager.appbanhang.Model.EventBus.TinhTongEvent;
import com.manager.appbanhang.Model.GioHang;
import com.manager.appbanhang.R;
import com.manager.appbanhang.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.MyViewHolder> {
    Context context;
    List<GioHang> giohangList;

    public GioHangAdapter(Context context, List<GioHang> giohangList) {
        this.context = context;
        this.giohangList = giohangList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang,parent,false);

        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override

    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GioHang gioHang = giohangList.get(position);
        holder.item_giohang_tensp.setText(gioHang.getTensp());
        holder.item_giohang_soluong.setText(gioHang.getSoluong() + " ");
        Glide.with(context).load(gioHang.getHinhsp()).into(holder.item_giohang_image);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.item_giohang_gia.setText(String.valueOf(gioHang.getGiasp()));
        long gia = gioHang.getSoluong() * gioHang.getGiasp();
        holder.item_giohang_giasp2.setText(decimalFormat.format(gia));
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Utils.mangmuahang.add(gioHang);
                    EventBus.getDefault().postSticky(new TinhTongEvent());

                }else {
                    for (int i = 0; i<Utils.mangmuahang.size(); i++){
                        if (Utils.mangmuahang.get(i).getIdsp() == gioHang.getIdsp()){
                            Utils.mangmuahang.remove(i);
                            EventBus.getDefault().postSticky(new TinhTongEvent());
                        }
                    }

                }

            }
        });
        holder.setListener(new ImageClickListener() {
            @Override
            public void onImageClick(View view, int position, int giatri) {
                Log.d("TAG", "onImageClick: "+ position + " ..."+giatri);
                if (giatri == 1) {
                    if(giohangList.get(position).getSoluong() > 1){
                        int soluongmoi = giohangList.get(position).getSoluong()-1;
                        giohangList.get(position).setSoluong(soluongmoi);
                        holder.item_giohang_soluong.setText(giohangList.get(position).getSoluong() + " ");
                        long gia = giohangList.get(position).getSoluong() * giohangList.get(position).getGiasp();
                        holder.item_giohang_giasp2.setText(decimalFormat.format(gia));
                        EventBus.getDefault().postSticky(new TinhTongEvent());
                    }else if(giohangList.get(position).getSoluong() == 1){
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                        builder.setTitle("Thông báo");
                        builder.setMessage("Bạn có muốn xóa sản phẩm này khỏi giỏ hàng ");
                        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //neu ng dung bam dong y thi xoa sp
                                Utils.manggiohang.remove(position);
                                notifyDataSetChanged();
                                EventBus.getDefault().postSticky(new TinhTongEvent());

                            }
                        });
                        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //neu ng dung bam huy thi ko xoa sp
                                dialog.dismiss();

                            }
                        });
                        builder.show();

                    }

                }else if (giatri == 2){
                    if(giohangList.get(position).getSoluong() < 11){
                        int soluongmoi = giohangList.get(position).getSoluong()+1;
                        giohangList.get(position).setSoluong(soluongmoi);

                    }
                    holder.item_giohang_soluong.setText(giohangList.get(position).getSoluong() + " ");
                    long gia = giohangList.get(position).getSoluong() * giohangList.get(position).getGiasp();
                    holder.item_giohang_giasp2.setText(decimalFormat.format(gia));
                    EventBus.getDefault().postSticky(new TinhTongEvent());
                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return giohangList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView item_giohang_image, imgtru, imgcong;
        TextView item_giohang_tensp, item_giohang_gia, item_giohang_soluong, item_giohang_giasp2;
        ImageClickListener listener;
        CheckBox checkBox;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_giohang_image = itemView.findViewById(R.id.item_giohang_image);
            item_giohang_tensp = itemView.findViewById(R.id.item_giohang_tensp);
            item_giohang_gia = itemView.findViewById(R.id.item_giohang_gia);
            item_giohang_soluong = itemView.findViewById(R.id.item_giohang_soluong);
            item_giohang_giasp2 = itemView.findViewById(R.id.item_giohang_giasp2);
            imgtru = itemView.findViewById(R.id.item_giohang_tru);
            imgcong = itemView.findViewById(R.id.item_giohang_cong);
            checkBox = itemView.findViewById(R.id.item_giohang_check);

            //event click
            imgcong.setOnClickListener(this);
            imgtru.setOnClickListener(this);
        }

        public void setListener(ImageClickListener listener) {
            this.listener = listener;
        }

        @Override
        public void onClick(View view) {
            if (view == imgtru){
                listener.onImageClick(view, getAdapterPosition(), 1);
                //1 tru

            }else if(view ==imgcong){
                //2 cong
                listener.onImageClick(view, getAdapterPosition(), 2);

            }
        }
    }
}
