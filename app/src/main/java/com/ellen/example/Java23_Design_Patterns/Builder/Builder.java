package com.ellen.example.Java23_Design_Patterns.Builder;

public interface Builder {

    Builder builderBoard(String mBoard);
    Builder builderDisplay(String mDisplay);
    Builder builderOS(String mOS);
    Computer build();
}
