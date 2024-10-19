import java.sql.*;
import java.util.Scanner;

public class Transanction {
    public static void main(String []args){
        try{
            // loading driver manager class
            Class.forName("com.mysql.cj.jdbc.Driver");

        }
        catch(ClassNotFoundException e){
            System.out.println("class is not loaded");
        }
        // establishing connection with the database
        try{
            String url="jdbc:mysql://127.0.0.1:3306/LENDEN";
            String username="root";
            String password="Shubham@2303";
            Connection connection= DriverManager.getConnection(url,username,password);
            connection.setAutoCommit(false);
            String debit_query="UPDATE AccountStatement SET BALANCE = BALANCE -? WHERE Account_No=?";
            String credit_query="UPDATE AccountStatement SET BALANCE = BALANCE +? WHERE Account_No=?";

            // taking input the amount and the debit and credit account no;
            Scanner sc = new Scanner(System.in);
            System.out.println("enter the amount :");
            double amount = sc.nextDouble();
            System.out.println("enter the credit account number :");
            int creditAcc=sc.nextInt();
            System.out.println("enter the debit account number:");
            int debitAcc=sc.nextInt();

            PreparedStatement debit= connection.prepareStatement(debit_query);
            PreparedStatement credit= connection.prepareStatement(credit_query);
            debit.setDouble(1,amount);
            debit.setInt(2,debitAcc);
            credit.setDouble(1,amount);
            credit.setInt(2,creditAcc);
            int result=credit.executeUpdate();
            int result2=debit.executeUpdate();

            if(check(connection, amount,debitAcc)){
                connection.commit();
                System.out.println("transaction completed successfully");
            }
            else{
                connection.rollback();
                System.out.println("sorry insufficient amount");
            }
        }
        catch(SQLException sql){
            System.out.println("sql exception has occurred");
        }
    }
    public static boolean check(Connection connection, double amount,int Account_No){
        try {
            String query = "SELECT BALANCE FROM AccountStatement WHERE Account_No=?";
            PreparedStatement prep = connection.prepareStatement(query);
            prep.setInt(1,Account_No);
            ResultSet result= prep.executeQuery();

            if(result.next()){
                double actualbalance =result.getDouble("BALANCE");
            if(actualbalance> amount){
                return true;
            }

        }}
        catch (SQLException e){
            System.out.println("there is a sql Exception");
        }
        return false;
    }
}
