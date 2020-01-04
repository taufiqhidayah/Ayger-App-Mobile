package com.taufiq.ayger;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.taufiq.ayger.Adapter.LapanganAdapter;
import com.taufiq.ayger.Adapter.RecentAdapter;
import com.taufiq.ayger.IntroSlider.PrefManager;
import com.taufiq.ayger.Model.ResponseLapangan;
import com.taufiq.ayger.Model.SemuaLapangan;
import com.taufiq.ayger.apihelper.BaseApiService;
import com.taufiq.ayger.apihelper.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Intent i;
    @BindView(R.id.btFutsal)
    ImageView btFutsal;
    @BindView(R.id.btbadminton)
    ImageView btbadminton;
    @BindView(R.id.btSepak)
    ImageView btSepak;
    @BindView(R.id.btbasket)
    ImageView btbasket;
    @BindView(R.id.bttenis)
    ImageView bttenis;
    @BindView(R.id.btgym)
    ImageView btgym;

    ProgressDialog loading;
    List<SemuaLapangan> semuaLapangans = new ArrayList<>();
    Context mContext;
    LapanganAdapter dosenAdapter;
    BaseApiService mApiService;
    
    public static final String KeyFutsal = "keyfutsal";
    public static final String KeyNama = "keyfutsal1";
    @BindView(R.id.tvProfile)
    TextView tvProfile;
    @BindView(R.id.ln2)
    LinearLayout ln2;
    @BindView(R.id.ln1)
    LinearLayout ln1;
    @BindView(R.id.lsRecent)
    RecyclerView lsview;
    PrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        prefManager= new PrefManager(MainActivity.this);
            tvProfile.setText(prefManager.getNama());
//        getActionBar().hide();
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        dosenAdapter = new LapanganAdapter(this, semuaLapangans);
  
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        lsview.setLayoutManager(layoutManager);
        lsview.setHasFixedSize(true);

        loading = new ProgressDialog(MainActivity.this);


        getResultListLapangan();

    }

    private void getResultListLapangan() {
        loading.show();
        mApiService.getRecent(prefManager.getId()).enqueue(new Callback<ResponseLapangan>() {
            @Override
            public void onResponse(Call<ResponseLapangan> call, Response<ResponseLapangan> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();

                    final List<SemuaLapangan> semuaDosenItems = response.body().getSemualapangan();

                    lsview.setAdapter(new LapanganAdapter(mContext, semuaDosenItems));
                    dosenAdapter.notifyDataSetChanged();
                } else {
                    loading.dismiss();
                    Toast.makeText(mContext, "Gagal mengambil data lpangan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLapangan> call, Throwable t) {

            }
        });
    }

    @OnClick({R.id.btFutsal, R.id.btbadminton, R.id.btSepak, R.id.btbasket, R.id.bttenis, R.id.btgym})
    public void onViewClicked(View view) {
        Intent i = new Intent(MainActivity.this, FutsallActivity.class);

        switch (view.getId()) {

            case R.id.btFutsal:

                i.putExtra(KeyNama, "Futsal");
                i.putExtra(KeyFutsal, "1");

                startActivity(i);
                break;
            case R.id.btbadminton:

                i.putExtra(KeyNama, "Badminton");
                i.putExtra(KeyFutsal, "2");
                startActivity(i);
                break;
            case R.id.btSepak:
                i.putExtra(KeyFutsal, "3");
                i.putExtra(KeyNama, "Sepak Bola");
                startActivity(i);
                break;
            case R.id.btbasket:

                i.putExtra(KeyFutsal, "4");
                i.putExtra(KeyNama, "Basket");
                startActivity(i);
                break;
            case R.id.bttenis:

                i.putExtra(KeyFutsal, "5");
                i.putExtra(KeyNama, "Tenis");
                startActivity(i);
                break;
            case R.id.btgym:
                i.putExtra(KeyFutsal, "6");
                i.putExtra(KeyNama, "Gym");
                startActivity(i);
                break;
        }
    }
}
