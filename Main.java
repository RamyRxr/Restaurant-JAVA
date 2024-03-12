import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    /*******************************************PrepareTheMenu*********************************************************/
    public static Menu[] PrepMenu(Scanner scan , int NumMeal){
        Menu[] menu = new Menu[NumMeal];
        for (int i = 0; i < NumMeal; i++) {
            System.out.println("What's the " + i + " Meal and how many do we have");
            String Meal = scan.nextLine();
            int num = Integer.parseInt(scan.nextLine());
            System.out.println("What's the Price");
            int Price = Integer.parseInt(scan.nextLine());

            Menu food = new Menu(Meal, num, Price);
            menu[i] = food;
        }
        for (int i=0 ; i<=10 ; i++){
            System.out.println();
        }
        System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();
        System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();
        System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();
        System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();
        System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();
    return menu;
    }
    /*******************************************PrintTheMenu***********************************************************/
    public static void PrntMenu(Menu[] menu , int NumMeal){
        int maxMealLength = 0;
        for (int i = 0; i < NumMeal; i++) {
            int mealLength = menu[i].Meal.length();
            if (mealLength > maxMealLength) {
                maxMealLength = mealLength;
            }
        }

        int mealColumn= maxMealLength + 6;
        int priceColumn = 15;

        System.out.println("=====================================");
        System.out.println("|| " + formatString("Menu", mealColumn) + " || "
                                + formatString("Price", priceColumn) + " ||");
        System.out.println("=====================================");
        for (int i = 0; i < NumMeal; i++) {
            System.out.println("|| " + formatString(menu[i].Meal, mealColumn) + " || "
                                    + formatString(menu[i].Price + " ", priceColumn) + " ||");
            System.out.println("=====================================");
        }
    }
    /*********************************************FormationString******************************************************/
    public static String formatString(String input, int width) {
        if (input.length() > width) {
            return input.substring(0, width);
        } else {
            return String.format("%-" + width + "s", input);
        }
    }

    /*********************************************TakeOrder************************************************************/
    public static void TakeOrder (Scanner scan , int NumMeal , Menu[] menu){
        boolean RP = false ;
        boolean TF = true;
        float TotalPrice = 0;

        System.out.println("============================Our Restaurant is the best in the world============================");
        System.out.println("============================This is our MENU============================");

        while (TF) {
            PrntMenu(menu, NumMeal);
            System.out.println("============================What do you want to order============================");

            System.out.println("Pick the number of the Meal");
            int W = Integer.parseInt(scan.nextLine());

            if (menu[W].num == 0) {
                System.out.println("Sorry, this meal is over");
            } else {
                menu[W].num = menu[W].num - 1;
                TotalPrice = TotalPrice + menu[W].Price;
            }
            System.out.println("you want anything else");
            System.out.println("[Y] for yes / [N] for no");
            char inswer = scan.next().charAt(0);
            scan.nextLine();
            if (inswer == 'N'){
                System.out.println("The Totale Price is : " + TotalPrice);
                System.out.println("Please pay here");
                float Price = Float.parseFloat(scan.nextLine());


                while (TotalPrice != 0 && Price >= TotalPrice) {
                    if (Price <= TotalPrice) {
                        TotalPrice = TotalPrice - Price;
                        System.out.println("The amount is incomplete !.");
                        System.out.println("You still have to pay the rest.");
                        System.out.println("Rest = " + TotalPrice);
                        Price = Float.parseFloat(scan.nextLine());
                    }else if (Price >= TotalPrice){
                        Price = Price - TotalPrice;
                        System.out.println("Thank you for eat here.");
                        System.out.println("Your rest money = " + Price);
                    }else {
                        System.out.println("Thank you for eat here.");
                    }
                }
                TF = false;
            }else if(inswer == 'Y'){
                TF = true;
            }
        }
    }
    /**************************************************CloserNot*******************************************************/
    public static boolean CloseOROpen (Menu [] menu , int NumMeal){
        int Num = 0;
        for (int i=0 ; i < NumMeal ; i++){
            Num = Num + menu[i].num;
        }
        if (Num == 0){
            return true;
        }
        return  false;
    }
    /****************************************************Main**********************************************************/

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        boolean Close = false;
        System.out.println("Welcome back, sir. What's the menu for today?");
        System.out.println("How many meals do we have today");
        int NumMeal = Integer.parseInt(scan.nextLine());
        Menu[] menu = PrepMenu(scan, NumMeal);

        while (!Close) {
            System.out.println("==============================Welcome in our Restaurant==============================");
            TakeOrder(scan, NumMeal, menu);
            Close = CloseOROpen(menu , NumMeal);
        }
        System.out.println("Sorry, all food and meals are over");
        scan.close();
    }
}