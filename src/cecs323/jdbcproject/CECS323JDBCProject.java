/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cecs323.jdbcproject;

/**
 *
 * @author Sotheanith
 */
import java.sql.*;
import java.util.Scanner;
import java.util.*;
public class CECS323JDBCProject {


    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input= new Scanner(System.in);
        boolean done=false;
        do{
            int choice= menu(input);
            switch(choice){
                case 1:
                    System.out.println("-Listing All Writing Groups-");
                    break;
                case 2:
                    System.out.println("-Listing all the data for a group-");
                    break;
                case 3:
                    System.out.println("-Listing all publishers-");
                    break;
                case 4:
                    System.out.println("-Listing all the data for publisher-");
                    break;
                case 5:
                    System.out.println("-Listing all book titles-");
                    break;
                case 6:
                    System.out.println("-Listing all the data for a book-");
                    break;
                case 7:
                    System.out.println("-Inserting a new book-");
                    break;
                case 8:
                    System.out.println("-Inserting a new publisher-");
                    break;
                case 9:
                    System.out.println("-Removing a book-");
                    break;
                case 10:
                    System.out.println("-Exiting-");
                    done=true;
                    break;
                default:
                    System.out.println("-Invalid Input-");
                    break;        
            }
        }while(!done);
        
    }
    
    
    //Need menu and input validation
    public static int menu(Scanner input){
        boolean done=false;
        int choice = 0; 
        System.out.println("--Menu--");
        System.out.println("1. List all writing groups ");
        System.out.println("2. List all the data for a group");
        System.out.println("3. List all publishers");
        System.out.println("4. List all the data for a publisher");
        System.out.println("5. List all book titles");
        System.out.println("6. List all the data for a book");
        System.out.println("7. Insert a new book");
        System.out.println("8. Insert a new publisher");
        System.out.println("9. Remove a book");
        System.out.println("10. Exit");
        while(!done){
            try{
                choice=input.nextInt();
                if (!(choice>=1&&choice<=10)){
                    throw new NumberFormatException();
                }
                done=true;
            }catch(InputMismatchException ime){
                input.next();
                System.out.print("Invalid Input. Re-enter: ");
            }catch(NumberFormatException nfe){
                System.out.print("Invalid Input. Re-enter: ");
            } 
        }
        return choice;
    }
}
