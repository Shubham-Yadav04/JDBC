import java.sql.*;

public class PreparedStatementPractice {
    private static String url="jdbc:mysql://127.0.0.1:3306/student";
    private static String username="root";
    private static String password="Shubham@2303";
    public static void main(String []args){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            System.out.println("class if not loaded an error has occurred :"+e.getMessage());
        }

        try{
            Connection connection = DriverManager.getConnection(url,username,password);

            // inserting value in the table

            String Query= "INSERT INTO stdtable VALUES (?,?,?)";
            PreparedStatement prepareStatement= connection.prepareStatement(Query);
            prepareStatement.setInt(1,22);
            prepareStatement.setString(2,"suhail");
            prepareStatement.setString(3,"Male");
//            int result=prepareStatement.executeUpdate();
//            if(result>0){
//                System.out.println("the query executed succesfully");
//
//            }
//            else{
//                System.out.println("the query is not executed");
//            }

            // deleting value using the prepareStatement
            String query="DELETE FROM stdtable WHERE student_Id=?";
            PreparedStatement prepareStatements= connection.prepareStatement(query);

            prepareStatements.setInt(1,3);
//
//            int result=prepareStatements.executeUpdate();
//            if(result>0){
//                System.out.println("the entered deletion query is executed ");
//            }
//            else{
//                System.out.println("the entered query does not executed");
//            }

            // upadation of value in the table
            String query2="UPDATE stdtable SET student_Id=? WHERE student_Id=?";
            PreparedStatement prepareStat= connection.prepareStatement(query2);
            prepareStat.setInt(1,25);
            prepareStat.setInt(2,22);
//            int result= prepareStat.executeUpdate();
//            if(result>0){
//                System.out.println("the value is updated ");
//
//            }
//            else{
//                System.out.println("the value is not updated");
//            }


            ResultSet result = prepareStatement.getResultSet();
            while(result.next()){
                String name =result.getString("std_Name");
                System.out.println("Student name:"+name);
            }


        }
        catch(SQLException sql){
            System.out.println("got a SQL error printing the program stack :"+sql.getStackTrace());
        }
    }
}
