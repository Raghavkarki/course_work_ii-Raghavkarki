package coursework2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class AddBooks {
    public static void main(String[] args) {
        bookSt Log = new bookSt();
    }
}

class bookSt {
    bookSt() {
        JFrame f = new JFrame("Login");

        JLabel lbName, laName, lpDate, lField;
        JTextField tfbName, tfaName, tfpDate, tfField;
        JButton btnSave, btnExit, btnView;

        // user_label
        lbName = new JLabel("Book Name:");
        f.add(lbName);
        lbName.setBounds(100, 100, 100, 30);

        laName = new JLabel("Publisher Name:");
        f.add(laName);
        laName.setBounds(100, 100, 100, 100);

        lpDate = new JLabel("Publish Date :");
        f.add(lpDate);
        lpDate.setBounds(100, 100, 100, 170);

        lField = new JLabel("Field");
        f.add(lField);
        lField.setBounds(100, 100, 100, 250);

        JLabel lQuantity = new JLabel("Quantity");
        f.add(lQuantity);
        lQuantity.setBounds(100, 100, 100, 340);

        tfbName = new JTextField(30);
        f.add(tfbName);
        tfbName.setBounds(200, 106, 160, 20);

        tfaName = new JTextField(10);
        f.add(tfaName);
        tfaName.setBounds(200, 145, 160, 20);

        tfpDate = new JTextField(30);
        f.add(tfpDate);
        tfpDate.setBounds(200, 180, 160, 20);

        tfField = new JTextField(30);
        f.add(tfField);
        tfField.setBounds(200, 220, 160, 20);

        JTextField tfQuantity = new JTextField(30);
        f.add(tfQuantity);
        tfQuantity.setBounds(200, 260, 160, 20);

        btnSave = new JButton("Save");
        f.add(btnSave);
        btnSave.setBounds(200, 300, 150, 30);

        btnExit = new JButton("Exit");
        f.add(btnExit);
        btnExit.setBounds(200, 350, 150, 30);

        btnView = new JButton("View Books");
        f.add(btnView);
        btnView.setBounds(200, 400, 150, 30);

        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new View();
                f.dispose();

            }
        });

        btnExit.addActionListener((ActionListener) new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int select = JOptionPane.showConfirmDialog(btnExit, "Do you want to Exit");

                if (select == 0) {
                    new Login();
                    f.dispose();
                }
            }
        });

        // save action
        btnSave.addActionListener(e -> {
            String BName = tfbName.getText();
            String PName = tfaName.getText();
            String Date = tfpDate.getText();
            String Field = tfField.getText();
            String Quantity = tfQuantity.getText();
            try {
                ConnectionC db = new ConnectionC();
                 
                    if (BName.equals(null) || BName.equals("")) {
                        JOptionPane.showMessageDialog(f, "Please enter book name", "Invalid",
                                JOptionPane.WARNING_MESSAGE);
                        tfbName.requestFocus();

                    } else if (PName.equals(null) || PName.equals("")) {
                        JOptionPane.showMessageDialog(f, "Please enter publisher name ", "Invalid",
                                JOptionPane.WARNING_MESSAGE);
                    } 
                    else if (!Date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    	JOptionPane.showMessageDialog(f, "Please enter correct date fromat (yyyy-mm-dd) ", "Invalid",
                                JOptionPane.WARNING_MESSAGE);
                    } else if (Field.equals(null) || Field.equals("")) {
                        JOptionPane.showMessageDialog(f, "Please enter field ", "Invalid",
                                JOptionPane.WARNING_MESSAGE);
                    } else if (Quantity.equals(null) || Quantity.equals("")) {
                        JOptionPane.showMessageDialog(f, "Please enter quantity ", "Invalid",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    
                    
                    else {
                    	String query1 = "Select * from books where book_name='" + BName + "'";
                        ResultSet result = db.connection().executeQuery(query1);
                        if (result.next()) {
                            JOptionPane.showMessageDialog(btnSave, "Book Already Exist");
                        } 
                        else {
                        	String query = "insert into books(book_name,publisher_name,publish_date,field,quantity) values('"
                                  + BName + "','" + PName + "','" + Date + "','" + Field + "','" + Quantity + "') ";
                          System.out.println(query);
                          int result1 = db.connection().executeUpdate(query);
                          if (result1 > 0) {
                              JOptionPane.showMessageDialog(btnSave, "Book Added");
                              System.out.println(result1);
                          }
                        }                     

                    
                }

            } catch (SQLException e1) {

                e1.printStackTrace();
            }

        });

        f.setLayout(null);
        f.setSize(600, 600);
        f.setVisible(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    }
}
