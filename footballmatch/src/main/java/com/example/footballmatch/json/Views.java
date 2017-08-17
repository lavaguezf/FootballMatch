package com.example.footballmatch.json;

import java.util.List;

/**
 * Created by U310 on 2016/8/29.
 */
public class Views {
    private List<Saicheng> saicheng1;
    private List<Saicheng>saicheng2;
    private List<Jifenbang>jifenbang;
    private List<Sheshoubang>sheshoubang;

    public List<Saicheng> getSaicheng1() {
        return saicheng1;
    }

    public void setSaicheng1(List<Saicheng> saicheng1) {
        this.saicheng1 = saicheng1;
    }

    public List<Saicheng> getSaicheng2() {
        return saicheng2;
    }

    public void setSaicheng2(List<Saicheng> saicheng2) {
        this.saicheng2 = saicheng2;
    }

    public List<Jifenbang> getJifenbang() {
        return jifenbang;
    }

    public void setJifenbang(List<Jifenbang> jifenbang) {
        this.jifenbang = jifenbang;
    }

    public List<Sheshoubang> getSheshoubang() {
        return sheshoubang;
    }

    public void setSheshoubang(List<Sheshoubang> sheshoubang) {
        this.sheshoubang = sheshoubang;
    }
}
