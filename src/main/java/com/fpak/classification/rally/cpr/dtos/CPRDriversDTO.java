package com.fpak.classification.rally.cpr.dtos;


public class CPRDriversDTO{
    private int position;
    private int number;
    private String name;
    private int total;

    public CPRDriversDTO(int position, int number, String name, int total) {
        this.position = position;
        this.number = number;
        this.name = name;
        this.total = total;
    }

    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }
}