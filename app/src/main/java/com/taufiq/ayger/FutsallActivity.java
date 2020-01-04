package com.taufiq.ayger;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.taufiq.ayger.Adapter.LapanganAdapter;
import com.taufiq.ayger.Model.ResponseLapangan;
import com.taufiq.ayger.Model.SemuaLapangan;
import com.taufiq.ayger.apihelper.BaseApiService;
import com.taufiq.ayger.apihelper.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FutsallActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressDialog loading;
    List<SemuaLapangan> semuaLapangans = new ArrayList<>();
    Context mContext;
    LapanganAdapter dosenAdapter;
    BaseApiService mApiService;
    String getKategori,getNama;
    @BindView(R.id.tvJudul)
    TextView tvJudul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futsall);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        getKategori = getIntent().getStringExtra(MainActivity.KeyFutsal);
        getNama = getIntent().getStringExtra(MainActivity.KeyNama);
        tvJudul.setText(getNama);

        mContext = this;
        mApiService = UtilsApi.getAPIService();
        dosenAdapter = new LapanganAdapter(this, semuaLapangans);
        recyclerView = (RecyclerView) findViewById(R.id.rvfutsal);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        loading = new ProgressDialog(FutsallActivity.this);


        getResultListLapangan();
    }

    private void getResultListLapangan() {
        loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);
        mApiService.postMesssage(getKategori).enqueue(new Callback<ResponseLapangan>() {
            @Override
            public void onResponse(Call<ResponseLapangan> call, Response<ResponseLapangan> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();

                    final List<SemuaLapangan> semuaDosenItems = response.body().getSemualapangan();

                    recyclerView.setAdapter(new LapanganAdapter(mContext, semuaDosenItems));
                    dosenAdapter.notifyDataSetChanged();
                } else {
                    loading.dismiss();
                    Toast.makeText(mContext, "Gagal mengambil data lpangan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLapangan> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
