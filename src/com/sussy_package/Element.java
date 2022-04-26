package com.sussy_package;

import java.util.ArrayList;
import java.util.List;

public class Element {

    List<Double> coordinates;
   public String name_of_object;
    String assigned_centroid;

    public String getAssigned_centroid() {
        return assigned_centroid;
    }

    public void setAssigned_centroid(String assigned_centroid) {
        this.assigned_centroid = assigned_centroid;
    }

    public Element(String name_of_object, List<Double> coordinates) {
        this.name_of_object = name_of_object;
        this.coordinates = coordinates;
    }

    public Element (String[] line){
        // -1 bo ostatni ele to nazwa
        List<Double> coor_temp = new ArrayList<>();
        for (int i = 0; i < line.length-1; i++) {
            coor_temp.add(Double.valueOf(line[i]));
        }
        this.coordinates=coor_temp;
        this.name_of_object=line[line.length-1];
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

}
