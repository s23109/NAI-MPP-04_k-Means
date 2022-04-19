package com.sussy_package;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        final int k = 3;

        List<Element> elementList = File_operations.list_from_file("Dane\\iris.data.txt");



        List<Centroid> centroidList = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            centroidList.add(
                    new Centroid(
                            new String("Centroid" + String.valueOf(i)),
                            elementList.get((int) (elementList.size()*(double)i/k)).getCoordinates()
                            )
            );

            System.out.println("Utworzono "+ centroidList.get(i).centroidName + " o koord wstępnych " + centroidList.get(i).centroidCoordinates);
        }


    }
}
