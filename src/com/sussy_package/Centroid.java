package com.sussy_package;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Centroid {


    List<Double> centroidCoordinates;
    String centroidName;
    public Map<String,Integer> elementCount = new HashMap<>();
    public int elementAmount = 0;

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

    public void calculateNewCoordinate (List<Element> elementList){

        List<Double> newCoordinates = elementList.get(0).getCoordinates();

        for (int i = 1; i < elementList.size(); i++) {

            for (int j = 0; j < elementList.get(i).coordinates.size(); j++) {

                newCoordinates.set(j,newCoordinates.get(j)+elementList.get(i).getCoordinates().get(j));

            }

        }

        // macierz z sumą długości wektorów

        for (int i = 0; i < newCoordinates.size(); i++) {
            newCoordinates.set(i, newCoordinates.get(i)/elementList.size());
        }

        setCentroidCoordinates(newCoordinates);

    }

    public void addToMap (Element element){

        if (elementCount.containsKey(element.name_of_object)){
            //jeśli zawiera to dodaj
            elementCount.put(element.name_of_object, (elementCount.get(element.name_of_object)+1));

        }
        else {
            //dodaj indeks równy 1
            elementCount.put(element.name_of_object,1);
        }
        this.elementAmount = elementAmount+1;
    }



}
