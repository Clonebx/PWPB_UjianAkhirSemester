package com.example.pwpb_ujianakhirsemester;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.UserViewHolder> {
    Context context;
    OnUserActionListener listener;
    List<Data> listData;

    public RecyclerViewAdapter(Context context, OnUserActionListener listener, List<Data> listData) {
        this.context = context;
        this.listener = listener;
        this.listData = listData;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list,parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(v);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.UserViewHolder holder, int position) {
        final Data currentData =listData.get(position);

        holder.txtTitle.setText(currentData.getDatatitle());
        holder.txtIsi.setText(currentData.getDataisi());
        holder.date.setText(currentData.getTanggal());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onUserAction(currentData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public interface OnUserActionListener { void onUserAction(Data data); }

    public class UserViewHolder extends RecyclerView.ViewHolder  {
        TextView txtTitle,txtIsi,date;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.Title);
            txtIsi = itemView.findViewById(R.id.isi);
            date = itemView.findViewById(R.id.date);
        }
    }
}
