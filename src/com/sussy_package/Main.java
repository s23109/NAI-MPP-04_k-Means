package com.sussy_package;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        final int k = 3;
        final int max_powtorzen_petli = 10000;
        final List<Element> elementList = File_operations.list_from_file("Dane\\iris.data.txt");

        System.out.println("Elements amount :" + elementList.size());

        List<Centroid> centroidList = new ArrayList<>();

        //los koord centroidów
        for (int i = 0; i < k; i++) {
            centroidList.add(
                    new Centroid(
                            new String("Centroid" + String.valueOf(i)),
                            Centroid.generateElements(elementList.get(0).coordinates.size(),0.0,5.0)
            )
            );


            System.out.println("Utworzono "+ centroidList.get(i).centroidName + " o koord wstępnych " + centroidList.get(i).centroidCoordinates);
        }


        List<List<List<Double>>> poprzednie_centroidy = new ArrayList<>();

        boolean doPetli = true;
        int powtorzenia = 0;

        //przypisywanie + obliczenia do tego
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

            System.out.println("---------\nPowtorzenie " + powtorzenia + " :");
            for (Centroid c: centroidList
                 ) {
                System.out.println("---\n" + c.centroidName + ": \n Srednia odleglosc do centroidu : "+ c.sredniaOdleglosc(elementList) + "\n Laczna odleglosc do centroidu :" + c.lacznaOdleglosc(elementList));
            }

        }


        for (Element element: elementList) {
            centroidList.get(Integer.parseInt(element.assigned_centroid.substring(8))).addToMap(element);
        }

        for (Centroid c:centroidList) {
            System.out.println("=====================");
            System.out.println(c.centroidName);
            System.out.println("---------------------");
            System.out.println("Srodek :" + c.centroidCoordinates);
            c.czystosci();
            System.out.println("---------------------");
//            System.out.println("Zawartość: ");
//            int ilosc = 0;
//            for (Element e :elementList) {
//                if (e.assigned_centroid.equals(c.centroidName)){
//                    System.out.println(e.name_of_object + " " + e.getCoordinates());
//                    ilosc+=1;
//                }
//            }
//            System.out.println("Assigned elements = " + ilosc);



            System.out.println("---------------------");

        }
        System.out.println("=====================");


    }
}
