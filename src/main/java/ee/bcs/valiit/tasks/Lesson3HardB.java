package ee.bcs.valiit.tasks;

import java.util.Random;
import java.util.Scanner;

public class Lesson3HardB {

    // TODO kirjuta mäng kus kasutaja peab ära arvama numbri 0-99 (nagu 3Hard)
    // NB programm ei tohi kohe alguses välja mõelda numbrit
    // vaid eesmärk on öelda kasutajale, et ta eksis nii kaua kui võimalik
    // ilma selleta, et ta läheks vastuollu ühegi varasema väitega
    public static String main(String[] args){
System.out.println("---------Sisesta oma pakkumine, mis numbri arvuti valis: ---------------");
    int x = 99;
    int y = 0;
    Scanner sisendnr = new Scanner(System.in);
        int p = sisendnr.nextInt();
        if (x-p > p-y) {y=p;
            return "Liiga väike";}
        else if (x-p > p-y) {x=p;
            return "Liiga suur";}
        else if (x-p == p-y && x-p!=1) {y=p; return "Liiga väike";}
        else {return "Õige!";}

    }
}
