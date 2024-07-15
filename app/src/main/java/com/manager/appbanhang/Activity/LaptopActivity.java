package com.manager.appbanhang.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.manager.appbanhang.Adapter.DienThoaiAdapter;
import com.manager.appbanhang.Adapter.LaptopAdapter;
import com.manager.appbanhang.Model.SanPhamMoi;
import com.manager.appbanhang.R;
import com.manager.appbanhang.retrofit.ApiBanHang;
import com.manager.appbanhang.retrofit.RetrofitClient;
import com.manager.appbanhang.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LaptopActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    ApiBanHang apiBanHang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    int page =1;
    int loai;
    LaptopAdapter adapterLaptop;
    List<SanPhamMoi> sanmPhamMoiList;
    LinearLayoutManager linearLayoutManager;
    Handler handler = new Handler();
    boolean isLoading = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop);
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        loai = getIntent().getIntExtra("loai",1);
        toolbar = findViewById(R.id.toobar);
        recyclerView  = findViewById(R.id.recycleview_laptop);
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        sanmPhamMoiList = new ArrayList<>();
        ActionToolBar();
        getData(page);
        addEventLoad();
    }

    private void addEventLoad() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(isLoading == false){
                    if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == sanmPhamMoiList.size()-1){
                        isLoading = true;
                        loadMore();
                    }
                }
            }
        });
    }

    private void loadMore() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                //add null
                sanmPhamMoiList.add(null);
                adapterLaptop.notifyItemInserted(sanmPhamMoiList.size()-1);
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //remove null
                sanmPhamMoiList.remove(sanmPhamMoiList.size()-1);
                adapterLaptop.notifyItemRemoved(sanmPhamMoiList.size());
                page = page + 1;
                getData(page);
                adapterLaptop.notifyDataSetChanged();
                isLoading = false;

            }
        },2000);
    }

    private void getData( int page) {
        compositeDisposable.add(apiBanHang.getSanPham(page,loai)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanPamMoiModel -> {
                            if(sanPamMoiModel.isSuccess()){
                                if (adapterLaptop == null) {
                                    sanmPhamMoiList = sanPamMoiModel.getResult();
                                    adapterLaptop = new LaptopAdapter(getApplicationContext(), sanmPhamMoiList);
                                    recyclerView.setAdapter(adapterLaptop);
                                }else {
                                    int vitri = sanmPhamMoiList.size()-1;
                                    int soluongadd = sanPamMoiModel.getResult().size();
                                    for (int i =0 ; i<soluongadd; i++){
                                        sanmPhamMoiList.add(sanPamMoiModel.getResult().get(i));
                                    }
                                    adapterLaptop.notifyItemRangeInserted(vitri, soluongadd);
                                }

                            }else {
                                Toast.makeText(getApplicationContext(), "Het du lieu", Toast.LENGTH_LONG).show();
                                isLoading = true;
                            }

                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "Khong ket noi server", Toast.LENGTH_LONG).show();
                        }
                ));
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}