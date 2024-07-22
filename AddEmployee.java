import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class AddEmployee extends JFrame
{
Container c;
JLabel labEid , labEname , labsalary;
JTextField txtEid , txtEname, txtsalary ; 
JButton btnsave , btnback;


AddEmployee()
{
c = getContentPane();
FlowLayout f1 = new FlowLayout(FlowLayout.CENTER, 40 ,35);
c.setLayout(f1);
Font f= new Font("calibri ", Font.BOLD,30);

labEid = new JLabel("Enter Employee id");
txtEid = new JTextField(20);
labEname  = new JLabel("Enter Employee name ");
txtEname = new JTextField(20);
labsalary =new JLabel("Enter Employee salary");
txtsalary = new JTextField(20);
btnsave = new JButton("save");
btnback = new JButton("back");


labEid.setFont(f);
txtEid.setFont(f);
labEname.setFont(f);
txtEname.setFont(f);
labsalary.setFont(f);
txtsalary.setFont(f);
btnsave.setFont(f);
btnback.setFont(f);


c.add(labEid);
c.add(txtEid);
c.add(labEname);
c.add(txtEname);
c.add(labsalary);
c.add(txtsalary);
c.add(btnsave);
c.add(btnback);

ActionListener a1 = (ae) ->{
Home h = new Home();
dispose();
};
btnback.addActionListener(a1);


ActionListener a9 = (ae) ->{
try
{
DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
String url = "jdbc:mysql://localhost:3306/ems";
Connection con = DriverManager.getConnection(url , "root" , "abc123");
String sql = "insert into Employee values(?,?,?)";
PreparedStatement pst = con.prepareStatement(sql);


if (txtEid.getText().isEmpty() || txtEname.getText().isEmpty() || txtsalary.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(c, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
int eid;
                try {
                    eid = Integer.parseInt(txtEid.getText());
                    if (eid <= 0) {
                        JOptionPane.showMessageDialog(c, "Employee ID must be a positive integer", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(c, "Invalid Employee ID format", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String ename = txtEname.getText();
                if (!ename.matches("^[a-zA-Z]+$")) {
                    JOptionPane.showMessageDialog(c, "Invalid name. Name should contain only alphabets", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double salary;
                try {
                    salary = Double.parseDouble(txtsalary.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(c, "Invalid salary format", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

pst.setInt(1,eid);
pst.setString(2 , ename);
pst.setDouble(3 , salary);
pst.executeUpdate();
JOptionPane.showMessageDialog(c,"record created");
txtEid.setText("");
txtEname.setText("");
txtsalary.setText("");
txtEid.requestFocus();
con.close();
}
catch(SQLException e)
{
JOptionPane.showMessageDialog(c ,"issue" +e);
}
};
btnsave.addActionListener(a9);


	setTitle("Employee Management System By Atharv Mejari");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        c.setBackground(new Color(230, 230, 250));
}
}