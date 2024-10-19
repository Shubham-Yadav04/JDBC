
import java.sql.*;
public class StatementInterface {
    private static final  String url= "jdbc:mysql://127.0.0.1:3306/student";
    private static final String username="root";
    private static final String password= "Shubham@2303";
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch( ClassNotFoundException e){
            System.out.println( e.getMessage());
        }
        try{
            Connection connection= DriverManager.getConnection(url,username,password); // connection is used to establish a connection and get the statement instance which help us to execute the sql queries in database

            Statement st= connection.createStatement(); // used to execute the sql queries  for the specified connection instance


            String query="SELECT * FROM stdtable";
//            st.executeQuery(query);
            ResultSet result= st.getResultSet();

            // contains the result of the executed query by using the specified statement instance


//             getting the value from the database or accessing the values of database
//
//
//            while(result.next()){
//                int std_id= result.getInt("student_Id");
//                String name = result.getString("std_Name");
//                String gender= result.getString("Gender");
//                System.out.println("Student_Id :"+ std_id);
//                System.out.println("Name :"+name);
//                System.out.println("Gender :"+ gender);
//            }
// performing insertion , deletion , update of data using statement;
            Statement st2= connection.createStatement();

            // insertion of data into database


//             String query2 =String.format("INSERT INTO stdtable(student_Id,std_Name,Gender) VALUES (%d,'%s','%s')",65,"ishita","Female");

            // updating value of ishita studentid
            String query3=String.format("UPDATE stdtable SET student_Id= %d WHERE student_Id=%d",53,65);

//             deleting data from table
            String query4 = "DELETE FROM stdtable WHERE student_Id=53";
            int res= st2.executeUpdate(query4);
            if(res>0){
                System.out.println("data deleted successfully");
            }
            else{
                System.out.println("data not deleted ");
            }
        }
        catch( SQLException sql){
            sql.printStackTrace();
        }

    }
}
