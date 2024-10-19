import java.sql.*;
import java.util.Scanner;

public class BatchPratice {
    public static void main(String [] args){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            System.out.println("the class is not found");
        }
        try{
// establishing connection with my database
            String url="jdbc:mysql://127.0.0.1:3306/student";
            String username="root";
            String password="Shubham@2303";
            Connection connection= DriverManager.getConnection(url,username,password);
            // using Statement Interface
            Statement statement =connection.createStatement();
            String query1="INSERT INTO stdtable VALUES(?,?,?)";

            PreparedStatement preparedStatement= connection.prepareStatement(query1);
            while(true){
                Scanner sc= new Scanner(System.in);
                System.out.println("Enter the name :");
                String name= sc.nextLine();
                System.out.println("Enter the Gender");
                String Gender=sc.next();
                System.out.println("Enter the Student ID :");
                int Id=sc.nextInt();

                System.out.println("choose 'Y'/'N' to Enter more entery :");
                String chose=sc.next();

                // defining the query which we want to execute multiple time
                String query=  String.format("INSERT INTO stdtable VALUES (%d,'%s','%s')",Id,name,Gender);
                statement.addBatch(query);


                // using PrepareStatement

                preparedStatement.setInt(1,Id);
                preparedStatement.setString(2,name);
                preparedStatement.setString(3,Gender);
                preparedStatement.addBatch();
                if(chose.toUpperCase().equals("N")){

                    break;
                }
            }
           int result[]=preparedStatement.executeBatch();
            if(result.length>0){
                System.out.println("The query is executed");
            }
            else{
                System.out.println("the query is not executed");
            }
        }
        catch(SQLException sql){
            System.out.println("sqlexception has occurred");
        }
    }
}
