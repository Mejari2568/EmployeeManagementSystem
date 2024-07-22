import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Home extends JFrame {
    Container c;
    JLabel labHeader; 
    JButton btnAdd, btnView, btnUpdate, btnDelete;

    Home() {
        c = getContentPane();
        FlowLayout f1 = new FlowLayout(FlowLayout.CENTER, 30, 30);
        c.setLayout(f1);
        Font f = new Font("Calibri", Font.BOLD, 40);
        labHeader = new JLabel("Employee Mangement System");
        btnAdd = new JButton("Add Employee");
        btnView = new JButton("View Employee");
        btnUpdate = new JButton("Update Employee");
        btnDelete = new JButton("Delete Employee");

        labHeader.setFont(new Font("Calibri", Font.BOLD, 50)); 
        btnAdd.setFont(f);
        btnView.setFont(f);
        btnUpdate.setFont(f);
        btnDelete.setFont(f);

        c.add(labHeader); 
        c.add(btnAdd);
        c.add(btnView);
        c.add(btnUpdate);
        c.add(btnDelete);

	ActionListener a3 = (ae) ->{
	AddEmployee a = new AddEmployee();
	dispose();
	};
	btnAdd.addActionListener(a3);

	ActionListener a2 = (ae) ->{
	ViewEmployee v = new ViewEmployee();
	dispose();
	};
	btnView.addActionListener(a2);

	ActionListener a6 = (ae) ->{
	UpdateEmployee u = new UpdateEmployee();
	dispose();
	};
	btnUpdate.addActionListener(a6);

	ActionListener a7 = (ae) ->{
	DeleteEmployee u = new DeleteEmployee();
	dispose();
	};
	btnDelete.addActionListener(a7);

        setTitle("Employee Management System By Atharv Mejari");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        c.setBackground(new Color(230, 230, 250));
    }

   
}
