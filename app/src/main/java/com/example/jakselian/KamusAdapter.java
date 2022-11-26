package com.example.jakselian;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class KamusAdapter extends RecyclerView.Adapter<KamusAdapter.ViewHolder> {

    List<Kamus> kamusarray;
    Context context;
    DBHelper dbHelper;

    public KamusAdapter(List<Kamus> kamusarray, Context context) {
        this.kamusarray = kamusarray;
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.kamus_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Kamus kamus = kamusarray.get(position);

        holder.textView_ID.setText(Integer.toString(kamus.getId()));
        holder.editText_Kata.setText(kamus.getKata());
        holder.editText_Arti.setText(kamus.getArti());
        holder.editText_Kalimat.setText(kamus.getKalimat());

        holder.button_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringKata = holder.editText_Kata.getText().toString();
                String stringArti = holder.editText_Arti.getText().toString();
                String stringKalimat = holder.editText_Kalimat.getText().toString();

                dbHelper.updateKamus(new Kamus(kamus.getId(),stringKata,stringArti,stringKalimat));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());

            }
        });

        holder.button_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteKamus(kamus.getId());
                kamusarray.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return kamusarray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView_ID;
        EditText editText_Kata, editText_Arti, editText_Kalimat;
        Button button_Edit, button_Delete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_ID = itemView.findViewById(R.id.kamus_id);
            editText_Kata = itemView.findViewById(R.id.editkata);
            editText_Arti = itemView.findViewById(R.id.editarti);
            editText_Kalimat = itemView.findViewById(R.id.editkalimat);
            button_Edit = itemView.findViewById(R.id.button_edit);
            button_Delete = itemView.findViewById(R.id.button_delete);

        }
    }
}
