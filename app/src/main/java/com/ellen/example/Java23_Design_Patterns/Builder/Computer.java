package com.ellen.example.Java23_Design_Patterns.Builder;

public class Computer {

    protected String mBoard;
    protected String mDisplay;
    protected String mOS;

    public void setmBoard(String mBoard) {
        this.mBoard = mBoard;
    }

    public void setmDisplay(String mDisplay) {
        this.mDisplay = mDisplay;
    }

    public void setmOS(String mOS) {
        this.mOS = mOS;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "mBoard='" + mBoard + '\'' +
                ", mDisplay='" + mDisplay + '\'' +
                ", mOS='" + mOS + '\'' +
                '}';
    }
}
