package ee.bcs.valiit.tasks;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class Lesson2 {
    public static void main(String[] args) {
        firstN(3);

                // TODO siia saab kirjutada koodi testimiseks
    }

    // TODO tagasta massiiv milles oleks numbrid 1,2,3,4,5
    public static int[] sampleArray() {
        int[] minuarray1 = new int[5]; // array {0,0,0,0,0}

        for (int i = 0; i < 5; i++) {
            minuarray1[i] = i + 1;
        }
        System.out.println(minuarray1);
        return minuarray1;
    }

    // TODO tagasta n esimest arvu alates 1-st
    // näiteks
    // sisend: 5
    // tagasta: 1 2 3 4 5
    public static int[] firstN(int n) {

        int[] minuarray2 = new int[n]; //array pikkusega n
        int i = 0;
        while (i < n) {
            minuarray2[i] = i + 1;
            i++;
        }

        return minuarray2;
    }

    // TODO loo massiiv mis saab sisendiks n ja tagastab massiivi suurusega n
    // Kus esimene element on 1 ja iga järnev arv on 1 võrra suurem
    // näiteks:
    // sisend: 5
    // vastus: {1, 2, 3, 4, 5}
    public static int[] generateArray(int n) {
        int[] minuarray3 = new int[n]; //array pikkusega n
        int i = 0;
        while (i < n) {
            minuarray3[i] = i + 1;
            i++;
        }
        return minuarray3;


    }

    // TODO
    // Tagastada massiiv kus oleks numbrid n-ist 0 ini
    // Näiteks: sisend: 5
    // Väljund: 5, 4, 3, 2, 1, 0
    // Näide2: siend: -3
    // Väljund: -3, -2, -1, 0
    public static int[] decreasingArray(int n) {

        int[] minuarray4 = new int[Math.abs(n)+1]; //array pikkusega n+1
        int i=0;
        if (n>=0) {
        while (i<=n) {
            minuarray4[i] = n-i;
            i++;
            System.out.println(Arrays.toString(minuarray4));
        }} else { int m = Math.abs(n);
            while (i<=m) {
                minuarray4[i] = n + i;
                i++;
                System.out.println(Arrays.toString(minuarray4));
            }}


        return minuarray4;
    }

    // TODO
    // tagasta massiiv pikkusega n, mille kõigi elementide väärtused on 3
    public static int[] yl3(int n) {

        int[] minuarray5=new int[n];
        int i = 0;
        while (i<n) {minuarray5[i] = 3; i++;}

        return minuarray5;
    }
}
