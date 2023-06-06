package com.swsoftware.synergytrains;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MyTicketsAdapter extends RecyclerView.Adapter<MyTicketsAdapter.MyTicketsViewHolder> {

    ArrayList<Ticket> tickets;

    public MyTicketsAdapter(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }
    public static class MyTicketsViewHolder extends ViewHolder {
        TextView destination;
        TextView date;
        TextView price;

        public MyTicketsViewHolder(@NonNull View itemView) {
            super(itemView);

            destination = itemView.findViewById(R.id.destination);
            date = itemView.findViewById(R.id.date);
            price = itemView.findViewById(R.id.ticketPrice);
        }
    }
    @NonNull
    @Override
    public MyTicketsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ticket_layout, parent, false);

        return new MyTicketsViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyTicketsViewHolder holder, int position) {
        Ticket ticket = tickets.get(position);
        holder.date.setText(ticket.getDate());
        String destinationText = ticket.getFrom() + " - " + ticket.getTo();
        holder.destination.setText(destinationText);
        holder.price.setText("₽" + ticket.getPrice());
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }
}
