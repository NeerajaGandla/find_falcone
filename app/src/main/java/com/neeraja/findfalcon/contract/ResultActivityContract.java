package com.neeraja.findfalcon.contract;

public class ResultActivityContract {
    public interface View {
        void onLoad(String message, String planetName);
        void onClickStart();
    }
}
