package com.swsoftware.synergytrains;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class ConfigureTicketFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    EditText from;
    EditText to;
    EditText date;
    EditText returnDate;
    AppCompatButton buyButton;
    TextView ticketPriceView;

    TicketClass[] ticketClasses = new TicketClass[] {
            TicketClass.ECONOMY,
            TicketClass.BUSINESS,
            TicketClass.PREMIUM
    };
    int[] ticketPrices = new int[] { 3000, 5000, 7000 };

    TicketClass ticketClass = TicketClass.ECONOMY;
    int ticketPrice = 3000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_configure_ticket, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        from = view.findViewById(R.id.from);
        to = view.findViewById(R.id.to);
        date = view.findViewById(R.id.date);
        returnDate = view.findViewById(R.id.returnDate);
        buyButton = view.findViewById(R.id.buyButton);
        ticketPriceView = view.findViewById(R.id.price);

        MainActivity activity = ((MainActivity) requireContext());
        activity.collapsingToolbarLayout.setTitle("Купить билет");
        activity.tabLayout.setVisibility(View.VISIBLE);

        LinearLayout.LayoutParams dateParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 50F, getResources().getDisplayMetrics())
        );
        LinearLayout.LayoutParams returnDateParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 50F, getResources().getDisplayMetrics())
        );
        dateParams.weight = 0;
        date.setLayoutParams(dateParams);
        returnDateParams.weight = 1;
        returnDate.setLayoutParams(returnDateParams);

        ValueAnimator animator = new ValueAnimator();
        animator.setDuration(200);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                dateParams.weight = (float) animation.getAnimatedValue();
                date.setLayoutParams(dateParams);
            }
        });

        activity.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        animator.setFloatValues(1.0f, 0.0f);
                        animator.start();
                        break;
                    case 1:
                        animator.setFloatValues(0.0f, 1.0f);
                        animator.start();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        Spinner ticketClassSpinner = view.findViewById(R.id.ticketClass);
        ArrayAdapter arrayAdapter = new ArrayAdapter(
                requireContext(),
                R.layout.spinner_item,
                getResources().getTextArray(R.array.ticket_class_list));
        ticketClassSpinner.setAdapter(arrayAdapter);
        ticketClassSpinner.setOnItemSelectedListener(this);

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @Nullable String returnDateValue = null;
                if (returnDate.getText().length() > 0) {
                    returnDateValue = returnDate.getText().toString();
                }
                Ticket ticket = new Ticket(
                        0,
                        from.getText().toString(),
                        to.getText().toString(),
                        date.getText().toString(),
                        returnDateValue,
                        ticketPrice,
                        ticketClass
                );
                activity.addTicket(ticket);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ticketClass = ticketClasses[position];
        ticketPrice = ticketPrices[position];
        ticketPriceView.setText("Стоимость: ₽" + ticketPrice);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}