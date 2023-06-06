package com.swsoftware.synergytrains;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;


public class MyTicketsFragment extends Fragment {
    View buyButton;
    RecyclerView ticketsRecyclerView;
    ArrayList<Ticket> tickets;
    MainActivity parentActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_tickets, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        parentActivity = ((MainActivity) requireContext());
        parentActivity.collapsingToolbarLayout.setTitle("Мои билеты");
        tickets = parentActivity.tickets;

        ticketsRecyclerView = view.findViewById(R.id.recyclerView);

        if (tickets.isEmpty()) {
            buyButton = view.findViewById(R.id.buyButton);
        } else {
            buyButton = ((MainActivity) requireContext()).buyFab;
            ticketsRecyclerView.setVisibility(View.VISIBLE);
            ticketsRecyclerView.setAdapter(new MyTicketsAdapter(tickets));
        }
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((FloatingActionButton) buyButton).hide();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, new ConfigureTicketFragment())
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        parentActivity.tabLayout.setVisibility(View.GONE);
        try {
            ((FloatingActionButton) buyButton).show();
        } catch (Exception e) {
            buyButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        try {
            ((FloatingActionButton) buyButton).hide();
        } catch (Exception e) {
            buyButton.setVisibility(View.GONE);
        }
    }
}