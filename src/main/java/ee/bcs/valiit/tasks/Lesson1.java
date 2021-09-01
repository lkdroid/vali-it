package ee.bcs.valiit.tasks;


import java.util.Scanner;

// TODO kasuta if/else. Ära kasuta Math librarit
public class Lesson1 {
    public static void main(String[] args) {
        // Siia sisse võid sa kirjutada koodi, et testida kas su meetodid töötavad ilusti
        // Katseta IntelliJ shortcuti. Olles intelliJ kirjuta "sout" ja siis vajuta enter
        System.out.println("--------------------------------------------------------------");
        System.out.println("See on nüüd Lesson1 ning lisaülesande katsetused");
        System.out.println("--------------------------------------------------------------");
        lisaylesanne();

    }

    // TODO
    //  Tagasta string mille väärtus oleks "\"\\""
    //  Trüki muutuja sisu välja
    public static String someString() {
        String proov;
        proov = "\"" + "\\" + "\"" + "\\\\" + "\"\"";
        System.out.println(proov);

        return proov;
    }

    // TODO tagasta a ja b väikseim väärtus
    public static int min(int a, int b) {
        int x;
        if (a < b) {
            x = a;
        } else {
            x = b;
        }
        System.out.println("Neist numbritest on väikseim: " + x);
        return x;
    }

    // TODO tagasta a ja b suurim väärtus
    public static int max(int a, int b) {
        int y;
        if (a > b) {
            y = a;
        } else {
            y = b;
        }
        ;
        System.out.println("Neist arvudest suurim on: "+ y);
        return y;
    }

    // TODO tagasta a absoluut arv
    public static int abs(int a) {
        int z;
        if (a < 0) {
            z = -a;
        } else {
            z = a;
        }
        System.out.println("Selle numbri absoluutväärtus on: "+ z);
        return z;
    }

    // TODO tagasta true, kui a on paaris arv
    // tagasta false kui a on paaritu arv
    public static boolean isEven(int a) {
        boolean q;
        if (a % 2 == 0) {
            q = true;
        } else {
            q = false;
        }
        ;
        System.out.println("isEven");
        return q;
    }

    // TODO tagasta kolmest arvust kõige väiksem
    public static int min3(int a, int b, int c) {
        int zx;
        if (a <= b && a <= c) {
            zx = a;
        } else if (b <= a && b <= c) {
            zx = b;
        } else {
            zx = c;
        }
        ;
        System.out.println("Väikseim on: " + zx);
        return zx;
    }

    // TODO tagasta kolmest arvust kõige suurem
    public static int max3(int a, int b, int c) {
        int zx;
        if (a >= b && a >= c) {
            zx = a;
        } else if (b >= a && b >= c) {
            zx = b;
        } else {
            zx = c;
        }
        ;
        return zx;
    }

    // TODO tagasta kolmest arvust kõige suurem
    public static void lisaylesanne() {
                int kysinumbrid;
        System.out.println("------------------------");
        System.out.println("Sisesta millist meetodit soovid jooksutada: 1-min 2-max 3-abs 4-isEven 5-min3 6-max3");

            Scanner valimeetod = new Scanner(System.in); // sisestatakse meetodi number
            int n = valimeetod.nextInt();
            System.out.println("Sinu valik oli: " + n);
            if ( n>6 || n<1) {
                System.out.println("Pidid valima 1-st 6-ni!");}
            //Nüüd on teada millist meetodit ta tahab ja aeg on küsida parameetrid.

        if (n == 3) {
            System.out.println("Sisesta nüüd number mille absoluutväärtust me leiame:");
            Scanner in = new Scanner(System.in);
            int absn= in.nextInt();
            abs(absn);
        } else if (n < 3 ) {
            System.out.println("Sisesta järgemööda kaks arvu ning vajuta sisestamise vahepeal Enter");
            Scanner n1 = new Scanner(System.in); //sisestatakse parameeter min või max jaoks
            int min1 = n1.nextInt();
            Scanner n2 = new Scanner(System.in);
            int min2 = n2.nextInt();
            if (n == 1) {min(min1,min2);}
            if (n == 2) {max(min1,min2);}}
        else {
            System.out.println("Sisesta järgemööda kolm arvu ning vajuta sisestamise vahepeal Enter");
            Scanner n1 = new Scanner(System.in); // sisestatakse parameeter min3 või max3 jaoks
            int min1 = n1.nextInt();
            Scanner n2 = new Scanner(System.in);
            int min2 = n2.nextInt();
            Scanner n3 = new Scanner(System.in);
            int min3 = n3.nextInt();
            if (n == 5) {min3(min1,min2,min3);}
            if (n == 6) {max3(min1,min2,min3);}
        }


        System.out.println("--------------------------------");
        System.out.println("See on lisaülesande lõpp!");

    }
}
