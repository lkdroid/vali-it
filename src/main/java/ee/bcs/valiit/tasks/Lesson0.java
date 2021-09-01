package ee.bcs.valiit.tasks;

import java.util.Random;
import java.util.Scanner;

public class Lesson0 {
    public static void main(String[] args) {
        excersie1();
        excersie2();
        excersie3();
        excersie4();
        game();
    }

    // TODO
    //  defineeri 3 muutujat a = 1, b = 1, c = 3
    //  Prindi välja a==b
    //  Prindi välja a==c
    //  Lisa rida a = c
    //  Prindi välja a==b
    //  Prindi välja a==c, mis muutus???
    public static void excersie1() {
        int a = 1;
        int b = 1;
        int c = 3;

        a = c;

    }

    // TODO
    //  Loo muutujad x1 = 10 ja x2 = 20, vali sobiv andmetüüp
    //  Tekita muutuja y1 = ++x1, trüki välja nii x1 kui y1
    //  Tekita muutuja y2 = x2++, trüki välja nii x2 ja y2
    //  Analüüsi tulemusi
    public static void excersie2() {
        int x1 = 10;
        int x2 = 20;
        int y1 = ++x1;

        int y2 = x2++;



    }

    // TODO
    //  Loo arvulised muutujad
    //  a = 18 % 3
    //  b = 19 % 3
    //  c = 20 % 3
    //  d = 21 % 3
    //  Prindi välja kõigi muutujate väärtused
    public static void excersie3() {
        int a = 16 % 3;
        int b = 19 % 3;
        int c = 20 % 3;
        int d = 21 % 3;

    }

    public static void excersie4() {
        int a = -100;
        int b = 40000;

        a = a + b;
        b = b - a;
        a = b + a;
        b = -b;

    }

    public static void game() {
        // TODO kirjuta mäng mis leiab suvalise numbri 0-99, mille kasutaja peab ära arvama
        // iga kord pärast kasutaja sisestatud täis arvu peab programm ütlema kas number oli suurem või väiksem
        // ja kasutaja peab saama uuesti arvata
        // numbri ära aramise korral peab programm välja trükkima mitu katset läks numbri ära arvamiseks
        System.out.println(" ------- See on ära arvamise mäng! ----");
        Random random = new Random();
        int i = random.nextInt(100);
        System.out.println(i);
        int m;
        if (i == 0) {m = 1;} else {m=0;}
        int luger = 0;
        while (m != i) {
        System.out.println("---------Sisesta oma pakkumine, mis numbri arvuti valis: ---------------");
        Scanner sisendnr = new Scanner(System.in);
        int n = sisendnr.nextInt();
        if (n<i) {
            System.out.println("VALE! Otsitav arv on SUUREM!");
        }
            else if (n > i){
            System.out.println("VALE! Otsitav arv on VÄIKSEM!");
            }
            m=n;
            luger++;
        }
        System.out.println("-------------------------------------------------------------------");
        System.out.println("ÕIGE VASTUS!");
        System.out.println("Arvasid õigesti - number oligi: " + m);
        System.out.println("Sul oli vaja " + luger + " katset!");

    }


}
