package ee.bcs.valiit.tasks;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Lesson4 {
    // Store account nr as a key and account balance as value
    // HashMap<String, Double> accountBalanceMap = new HashMap<>();

    public static void main(String[] args) {
        String ANSI_BLACK_BACKGROUND = "\u001B[40m";
        String ANSI_RED_BACKGROUND = "\u001B[41m";
        String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
        String ANSI_WHITE_BACKGROUND = "\u001B[47m";
        String ANSI_BLUE_BACKGROUND = "\u001B[44m";
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_PURPLE = "\u001B[35m";
        String ANSI_CYAN = "\u001B[36m";
        String ANSI_WHITE = "\u001B[37m";
        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_PURPLE + "Tere tulemast panka!" + ANSI_RESET);
        System.out.println();
        String n1 = new String("Koll");
        String n2 = new String("Koll");
        String s1 = "Dingo";
        System.out.println(s1.substring(1,3));
        Scanner scanner = new Scanner(System.in);
        Map<String, Double> pank = new HashMap<>();

        while (true) {
            System.out.println(ANSI_PURPLE + "Palun sisestage järgmisele reale soovitud tegevus: create - getBalance - deposit - withdraw - transfer" + ANSI_RESET);

            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            String line = scanner.nextLine();

            if (line.equalsIgnoreCase("exit")) {
                break;
            }
            // TODO 1
            // Add command: "createAccount ${accountNr}"
            // this has to store accountNr with 0 balance
            else if (line.equalsIgnoreCase("create")) {
                System.out.println(ANSI_GREEN + "Sisestage järgmisele reale palun oma konto number: " + ANSI_RESET);
                String accountNumber = scanner.nextLine();  //sisestatakse oma konto number
                pank.put(accountNumber, (double) 0);
                System.out.println(pank);

            }
            // TODO 2
            // Add command: "getBalance ${accountNr}"
            // this has to display account balance of specific acount
            else if (line.equalsIgnoreCase("getBalance")) {
                System.out.println(ANSI_CYAN + "Sisestage järgmisele reale palun oma konto number: " + ANSI_RESET);
                String accountNumber = scanner.nextLine();
                if (pank.get(accountNumber) != null) {
                    System.out.println("Arvel on hetkel " + pank.get(accountNumber) + " EUR");
                    System.out.println(pank);
                } else {
                    System.out.println(ANSI_RED + "Sellist arvet ei eksisteeri. Vali createAccount!" + ANSI_RESET);
                }
            }
            // TODO 3
            // Add command: "depositMoney ${accountNr} ${amount}
            // this has to add specified amount of money to account
            // You have to check that amount is positive number
            else if (line.equalsIgnoreCase("deposit")) {
                System.out.println(ANSI_YELLOW + "Sisestage järgmisele reale palun oma konto number: " + ANSI_RESET);
                String accountNumber = scanner.nextLine();
                if (pank.get(accountNumber) != null) {
                    System.out.println("Sisestage järgmisele reale palun millise summa arvele panete: ");
                    Double depositAmount = scanner.nextDouble();
                    pank.put(accountNumber, depositAmount);
                    System.out.println(pank);
                    scanner.nextLine();   // hetketeooria on see, et kui on nextdouble siis ei loe scanner newline vaid see läheb järgmisse inputi mille tõttu
                    //tekkis lisaloop ja tagastas "unknown command" ühe korra alati
                    System.out.println("Uus summa väärtus on: " + depositAmount);
                } else {
                    System.out.println(ANSI_RED + "Sellist arvet ei eksisteeri. Vali createAccount!" + ANSI_RESET);
                }
            }

            // TODO 4
            // Add command: "withdrawMoney ${accountNr} ${amount}
            // This has to remove specified amount of money from account
            // You have to check that amount is positive number
            // You may not allow this transaction if account balance would become negative
            else if (line.equalsIgnoreCase("withdraw")) {
                System.out.println(ANSI_BLUE + "Sisestage järgmisele reale palun oma konto number: " + ANSI_RESET);
                String accountNumber = scanner.nextLine();
                if (pank.get(accountNumber) != null) {
                    System.out.println(pank);
                    System.out.println("Sisestage järgmisele reale palun millise summa välja võtate: ");
                    Double depositAmount = scanner.nextDouble();
                    Double newAmount = pank.get(accountNumber) - depositAmount;
                    scanner.nextLine();          // otsi teistest kommentaaridest seletust selle rea kohta
                    if (newAmount < 0) {
                        System.out.println("Nii suurt summat hetkel kontol ei ole!");
                    } else {
                        pank.put(accountNumber, newAmount);

                    }
                } else {
                    System.out.println(ANSI_RED + "Sellist arvet ei eksisteeri. Vali createAccount!" + ANSI_RESET);
                }
            }
            // TODO 5
            // Add command: "transfer ${fromAccount} ${toAccount} ${amount}
            // This has to remove specified amount from fromAccount and add it to toAccount
            // Your application needs to check that toAccount is positive
            // And from account has enough money to do that transaction

            else if (line.equalsIgnoreCase("transfer")) {
                System.out.println("Sisestage järgmisele reale palun esimese konto number: ");
                String accountNumber1 = scanner.nextLine();
                Double acc1 = pank.get(accountNumber1);

                System.out.println("Sisestage järgmisele reale palun millise summa te üle kannate: ");

                Double transferAmount = scanner.nextDouble();


                Double minusAmount = acc1 - transferAmount; //leiab summa algne miinus ülekantav summa


                if (minusAmount < 0) {
                    System.out.println("Nii suurt summat hetkel kontol ei ole!");
                }
                System.out.println("Sisestage järgmisele reale palun teise konto number: ");
                scanner.nextLine();
                String accnr2 = scanner.nextLine(); // googel ütleb midagi teemal, et nextint (ilmselt ka nextdouble) ei loe newline peale enterit ja seega nextline tagastab selle mis on
                // peale enterit ehk tühjus kuna ma löön enterit PEALE teise konto sisestamist?!??!?! selline teooria praegu
                System.out.println("acc nr 2 : " + accnr2);
                System.out.println("transfer amount : " + transferAmount);
                Double totalAmount = pank.get(accnr2) + transferAmount; //Teeb muutuja kus liidab teise arve peal olevale summale uue sisestatud summa.
                pank.put(accnr2, totalAmount);   //paneb liidetud summa mapi teise arve summaks
                pank.put(accountNumber1, minusAmount);   // paneb esimesele arvele uue summa millest maha võetud transfer summa
                System.out.println(pank);


            } else {
                System.out.println("Unknown command");

            }
        }
    }

}