package com.teamTwo;

import java.sql.*;


import static java.sql.DriverManager.*;

public class TrainDAO {
    private String Driver_NAME;
    private String DB_URL;
    private String USER_NAME;
    private String PASSWORD ;

    public TrainDAO() {
        Driver_NAME = "oracle.jdbc.driver.OracleDriver";
        this.DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
        this.USER_NAME = "hr";
        this.PASSWORD = "hr";
    }

    public static Train findTrain(int trainNo) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url1="jdbc:oracle:thin:@localhost:1521:xe";
            con = getConnection(url1,"hr","hr");
            String query1="select * from TRAINS where TRAIN_NO = ? ";
            pstmt = con.prepareStatement(query1);
            // TODO In the following statement 1st argument represent the
            // parameter number mentioned in the above statement with ?
            pstmt.setInt(1, trainNo);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                System.out.println(rs.getInt(1)+"...."+rs.getString(2)+"....."+rs.getString(3));
                String trainName = rs.getString(2);
                String source = rs.getString(3);
                String destination = rs.getString(4);
                int ticketPrice = rs.getInt(5);

                Train trainObj = new Train(trainNo, trainName, source, destination, ticketPrice);
                return trainObj;
            } else {
                return null;
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (SQLException sqe) {
            sqe.printStackTrace();
        } finally {
            rs.close();
            pstmt.close();
            con.close();
        }
        return null;
    }

}

