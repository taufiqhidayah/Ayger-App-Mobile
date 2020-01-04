package com.taufiq.ayger.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.taufiq.ayger.DetailActivity;
import com.taufiq.ayger.MainActivity;
import com.taufiq.ayger.Model.SemuaLapangan;
import com.taufiq.ayger.R;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LapanganAdapter extends RecyclerView.Adapter<LapanganAdapter.LapanganHolder> {
    List<SemuaLapangan> semuaLapangan;
    Context mContext;
    public static final String Key_Location = "keylocation";
    public static final String Key_Nama = "keynama";
    public static final String KEY_ID = "keyId";
    public static final String Key_Desc = "keydesc";
    public static final String Key_Harga = "keyharga";
    public static final String KeyIDAlat = "keyidalat";

    public String[] mColors = {
            "#39add1", // light blue
            "#3079ab", // dark blue
            "#c25975", // mauve
            "#e15258", // red
            "#f9845b", // orange
            "#838cc7", // lavender
            "#7d669e", // purple
            "#53bbb4", // aqua
            "#51b46d", // green
            "#e0ab18", // mustard
            "#637a91", // dark gray
            "#f092b0", // pink
            "#b7c0c7"  // light gray
    };


    @Override
    public int getItemCount() {
        return semuaLapangan.size();
    }

    public class LapanganHolder extends RecyclerView.ViewHolder {

        ImageView imglist1;

        TextView tvNama;
        TextView tvKota;


        LapanganHolder(View itemView) {
            super(itemView);

//            tvsaldo=(TextView) MainActivity.findViewById(R.id.tvsaldo);
            imglist1 = (ImageView) itemView.findViewById(R.id.imglist1);
            tvNama = (TextView) itemView.findViewById(R.id.tvNama);
            tvKota = (TextView) itemView.findViewById(R.id.tvKota);
            ButterKnife.bind(this, itemView);

        }
    }

    public int getColor() {
        String color;

        // Randomly select a fact
        Random randomGenerator = new Random(); // Construct a new Random number generator
        int randomNumber = randomGenerator.nextInt(mColors.length);

        color = mColors[randomNumber];
        int colorAsInt = Color.parseColor(color);

        return colorAsInt;
    }

    public LapanganAdapter(Context context, List<SemuaLapangan> lapanganList) {
        this.mContext = context;
        semuaLapangan = lapanganList;
    }

    @Override
    public LapanganHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater itemView = LayoutInflater.from(parent.getContext());
        View v = itemView.inflate(R.layout.list_item, parent, false);

        
        return new LapanganHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(LapanganHolder holder, int position) {
        TextView tvsaldo;
        View layoutInflater = LayoutInflater.from(mContext).inflate(R.layout.activity_main, null);
        tvsaldo = (TextView) layoutInflater.findViewById(R.id.tvsaldo);

        final SemuaLapangan responseLapangan = semuaLapangan.get(position);
        holder.tvKota.setText(responseLapangan.getAlamat());
        holder.tvNama.setText(responseLapangan.getNama());
        tvsaldo.setText("lll");
        String namaDosen = responseLapangan.getNama();
        String firstCharNamaDosen = namaDosen.substring(0, 1);
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(firstCharNamaDosen, getColor());
        holder.imglist1.setImageDrawable(drawable);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, DetailActivity.class);
                i.putExtra(Key_Desc, responseLapangan.getDesc());
                i.putExtra(Key_Location, responseLapangan.getAlamat());
                i.putExtra(Key_Nama, responseLapangan.getNamalap());
                i.putExtra(Key_Harga, responseLapangan.getHarga());
                i.putExtra(KEY_ID, responseLapangan.getIdpenyewa());
                i.putExtra(KeyIDAlat, responseLapangan.getIdalat());
                mContext.startActivity(i);
            }
        });
    }

}

