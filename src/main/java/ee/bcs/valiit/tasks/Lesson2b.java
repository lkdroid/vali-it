package ee.bcs.valiit.tasks;

import java.sql.SQLOutput;
import java.util.Arrays;

public class Lesson2b {

    public static void main(String[] args) {
        // TODO siia saab kirjutada koodi testimiseks
        System.out.println(fibonacci(3));
    }

    // TODO loe funktsiooni sisendiks on täisarvude massiiv
    // TODO tagasta massiiv mille elemendid on vastupidises järiekorras
    public static int[] reverseArray(int[] inputArray) {
        int arrayL = inputArray.length;
        int[] newArray = new int[arrayL];
        int i = 0;
        while (i < arrayL) {
            newArray[i] = inputArray[arrayL - (i + 1)];
            i++;
        }
        return newArray;
    }

    // TODO tagasta massiiv mis sisaldab n esimest paaris arvu
    // Näide:
    // Sisend 5
    // Väljund 2 4 6 8 10
    public static int[] evenNumbers(int n) {
        int[] newArray = new int[n];
        int i = 0;
        while (i < n) {
            newArray[i] = (i + 1) * 2;
            i++;
        }
        return newArray;
    }

    // TODO, leia massiivi kõige väiksem element
    // tema lahendas selle andes muutujale integer.MAX_VALUE ehk suurima integeri väärtuse ning võrdles
    // esimest numbrit sellega ja määras seega deafultilt väikseimaks (muutuja vaikseim) array esimese numbri
    //seejärel võrdles järgmisi ning pani väiksema kirja)
    //

    public static int min(int[] x) {
        int L = x.length;
        System.out.println("Massiivi pikkus on: " + L);
        int i = 0;
        int vaike = 0;
        if (x[i] < x[i + 1]) {
            vaike = x[i];
        } else {
            vaike = x[i + 1];
        }
        for (i = 2; i < L; i++) {
            if (vaike > x[i]) {
                vaike = x[i];
            }
            System.out.println(vaike);
        }

        return vaike;
    }

    // TODO, leia massiivi kõige suurem element
    // siin kasutas integer.MIN.VALUE
    //

    public static int max(int[] x) {
        int L = x.length;
        System.out.println("Suurima leidmise massiivi pikkus on: " + L);
        int i = 0;
        int suur = 0;
        if (x[i] > x[i + 1]) {
            suur = x[i];
        } else {
            suur = x[i + 1];
        }
        for (i = 2; i < L; i++) {
            if (suur < x[i]) {
                suur = x[i];
            }
            System.out.println(suur);
        }
        System.out.println("Suurim on: " + suur);
        return suur;
    }

    // TODO, leia massiivi kõigi elementide summa
    public static int sum(int[] x) {
        int L = x.length;
        int i = 1;
        int summa = 0;
        for (i = 0; i < L; i++) {
            summa = summa + x[i];
        }
        return summa;
    }

    // TODO trüki välja korrutustabel mis on x ühikut lai ja y ühikut kõrge
    // TODO näiteks x = 3 y = 3
    // TODO väljund
    // 1 2 3
    // 2 4 6
    // 3 6 9
    // TODO 1 trüki korrutustabeli esimene rida (numbrid 1 - x)
    // TODO 2 lisa + " " print funktsiooni ja kasuta print mitte println
    // TODO 3 trüki seda sama rida y korda
    // TODO 4 Kuskile võiks kirjutada System.out.println(),
    //  et saada taebli kuju
    // TODO 5 võrdle ridu. Kas on mingi seaduspärasus ridade vahel,
    // mis on ja mis võiks olla. Äkki tuleb mõni idee
    public static void multiplyTable(int x, int y) {

        int i = 1;
        while (i <= x) {
            System.out.print(i + " ");
            i++;
        }

        int j = 2;
        while (j < y) {
            System.out.println();
            int k = 1;
            while (k <= x) {
                System.out.print(k * j + " ");
                k++;
            }
            j++;
        }

    }

    // TODO
    // Fibonacci jada on fib(n) = fib(n-1) + fib(n-2);
    // 0, 1, 1, 2, 3, 5, 8, 13, 21
    // Tagasta fibonacci jada n element. Võid eeldada, et n >= 0
    public static int fibonacci(int n) {
        int[] fibo = new int[n + 1];
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else
            fibo[0] = 0;
        fibo[1] = 1;
        System.out.println(fibo[0]);
        System.out.println(fibo[1]);
        for (int i = 2; i <= n; i++) {
            fibo[i] = fibo[i - 1] + fibo[i - 2];
            System.out.println("Fibo i-s element on: " + fibo[i]);
        }
        return fibo[n];
    }

    // TODO
    // Kujutame ette numbrite jada, kus juhul kui number on paaris arv siis me jagame selle 2-ga
    // Kui number on paaritu arv siis me korrutame selle 3-ga ja liidame 1. (3n+1)
    // Seda tegevust teeme me niikaua kuni me saame vastuseks 1
    // Näiteks kui sisend arv on 22, siis kogu jada oleks:
    // 22 -> 11 -> 34 -> 17 -> 52 -> 26 -> 13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
    // Sellise jada pikkus on 16
    // Kirjutada programm, mis peab leidma kõige pikema jada pikkuse mis jääb kahe täis arvu vahele
    // Näiteks:
    // Sisend 10 20
    // Siis tuleb vaadata, kui pikk jada tuleb kui esimene numbr on 10. Järgmisen tuleb arvutada number 11 jada pikkus.
    // jne. kuni numbrini 20
    // Tagastada tuleb kõige pikem number
    // Näiteks sisendi 10 ja 20 puhul on vastus 20

    public static int sequence3n(int x, int y) {
        int k = 0;
        int i = 1;
        for (int j = x; j <= y; j++) {
            if (i > k) {
                k = i;
            }
            int n = j;
            i = 1;
            while (n != 1) {
                i++;
                if (n % 2 == 0) {
                    n = n / 2;
                } else {
                    n = n * 3 + 1;
                }
            }
        }

        return k;
    }


}
