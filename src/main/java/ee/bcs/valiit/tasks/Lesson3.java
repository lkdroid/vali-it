package ee.bcs.valiit.tasks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Lesson3 {

    public static void main(String[] args) {

    }
    // TODO tagasta x faktoriaal.
    // Näiteks
    // x = 5
    // return 5*4*3*2*1 = 120
    public static int factorial(int x) {
        int p = x;
        for (int i = 1; i < p; i++) {

            x = x * (p - i);

        }

        return x;
    }

    // TODO tagasta string tagurpidi
    public static String reverseString(String a) {
        if (a.length() == 1) {
            return a;
        } else if (a.length() == 0) {
            return "";
        } else {
            int p = a.length();

            String uus = "";
            for (int i = 0; i < p; i++) {

                uus = uus + a.charAt(p - i - 1);

            }
            return uus;
        }

    }

    // TODO tagasta kas sisestatud arv on primaar arv (jagub ainult 1 ja iseendaga)
    public static boolean isPrime(int x) {
        if (x == 0 || x == 1) {
            return false;
        }
        int i = 2;
        while (i < Math.sqrt(x)) {
            if (x % i == 0) {
                return false;
            }
            i++;
        }
        return true;

    }


    // TODO sorteeri massiiv suuruse järgi.
    // TODO kasuta tsükleid, ära kasuta ühtegi olemasolevat sort funktsiooni
    public static int[] sort(int[] a) {
        int L = a.length;
        System.out.println("Stringi pikkus on: " + L);
        System.out.println(Arrays.toString(a));
        for (int j = 1; j <= L; j++) {
            int i = 0;
            while (i < L - 1) {
                System.out.println("I väärtus on: " + i);
                System.out.println("Ai väärtus on: " + a[i]);
                System.out.println("Ai+1 väärtus on: " + a[i + 1]);
                if (a[i] > a[i + 1]) {
                    int tmp = a[i + 1];
                    a[i + 1] = a[i];
                    a[i] = tmp;
                    System.out.println("tmp on: " + tmp);
                }
                System.out.println(Arrays.toString(a));
                i++;
            }
        }

        return a;
    }

    public static int evenFibonacci(int x) {
        // TODO liida kokku kõik paaris fibonacci arvud kuni numbrini x
        System.out.println(x);
        int[] fibo = new int[x + 1];
        if (x == 0) {
            return 0;
        } else if (x == 1) {
            return 0;
        } else
            fibo[0] = 0;
        fibo[1] = 1;
        int sum = 0;
        for (int i = 2; i <= x; i++) {
            fibo[i] = fibo[i - 1] + fibo[i - 2];
            if (fibo[i] % 2 == 0 && fibo[i] <= x) {
                sum = sum + fibo[i];
            }
        }
        return sum;

    }

    public static String morseCode(String text) {
        // TODO kirjuta programm, mis tagastab sisestatud teksti morse koodis (https://en.wikipedia.org/wiki/Morse_code)
        // Kasuta sümboleid . ja - ning eralda kõik tähed tühikuga

        HashMap<String, String> morsekood = new HashMap<>();
        morsekood.put("h", "....");
        morsekood.put("e", ".");
        morsekood.put("l", ".-..");
        morsekood.put("o", "---");
        morsekood.put("s", "...");
        int L = text.length();
        System.out.println(text);
        String tulemus = new String();
        for (int i = 0; i < L; i++) {
            String tahtStringiks = String.valueOf(text.charAt(i));
            if (i < L-1) {tulemus = tulemus + morsekood.get(tahtStringiks) + " ";}
            else {tulemus = tulemus + morsekood.get(tahtStringiks);}
        }
        return tulemus;
    }
}
