package com.sussy_package;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        final int k = 3;

        List<Element> elementList = File_operations.list_from_file("Dane\\iris.data.txt");



        List<Centroid> centroidList = new ArrayList<>();

        //los koord centroidów
        for (int i = 0; i < k; i++) {
            centroidList.add(
                    new Centroid(
                            new String("Centroid" + String.valueOf(i)),
                            elementList.get((int) (elementList.size()*(double)i/k)).getCoordinates()
                            )
            );

            System.out.println("Utworzono "+ centroidList.get(i).centroidName + " o koord wstępnych " + centroidList.get(i).centroidCoordinates);
        }

        Stack<List<List<Double>>> poprzednie_centroidy = new Stack<>();

    }
}
