package com.movie.springboot.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;


public class Output {

    /** 年龄 */
    private List<Integer> coords;

    private Integer patches;

    public Output(){
        super();
    }

    public Output(List<Integer> coords, Integer patches) {
        this.coords = coords;
        this.patches = patches;
    }

    public List<Integer> getCoords() {
        return coords;
    }

    public void setCoords(List<Integer> coords) {
        this.coords = coords;
    }

    public Integer getPatches() {
        return patches;
    }

    public void setPatches(Integer patches) {
        this.patches = patches;
    }
}
