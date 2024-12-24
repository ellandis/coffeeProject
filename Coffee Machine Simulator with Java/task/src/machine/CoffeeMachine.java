package machine;

import com.sun.source.tree.WhileLoopTree;
import org.cef.handler.CefLifeSpanHandler;

import javax.swing.*;
import java.util.Objects;
import java.util.Scanner;

public class CoffeeMachine {
    private int wtrTray = 400;
    private int mlkTray = 540;
    private int cofeTray = 120;
    private int numCups = 9;
    private int cashOnHand = 550;
    private int cupsServed = 0;
    private boolean needCleaning = false;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine();
        String action = "";
        boolean open = true;
        while(open){
            System.out.println("Write action (buy, fill, take, clean, remaining, exit):");
            action = scanner.next();
            if(machine.cupsServed > 9){
                if(Objects.equals(action, "clean")){
                    machine.checkAction(action);
                }
                else {
                    System.out.println("I need cleaning!");
                }
            }
            else{

                if(Objects.equals(action, "exit")){
                    open = false;
                }
                else {
                    machine.checkAction(action);
                    open = true;
                }
            }
        }
    }

    public void checkCleaning(){
        if(this.cupsServed > 9){
            this.needCleaning = true;
//            System.out.println("I need cleaning!");
        }
    }
    public void checkAction(String act){


        if(Objects.equals(act, "buy")){
            System.out.print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino,  back - to main menu:\n");
            var coffeeItem = scanner.next();
            if(needCleaning == true){
                this.checkCleaning();
                System.out.println("I need cleaning!");
            }
            else {
                coffeeSale(coffeeItem);
            }
        }
        else if (Objects.equals(act, "fill")) {
            fillCoffee();
        }
        else if (Objects.equals(act, "take")){
            takeMoney();
        }
        else if (Objects.equals(act,"remaining")) {
            display();
        }
        else if (Objects.equals(act, "clean")) {
            cleanIt();
        }
        else if(Objects.equals(act,"back")){
            return;
        }
    }




    public void coffeeSale(String coffee){
        if(Objects.equals(coffee, "1")){
            if(this.wtrTray < 250){
                System.out.println("Sorry, not enough water!");
            }
            else if(this.cofeTray < 16){
                System.out.println("Sorry, not enough coffee beans!");
            }
            else{
                sale1();
            }
        }
        else if(Objects.equals(coffee, "2")){
            if(this.wtrTray < 350){
                System.out.println("Sorry, not enough water!");
            }
            else if(this.mlkTray < 75){
                System.out.println("Sorry, not enough milk!");
            }
            else if(this.cofeTray < 20){
                System.out.println("Sorry, not enough coffee beans!");
            }
            else{
                sale2();
            }
        }
        else if(Objects.equals(coffee, "3")) {
            if(this.wtrTray < 200){
                System.out.println("Sorry, not enough water!");
            }
            else if(this.mlkTray < 100){
                System.out.println("Sorry, not enough milk!");
            }
            else if(this.cofeTray < 12){
                System.out.println("Sorry, not enough coffee beans!");
            }
            else{
                sale3();
            }
        }
    }

    public void cleanIt(){
        this.cupsServed = 0;
        this.needCleaning = false;
        System.out.println("I have been cleaned!");
    }

    public void sale1() {
        this.wtrTray -= 250;
        this.cofeTray -= 16;
        this.numCups -= 1;
        this.cashOnHand += 4;
        this.cupsServed++;
        System.out.println("I have enough resources, making you a coffee!");
    }
    public void sale2(){
        this.wtrTray -= 350;
        this.mlkTray -= 75;
        this.cofeTray -= 20;
        this.numCups -= 1;
        this.cashOnHand += 7;
        this.cupsServed++;
        System.out.println("I have enough resources, making you a coffee!");
    }
    public void sale3(){
        this.wtrTray -= 200;
        this.mlkTray -= 100;
        this.cofeTray -= 12;
        this.numCups -= 1;
        this.cashOnHand += 6;
        this.cupsServed++;
        System.out.println("I have enough resources, making you a coffee!");
    }



    public void fillCoffee(){
        int amount;
        System.out.println("Write how many ml of water you want to add:");
        amount = scanner.nextInt();
        this.wtrTray += amount;
        System.out.println("Write how many ml of milk you want to add:");
        amount = scanner.nextInt();
        this.mlkTray += amount;
        System.out.println("Write how many grams of coffee beans you want to add:");
        amount = scanner.nextInt();
        this.cofeTray += amount;
        System.out.println("Write how many disposable cups you want to add:");
        amount = scanner.nextInt();
        this.numCups += amount;
//        System.out.println("\n");
    }

    public void takeMoney(){
        System.out.println("I gave you $" + this.cashOnHand +"");
        this.cashOnHand = 0;
    }

    public void display(){
        System.out.println( "The coffee machine has:\n" +
                this.wtrTray + " ml of water\n" +
                this.mlkTray + " ml of milk\n" +
                this.cofeTray + " g of coffee beans\n" +
                this.numCups + " disposable cups\n" +
                "$" + this.cashOnHand + " of money\n");
    }

    @Override
    public String toString() {
        return "The coffee machine has:\n" +
                this.wtrTray + " ml of water\n" +
                this.mlkTray + " ml of milk\n" +
                this.cofeTray + " g of coffee beans\n" +
                this.numCups + " disposable cups\n" +
                "$" + this.cashOnHand + " of money\n";
    }
}