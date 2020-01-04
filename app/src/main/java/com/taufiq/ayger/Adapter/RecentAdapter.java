package com.taufiq.ayger.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.taufiq.ayger.Model.SemuaLapangan;
import com.taufiq.ayger.R;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.RecentHolder> {

    List<SemuaLapangan> semuaLapangans;
    Context mContect;

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

    public RecentAdapter(Context mContext, List<SemuaLapangan> semuaDosenItems) {
        this.semuaLapangans = semuaDosenItems;
        this.mContect = mContext;
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


    @NonNull
    @Override
    public RecentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.list_item, parent, false);

        return new RecentHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RecentHolder holder, int position) {
        final SemuaLapangan responseLapangan = semuaLapangans.get(position);
        holder.tvKota.setText(responseLapangan.getAlamat());
        holder.tvNama.setText(responseLapangan.getNama());

        String namaDosen = responseLapangan.getNama();
        String firstCharNamaDosen = namaDosen.substring(0, 1);
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(firstCharNamaDosen, getColor());
        holder.imglist1.setImageDrawable(drawable);
    }


    @Override
    public int getItemCount() {
        return semuaLapangans.size();
    }

    public class RecentHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imglist1)
        ImageView imglist1;
        @BindView(R.id.tvNama)
        TextView tvNama;
        @BindView(R.id.tvKota)
        TextView tvKota;

        RecentHolder(View view) {
            super(view);
            ButterKnife.bind(mContect, view);


        }
    }
}
