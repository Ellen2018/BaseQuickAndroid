package com.ellen.example.Java23_Design_Patterns.Builder;

public class MacBuilder implements Builder{

    private Computer macComputer = new Computer();

    @Override
    public Builder builderBoard(String mBoard) {
        macComputer.setmBoard(mBoard);
        return this;
    }

    @Override
    public Builder builderDisplay(String mDisplay) {
        macComputer.setmDisplay(mDisplay);
        return this;
    }

    @Override
    public Builder builderOS(String mOS) {
        macComputer.setmOS(mOS);
        return this;
    }

    @Override
    public Computer build() {
        return macComputer;
    }
}
