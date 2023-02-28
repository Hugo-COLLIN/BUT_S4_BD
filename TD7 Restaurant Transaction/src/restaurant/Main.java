package restaurant;

import java.sql.*;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AccessData ad = new AccessData();
        System.out.println(ad.connection(args[0], args[1]));
        Scanner sc = new Scanner(System.in);

        int logged = 0;

        while (logged == 0)
        {
            System.out.println("Please enter your login and password to connect");
            System.out.print("Login: ");
            String login = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();
            logged = ad.loginUser(login, password);
        }
        System.out.println(logged);

        boolean end = false;
        sc = new Scanner(System.in);
        while (!end)
        {
            System.out.println("\nPlease choose an action :\n1. List of available tables for a specific date and hour\n" +
                    "2. Book a table\n3. List of available meals\n4. Order meals");
            if (logged == 2)
                System.out.println("5. Manage waiters\n6. Assign server to table\n7. Manage meals\n8. Order amount\n" +
                        "9. Turnover + orders by waiter\n10. List of waiters who achived no turnover during a period\n0. Exit");
            System.out.print(">> ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice > 10 || (logged < 2 && choice > 4))
                continue;

            String p1,p2,p3, p4;
            boolean b1;
            int i1, i2, i3, i4;
            switch (choice)
            {
                case 1:
                    System.out.print("Date (DD/MM/YYYY): ");
                    p1 = sc.nextLine();
                    System.out.print("Time (HH:MI): ");
                    p2 = sc.nextLine();
                    System.out.println("\n" + ad.listTables(p1,p2));
                    break;
                case 2:
                    System.out.print("Table number: ");
                    p1 = sc.nextLine();
                    System.out.print("Date (DD/MM/YYYY): ");
                    p2 = sc.nextLine();
                    System.out.print("Time (HH:MI): ");
                    p3 = sc.nextLine();
                    System.out.print("Number of people: ");
                    p4 = sc.nextLine();
                    System.out.println("\n" + ad.bookTable(p1,p2,p3,p4));
                    break;
                case 3:
                    System.out.println(ad.listBookings());
                    break;
                case 4:
                    System.out.println(ad.listBookings());
                    System.out.print("Booking number: ");
                    i1 = sc.nextInt();
                    System.out.println(ad.listMeals());
                    System.out.print("Meal number: ");
                    i2 = sc.nextInt();
                    System.out.print("Quantity: ");
                    i3 = sc.nextInt();
                    System.out.println("\n" + ad.orderMeal(i1,i2,i3));
                    break;
                case 5:
                    System.out.println(ad.listWaiters());
                    System.out.print("Type waiter number to update it or -1 to add a waiter\n>>");
                    i1 = sc.nextInt();
                    sc.nextLine();
                    if (i1 == -1)
                    {
                        System.out.print("Name: ");
                        p1 = sc.nextLine();
                        System.out.print("Email: ");
                        p2 = sc.nextLine();
                        System.out.print("Password: ");
                        p3 = sc.nextLine();
                        System.out.println("\n" + ad.newWaiter(p1,p2,p3));
                    }
                    else
                    {
                        String res = ad.selectWaiter(i1);
                        System.out.println(res);
                        if (res.equals("Waiter not found")) continue;
                        System.out.print("1. Name\n2. Email\n3. Password\n>>");
                        i2 = sc.nextInt();
                        sc.nextLine();
                        String[] tab = {"Name", "Email", "Password"};

                        if (i2 > 0 && i2 < 4) {
                            System.out.print(tab[i2-1] + ": ");
                            p1 = sc.nextLine();
                            System.out.println("\n" + ad.updateWaiter(i1, i2, p1));
                        }
                    }
                    System.out.println("Name: ");
                    //System.out.println("\n" + ad.cliList2Models());
                    break;
                case 0:
                    end = true;
                    break;
                default:
                    break;
            }
            System.out.println("Press any key to continue...");
            sc.nextLine();
        }

    }

}
