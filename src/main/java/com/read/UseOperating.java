package com.read;

public class UseOperating implements Use{
    private Operating operating;


    public void setOperating(Operating operating) {
       this.operating = operating;
    }

    public void read() {
        operating.read();
    }

    public void qing() {
        operating.qingkong();
    }

}
