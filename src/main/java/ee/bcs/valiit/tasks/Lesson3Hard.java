package ee.bcs.valiit.tasks;

import java.util.Random;
import java.util.Scanner;

public class Lesson3Hard {


    // TODO kirjuta mäng mis leiab suvalise numbri 0-99, mille kasutaja peab ära arvama
    // iga kord pärast kasutaja sisestatud täis arvu peab programm ütlema kas number oli suurem või väiksem
    // ja kasutaja peab saama uuesti arvata
    // numbri ära aramise korral peab programm välja trükkima mitu katset läks numbri ära arvamiseks
    public static void main(String [] args){

        Random random = new Random();
        int i = random.nextInt(100);
        System.out.println(i);

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
