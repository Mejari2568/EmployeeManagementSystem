import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class ViewEmployee extends JFrame
{
Container c;
TextArea taData;
JButton btnBack;

ViewEmployee()
{
c = getContentPane();
FlowLayout f1 = new FlowLayout(FlowLayout.CENTER,30 ,30);
c.setLayout(f1);
Font f = new Font("Calibri " , Font.BOLD,30);
taData = new TextArea(10 ,20);
btnBack = new JButton("Back");
taData.setEditable(false);

taData.setFont(f);
btnBack.setFont(f);


	


c.add(taData);
c.add(btnBack);

try
{
DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
String url = "jdbc:mysql://localhost:3306/ems";
Connection con = DriverManager.getConnection(url, "root", "abc123");

String sql = "select * from employee";
PreparedStatement pst = con.prepareStatement(sql);
ResultSet rs = pst.executeQuery();

while (rs.next()) {
int eid = rs.getInt("id");
String ename = rs.getString("name");
double salary = rs.getDouble("salary");
taData.append(eid + "   " + ename + "   " + salary + "\n");
}
con.close();

}
catch(SQLException e)
{
JOptionPane.showMessageDialog(c, "issue" +e);

}

ActionListener a4 = (ae) ->{
	Home h = new Home();
	dispose();
	};
	btnBack.addActionListener(a4);



	setTitle("Employee Management System By Atharv Mejari");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        c.setBackground(new Color(230, 230, 250));



}
}