package com.Panos.AssignmentFinal;

//import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.SwingConstants;

public class FrmLibraryInsert extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final JTextField textFieldLibraryName;
    final JTextField textFieldAddress;
    final JTextField textFieldLibraryID;
    private JTextField textFieldPhone;

    public FrmLibraryInsert() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/aueb.jpg")));
        this.setTitle("Add a New Library in the Database");
        this.setBackground(SystemColor.activeCaption);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 598, 365);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 255, 255));
        contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Event Handler for Window Activated including
        // when Window is Opened
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                textFieldLibraryID.setText("");
                textFieldLibraryName.setText("");
                textFieldAddress.setText("");
            }
        });

        JLabel lblLibraryID = new JLabel("ID");
        lblLibraryID.setHorizontalAlignment(SwingConstants.RIGHT);
        lblLibraryID.setForeground(new Color(153, 0, 0));
        lblLibraryID.setFont(new Font("Malgun Gothic", Font.BOLD, 16));
        lblLibraryID.setBounds(68, 39, 60, 25);
        contentPane.add(lblLibraryID);

        JLabel lblLibraryName = new JLabel("Library Name");
        lblLibraryName.setHorizontalAlignment(SwingConstants.RIGHT);
        lblLibraryName.setForeground(new Color(153, 0, 0));
        lblLibraryName.setFont(new Font("Malgun Gothic", Font.BOLD, 16));
        lblLibraryName.setBounds(17, 79, 111, 25);
        contentPane.add(lblLibraryName);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
        lblAddress.setForeground(new Color(153, 0, 0));
        lblAddress.setFont(new Font("Malgun Gothic", Font.BOLD, 16));
        lblAddress.setBounds(48, 119, 80, 22);
        contentPane.add(lblAddress);
        
        JLabel lblPhone = new JLabel("Phone ");
        lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPhone.setForeground(new Color(153, 0, 0));
        lblPhone.setFont(new Font("Malgun Gothic", Font.BOLD, 16));
        lblPhone.setBounds(58, 160, 70, 25);
        contentPane.add(lblPhone);

        textFieldLibraryID = new JTextField();
        textFieldLibraryID.setEditable(false);
        textFieldLibraryID.setFont(new Font("Malgun Gothic", Font.PLAIN, 16));
        textFieldLibraryID.setText("Auto ID");
        textFieldLibraryID.setBounds(148, 39, 120, 25);
        contentPane.add(textFieldLibraryID);
        textFieldLibraryID.setColumns(12);

        textFieldLibraryName = new JTextField();
        textFieldLibraryName.setBounds(148, 80, 376, 25);
        contentPane.add(textFieldLibraryName);
        textFieldLibraryName.setColumns(10);

        textFieldAddress = new JTextField();
        textFieldAddress.setBounds(148, 122, 376, 25);
        textFieldAddress.setColumns(10);
        contentPane.add(textFieldAddress);

        JButton btnInsert = new JButton("Insert");
        btnInsert.setForeground(new Color(0, 0, 153));
        btnInsert.setFont(new Font("Malgun Gothic", Font.BOLD, 17));
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(textFieldLibraryID.getText());
                    String libname = textFieldLibraryName.getText();
                    String address = textFieldAddress.getText();
                    String phone = textFieldPhone.getText();
                    
                    Connection conn = DBconnector.getConnection();
                    PreparedStatement p =  conn.prepareStatement("INSERT INTO LIBRARY VALUES (?, ?, ?, ?)");
                    p.setInt(1, id);
                    p.setString(2, libname);
                    p.setString(3, address);
                    p.setString(4, phone);

                    int n = p.executeUpdate();

                    JOptionPane.showMessageDialog(null, n + " Record inserted.", "INSERT",JOptionPane.PLAIN_MESSAGE);

                    p.close();
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, "Invalid Key for LIBRARY ID, Please try again", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btnInsert.setBounds(248, 283, 95, 25);
        contentPane.add(btnInsert);

        JButton btnClose = new JButton("Close");
        btnClose.setForeground(new Color(0, 0, 153));
        btnClose.setFont(new Font("Malgun Gothic", Font.BOLD, 17));
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DriverClass.librarysearchlist.setEnabled(true);
                DriverClass.libraryinsert.setVisible(false);
            }
        });
        btnClose.setBounds(463, 283, 95, 25);
        contentPane.add(btnClose);
                
                textFieldPhone = new JTextField();
                textFieldPhone.setBounds(148, 163, 376, 25);
                contentPane.add(textFieldPhone);
                textFieldPhone.setColumns(10);
                                
                                JSeparator separator_1 = new JSeparator();
                                separator_1.setBounds(68, 271, 445, 2);
                                contentPane.add(separator_1);
                                
                                        // JPanel Container
                                        JPanel panel = new JPanel();
                                        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
                                        panel.setBounds(10, 10, 564, 308);
                                        contentPane.add(panel);
                                        
                                                JSeparator separator = new JSeparator();
                                                panel.add(separator);
    }
}
