package com.sussy_package;

import java.util.ArrayList;
import java.util.List;

public class Centroid {


    List<Double> centroidCoordinates;
    String centroidName;

    public Centroid(String centroidName , List<Double> centroidCoordinates ) {
        this.centroidCoordinates = centroidCoordinates;
        this.centroidName = centroidName;

    }

    public List<Double> getCentroidCoordinates() {
        return centroidCoordinates;
    }

    public void setCentroidCoordinates(List<Double> centroidCoordinates) {
        this.centroidCoordinates = centroidCoordinates;
    }

    public static List<List<Double>> generateCentroidCoordinates (List<Centroid> centroids){
        List<List<Double>> doReturna = new ArrayList<>();

        for (Centroid element: centroids
             ) {
            doReturna.add(element.getCentroidCoordinates());
        }
        return doReturna;
    }

    public double getDistanceTo (List<Double> coordinates ){
        double distance = 0;

        for (int i = 0; i < this.centroidCoordinates.size(); i++) {
            distance+= Math.pow((coordinates.get(i)-centroidCoordinates.get(i)),2);
        }

        return Math.sqrt(distance);
    }

}
