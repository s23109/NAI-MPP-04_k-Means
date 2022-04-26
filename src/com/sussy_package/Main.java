package com.sussy_package;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        final int k = 3;
        final int max_powtorzen_petli = 10000;
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

        List<List<List<Double>>> poprzednie_centroidy = new ArrayList<>();

        boolean doPetli = true;
        int powtorzenia = 0;
        while (doPetli){
            powtorzenia+=1;
            // przejdź przez elementy- klasyfikuj je
            for (Element element: elementList) {
                double smallestDistance = Double.MAX_VALUE;
                String smallestName = null;

                //sprawdź do którego jest najmniejszy dystans
                for (Centroid centroid : centroidList) {
                   double distance = centroid.getDistanceTo(element.getCoordinates());
                   if (smallestDistance>distance){
                       smallestDistance=distance;
                       smallestName=centroid.centroidName;
                   }
                }
                //przypisz go
                element.setAssigned_centroid(smallestName);
            }


            // oblicz nowe centroidy
            for (Centroid ce : centroidList) {
                List<Element> nalezace = new ArrayList<>();
                for (Element e: elementList) {
                    if (e.assigned_centroid.equals(ce.centroidName)){ nalezace.add(e);}
                }
                ce.calculateNewCoordinate(nalezace);
            }


            //dodaj je do listy-> sprawdź czy ostatnie są takie same
            poprzednie_centroidy.add(Centroid.generateCentroidCoordinates(centroidList));

            if (poprzednie_centroidy.size()==2){
                //sprawdź czy to te same
                if (poprzednie_centroidy.get(0).equals(poprzednie_centroidy.get(1))){
                    //dwie poprzednie grupy są takie same , wywal
                    doPetli=false;
                    System.out.println("Dwie poprzednie grupy centroidów są takie same, wychodzenie z pętli");
                }else {
                    poprzednie_centroidy.remove(0);
                }
            }

            // If do liczby powtórzeń
            if (powtorzenia>= max_powtorzen_petli){
                System.out.println("Osiągnięto limit powtórzeń pętli nauki, wychodzenie");
                doPetli=false;
            }



        }
        
        //TODO  
        // wypisz czystość grup itp itd

    }
}
