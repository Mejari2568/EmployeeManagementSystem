import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class DeleteEmployee extends JFrame
{
Container c;
JLabel labid ;
JTextField  txtEid ;
JButton btndel , btnback;

DeleteEmployee()
{
c = getContentPane();
FlowLayout f1 = new FlowLayout(FlowLayout.CENTER, 40 ,35);
c.setLayout(f1);
Font f= new Font("calibri ", Font.BOLD,30);

labid = new JLabel("Enter Employee id to Delete");
txtEid = new JTextField(20);
btndel = new JButton("Delete");
btnback = new JButton("back");

labid.setFont(f);
txtEid.setFont(f);
btndel.setFont(f);
btnback.setFont(f);

c.add(labid);
c.add(txtEid);
c.add(btndel);
c.add(btnback);

ActionListener a8= (ae) ->{
	Home h = new Home();
	dispose();
	};
	btnback.addActionListener(a8);

ActionListener a11 = (ae) -> {
            try {
                if (txtEid.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(c, "Employee ID is required", "Error", JOptionPane.ERROR_MESSAGE);
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

                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

                String url = "jdbc:mysql://localhost:3306/ems";
                Connection con = DriverManager.getConnection(url, "root", "abc123");

                String checkIdSql = "SELECT * FROM employee WHERE id = ?";
                PreparedStatement checkIdPst = con.prepareStatement(checkIdSql);
                checkIdPst.setInt(1, eid);
                ResultSet idResult = checkIdPst.executeQuery();

                if (!idResult.next()) {
                    JOptionPane.showMessageDialog(c, "Employee with ID " + eid + " does not exist", "Error", JOptionPane.ERROR_MESSAGE);
                    con.close();
                    return;
                }

                String sql = "DELETE FROM employee WHERE id = ?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, eid);
                int rowsDeleted = pst.executeUpdate();

                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(c, "Record deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(c, "No changes made. Record already deleted");
                }

                txtEid.setText("");
                txtEid.requestFocus();
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(c, "Issue: " + e, "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(c, "Invalid employee ID format", "Error", JOptionPane.ERROR_MESSAGE);
            }
        };
        btndel.addActionListener(a11);



setTitle("Employee Management System By Atharv Mejari");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        c.setBackground(new Color(230, 230, 250));


}
}