package com.taufiq.ayger;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.taufiq.ayger.Adapter.LapanganAdapter;
import com.taufiq.ayger.IntroSlider.PrefManager;
import com.taufiq.ayger.Model.SemuaLapangan;
import com.taufiq.ayger.apihelper.BaseApiService;
import com.taufiq.ayger.apihelper.UtilsApi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.imgdet)
    ImageView imgdet;
    @BindView(R.id.tvNama)
    TextView tvNama;
    @BindView(R.id.tvlocation)
    TextView tvlocation;
    @BindView(R.id.tvdesc)
    TextView tvdesc;
    @BindView(R.id.tvDuration)
    TextView tvDuration;
    @BindView(R.id.btnbook)
    Button btnbook;
    @BindView(R.id.spnier)
    Spinner spnier;
    Integer totaljam;
    String harga;
    PrefManager prefManager;
    String idvendor,idalat;
    BaseApiService mApiService;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        mApiService = UtilsApi.getAPIService();
        progressDialog = new ProgressDialog(DetailActivity.this);
        prefManager = new PrefManager(DetailActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();
        String nama = i.getStringExtra(LapanganAdapter.Key_Nama);
        String alamat = i.getStringExtra(LapanganAdapter.Key_Location);
        String desc = i.getStringExtra(LapanganAdapter.Key_Desc);
        harga = i.getStringExtra(LapanganAdapter.Key_Harga);
        idvendor = i.getStringExtra(LapanganAdapter.KEY_ID);
        idalat=i.getStringExtra(LapanganAdapter.KeyIDAlat);

        tvdesc.setText(desc);
        tvlocation.setText(alamat);
        tvNama.setText(nama);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.hour, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnier.setAdapter(adapter);
        spnier.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int hold = spnier.getSelectedItemPosition() + 1;

                switch (hold) {
                    case 1:
                        totaljam = 1;
                        break;
                    case 2:
                        totaljam = 2;
                        break;
                    case 3:
                        totaljam = 3;
                        break;
                    case 4:
                        totaljam = 4;
                        break;
                    case 5:
                        totaljam = 5;
                        break;
                }
                Toast.makeText(DetailActivity.this, harga, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @OnClick(R.id.btnbook)
    public void onViewClicked() {
        Integer hargaq = Integer.parseInt(harga);
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
        builder.setTitle("Harga :" + totaljam * hargaq);
        builder.setMessage("Anda Akan Melalukan Pembayaran").setPositiveButton("Bayar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                InputUpdateData(totaljam * hargaq);
            }
        });
        builder.show();
        Log.d("Hasil", String.valueOf(totaljam));
    }

    private void InputUpdateData(int harga) {
        String iduser = prefManager.getId();
        String harg = String.valueOf(harga);
        progressDialog.show();
        mApiService.insertupdate(harg, idvendor, iduser,idalat).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(DetailActivity.this, "Sukses", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DetailActivity.this,MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
