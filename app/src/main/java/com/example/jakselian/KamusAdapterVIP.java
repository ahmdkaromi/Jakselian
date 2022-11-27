package com.example.jakselian;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class KamusAdapterVIP extends RecyclerView.Adapter<KamusAdapterVIP.ViewHolder> {

    List<Kamus> kamusarray;
    Context context;
    DBHelper dbHelper;

    public KamusAdapterVIP(List<Kamus> kamusarray, Context context) {
        this.kamusarray = kamusarray;
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.kamus_item_list_vip,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Kamus kamus = kamusarray.get(position);

        //holder.textView_ID.setText(Integer.toString(kamus.getId()));
        holder.katavip.setText(kamus.getKata());
        holder.artivip.setText(kamus.getArti());
        holder.kalimatvip.setText(kamus.getKalimat());

    }

    @Override
    public int getItemCount() {
        return kamusarray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView katavip, artivip, kalimatvip;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            katavip = itemView.findViewById(R.id.textkatavip);
            artivip = itemView.findViewById(R.id.textartivip);
            kalimatvip = itemView.findViewById(R.id.textkalimatvip);
        }
    }
}
