package com.swsoftware.synergytrains;

import androidx.annotation.Nullable;

public class Ticket {
    private final int userId;
    private final String from;
    private final String to;
    private final String date;
    @Nullable private String returnDate = null;
    private final int price;
    private TicketClass ticketClass = TicketClass.ECONOMY;

    public Ticket(int userId,
                  String from,
                  String to,
                  String date,
                  @Nullable String returnDate,
                  int price,
                  TicketClass ticketClass)
    {
        this.userId = userId;
        this.from = from;
        this.to = to;
        this.date = date;
        this.returnDate = returnDate;
        this.price = price;
        this.ticketClass = ticketClass;
    }

    public Ticket(int userId,
                  String from,
                  String to,
                  String date,
                  @Nullable String returnDate,
                  int price)
    {
        this.userId = userId;
        this.from = from;
        this.to = to;
        this.date = date;
        this.returnDate = returnDate;
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public int getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public String getFrom() {
        return from;
    }

    @Nullable
    public String getReturnDate() {
        return returnDate;
    }

    public String getTo() {
        return to;
    }

    public TicketClass getTicketClass() {
        return ticketClass;
    }
}
