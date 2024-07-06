package com.example.act5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private final List<ClassHistory> data;
    private final int layout;

    public HistoryAdapter(List<ClassHistory> data, int layout) {
        this.data = data;
        this.layout = layout;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderNumber;
        TextView tvHistoryGameName;
        TextView tvNamaAkunGame;
        TextView tvOrderStatus;

        public ViewHolder(View row) {
            super(row);
            tvOrderNumber = row.findViewById(R.id.tvOrderNumber);
            tvHistoryGameName = row.findViewById(R.id.tvHistoryGameName);
            tvNamaAkunGame = row.findViewById(R.id.tvNamaAkunGame);
            tvOrderStatus = row.findViewById(R.id.tvOrderStatus);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClassHistory history = data.get(position);
        holder.tvOrderNumber.setText(String.valueOf("Order ID: "+history.getOrderId()));
        holder.tvHistoryGameName.setText("Game Name: "+history.getGameName());
        holder.tvNamaAkunGame.setText("Nama Akun Game: "+history.getAccountName());
        holder.tvOrderStatus.setText("Status Order: "+history.getStatus());

//            String[] temp = question.getTags().split(",");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
