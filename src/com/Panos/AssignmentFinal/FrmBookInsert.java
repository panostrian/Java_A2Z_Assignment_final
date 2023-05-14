package com.Panos.AssignmentFinal;

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

public class FrmBookInsert extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final JTextField textFieldTitle;
    final JTextField textFieldYear;
    final JTextField textFieldBookID;
    private JTextField textFieldEdition;
    private JTextField textFieldAuthor;

    public FrmBookInsert() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/aueb.jpg")));
        this.setTitle("Add a New Book in the Database");
        this.setBackground(SystemColor.activeCaption);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 600, 371);

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
                textFieldBookID.setText("");
                textFieldTitle.setText("");
                textFieldYear.setText("");
            }
        });

        JLabel lblBookID = new JLabel("ID");
        lblBookID.setHorizontalAlignment(SwingConstants.RIGHT);
        lblBookID.setForeground(new Color(153, 0, 0));
        lblBookID.setFont(new Font("Malgun Gothic", Font.BOLD, 16));
        lblBookID.setBounds(47, 37, 60, 25);
        contentPane.add(lblBookID);

        JLabel lblTitle = new JLabel("Title");
        lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTitle.setForeground(new Color(153, 0, 0));
        lblTitle.setFont(new Font("Malgun Gothic", Font.BOLD, 16));
        lblTitle.setBounds(47, 77, 60, 25);
        contentPane.add(lblTitle);

        JLabel lblYear = new JLabel("Year");
        lblYear.setHorizontalAlignment(SwingConstants.RIGHT);
        lblYear.setForeground(new Color(153, 0, 0));
        lblYear.setFont(new Font("Malgun Gothic", Font.BOLD, 16));
        lblYear.setBounds(47, 117, 60, 22);
        contentPane.add(lblYear);

        textFieldBookID = new JTextField();
        textFieldBookID.setEditable(false);
        textFieldBookID.setFont(new Font("Malgun Gothic", Font.PLAIN, 16));
        textFieldBookID.setText("Auto ID");
        textFieldBookID.setBounds(117, 37, 120, 25);
        contentPane.add(textFieldBookID);
        textFieldBookID.setColumns(12);

        textFieldTitle = new JTextField();
        textFieldTitle.setBounds(116, 80, 430, 25);
        contentPane.add(textFieldTitle);
        textFieldTitle.setColumns(10);

        textFieldYear = new JTextField();
        textFieldYear.setBounds(116, 120, 120, 25);
        textFieldYear.setColumns(10);
        contentPane.add(textFieldYear);

        JButton btnInsert = new JButton("Insert");
        btnInsert.setForeground(new Color(0, 0, 153));
        btnInsert.setFont(new Font("Malgun Gothic", Font.BOLD, 17));
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(textFieldBookID.getText());
                    String title = textFieldTitle.getText();
                    String year = textFieldYear.getText();
                    String edition = textFieldEdition.getText();
                    String author = textFieldAuthor.getText();
                    
                    Connection conn = DBconnector.getConnection();
                    PreparedStatement p = conn.prepareStatement("INSERT INTO BOOK VALUES (?, ?, ?, ?, ?)");
                    p.setInt(1, id);
                    p.setString(2, title);
                    p.setString(3, year);
                    p.setString(4, edition);
                    p.setString(5, author);

                    int n = p.executeUpdate();

                    JOptionPane.showMessageDialog(null, n + " Record inserted.", "INSERT",JOptionPane.PLAIN_MESSAGE);

                    p.close();
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, "Invalid Key for BOOK ID, Please try again", "Error", JOptionPane.WARNING_MESSAGE);
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
                DriverClass.booksearchlist.setEnabled(true);
                DriverClass.bookinsert.setVisible(false);
            }
        });
        btnClose.setBounds(463, 283, 95, 25);
        contentPane.add(btnClose);
                
                textFieldEdition = new JTextField();
                textFieldEdition.setBounds(117, 160, 120, 25);
                contentPane.add(textFieldEdition);
                textFieldEdition.setColumns(10);
                
                JLabel lblEdition = new JLabel("Edition");
                lblEdition.setHorizontalAlignment(SwingConstants.RIGHT);
                lblEdition.setForeground(new Color(153, 0, 0));
                lblEdition.setFont(new Font("Malgun Gothic", Font.BOLD, 16));
                lblEdition.setBounds(47, 157, 60, 25);
                contentPane.add(lblEdition);
                
                JLabel lblAuthor = new JLabel("Author(s)");
                lblAuthor.setHorizontalAlignment(SwingConstants.RIGHT);
                lblAuthor.setForeground(new Color(153, 0, 0));
                lblAuthor.setFont(new Font("Malgun Gothic", Font.BOLD, 16));
                lblAuthor.setBounds(27, 197, 80, 25);
                contentPane.add(lblAuthor);
                
                textFieldAuthor = new JTextField();
                textFieldAuthor.setColumns(10);
                textFieldAuthor.setBounds(117, 200, 429, 25);
                contentPane.add(textFieldAuthor);
                                
                                JSeparator separator_1 = new JSeparator();
                                separator_1.setBounds(63, 271, 445, 2);
                                contentPane.add(separator_1);
                                
                                        // JPanel Container
                                        JPanel panel1 = new JPanel();
                                        panel1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
                                        panel1.setBounds(10, 10, 564, 308);
                                        contentPane.add(panel1);
                                        
                                                JSeparator separator = new JSeparator();
                                                panel1.add(separator);
    }
}
