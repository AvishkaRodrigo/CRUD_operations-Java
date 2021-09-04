/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DELL
 */

import javax.swing.text.View;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DBclass {
    public static Connection getDBConnection()throws Exception{
        final String BDURL = "jdbc:mysql://localhost:3306/databasename";                                                // databasename is your database name
        final String USERNAME = "root";
        final String PASSWORD = "";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn= DriverManager.getConnection(BDURL,USERNAME,PASSWORD);
        return conn;
        
    }
    
    public static void InsertData (int index,String value2,int value3,int value4) throws Exception{
        Connection idc = getDBConnection();
        Statement stmt = idc.createStatement ();
        String sql = "insert into tablename (attribute1,attribute2,attribute3,attribute4) values('"+index+"','"+value2+"','"+value3+"','"+value4+"')";
        stmt.executeUpdate(sql);                                                                                        // enter values according to your database
        idc.close();
        
    }
    
    public static void ViewData() throws Exception{
        Connection c = getDBConnection();
        Statement stmt = c.createStatement();
        
        String sql = "Select * From tablename";                                                                         //table name should be a name of a table which is in your database
        ResultSet rs = stmt.executeQuery(sql);
        
        while(rs.next()){
            System.out.println("column1 : "+rs.getInt("attribute1")+" | column2 : "+rs.getInt("attribute2")+" | column3 :"+rs.getInt("attribute3")+" | column4 : "+rs.getInt("attribute4"));
        }                                                                                                               // attributes names should be the column labels of the table in your table
        c.close();
        
    }

    public static void UpdateData(int index,String value2,int value3,int value4) throws Exception{
        Connection c = getDBConnection();
        Statement stmt = c.createStatement();

        String sql = "UPDATE tablename SET attribute2 = '"+value2+"' ,attribute3 = '"+value3+"' ,attribute4 = '"+value4+"' WHERE tablename.attribute1 = '"+index+"'";
        stmt.executeUpdate(sql);
        ViewData();                                                                                                     // view data after updation
        c.close();
    }

    public static void DeleteData (int index) throws Exception{
        Connection c = getDBConnection();
        Statement stmt = c.createStatement();

        String sql = "DELETE FROM tablename WHERE tablename.attribute1 ='"+index+"'";
        stmt.executeUpdate(sql);
        ViewData();                                                                                                     // view data after deleting
        c.close();
    }
    
    public static void performAction (int choice){

        int attribute1 = 0;
        String attribute2 = null;
        int attribute3 = 0;
        int attribute4 = 0;

        try {
            switch (choice){
                case 1:
                    System.out.println("-----------------Insert data-----------------");
                    Scanner sc1 = new Scanner(System.in);
                    System.out.print("Attribute 1 (Integer) :");
                    attribute1 = sc1.nextInt();

                    System.out.print("Attribute 2 (String):");
                    Scanner sc11 = new Scanner(System.in);
                    attribute2 = sc11.nextLine();
                    System.out.print("Attribute 3 (Integer) :");
                    attribute3 = sc1.nextInt();
                    System.out.print("Attribute 4 (Integer) :");
                    attribute4 = sc1.nextInt();

                    try {
                        System.out.println("Successfully entered data");
                        InsertData(attribute1, attribute2, attribute3, attribute4);
                    }catch(Exception insertdata){
                        System.out.println("Error inserting data");
                        System.out.println(insertdata);
                    }
                    break;

                case 2:
                    try{
                        ViewData();
                    }catch (Exception viewdata){
                        System.out.println("Error"+viewdata);
                    }
                    break;

                case 3:
                    System.out.println("-----------------Update data-----------------");
                    Scanner sc2 = new Scanner(System.in);
                    System.out.print("Attribute 1 (Integer) :");
                    attribute1 = sc2.nextInt();
                    System.out.print("Attribute 2 (String):");
                    Scanner sc22 = new Scanner(System.in);
                    attribute2 = sc22.nextLine();
                    System.out.print("Attribute 3 (Integer) :");
                    attribute3 = sc2.nextInt();
                    System.out.print("Attribute 4 (Integer) :");
                    attribute4 = sc2.nextInt();

                    try{
                        UpdateData(attribute1, attribute2, attribute3, attribute4);
                    }catch (Exception updatedata){
                        System.out.println(updatedata);
                    }
                    break;
                case 4:
                    System.out.println("-----------------Delete data-----------------");
                    Scanner sc3 = new Scanner(System.in);
                    System.out.print("Attribute 1 (Integer) :");
                    attribute1 = sc3.nextInt();
                    try{
                        DeleteData(attribute1);
                    }catch (Exception deletedata){
                        System.out.println(deletedata);
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Enter Valid number!!!!!\n1 - Enter data \n 2 - View data \n 3 - Update data \n 4 - Delete data \n");
            }
        }catch (Exception performaction){
            System.out.println(performaction);
        }



    }


    
    
    public static void main(String[] args) {

        System.out.println("++++++++++Welcome to the sample database CRUD operation programme++++++++++\n\n");
        System.out.println(" 1 - Enter data \n 2 - View data \n 3 - Update data \n 4 - Delete data \n 0 - Exit");
        int choice = -1;
        Scanner userChoice = new Scanner(System.in);

        while (choice != 0){
            System.out.print("Enter your selection : ");
            choice = userChoice.nextInt();
            performAction(choice);
        }


    }
    
}
