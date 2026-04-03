
/**
 * Program Description: Display MainPage
 *
 * Author: NUR IZZAH THAQIFFAH
 * Date: 31 MARCH 2026
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Driver Class
public class MainPage extends JFrame implements ActionListener
{

    private JLabel lblStart;
    private JButton btnStart;
    private Container cont;
    private FlowLayout layout;

    //Method
    public MainPage()
    {
        super("EASY MATH QUIZ"); //title header

        layout = new FlowLayout(FlowLayout.CENTER, 10, 20); // Adjust the layout

        cont = getContentPane();
        cont.setLayout(layout);
        cont.setBackground(Color.WHITE); // Set background color to white

        ImageIcon logo = new ImageIcon("easy-math.png");
        Image image = logo.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH); // Increase the size
        ImageIcon scaledLogo = new ImageIcon(image);
        JLabel imageLabel = new JLabel(scaledLogo);

        lblStart = new JLabel("Up for a challenge? Answer our questions easy math questions!");
        lblStart.setFont(new Font("Arial", Font.PLAIN, 16)); // Set font to Arial, size 16
        lblStart.setHorizontalAlignment(SwingConstants.CENTER); // Center align the label

        btnStart = new JButton("Start");
        btnStart.setFont(new Font("Arial", Font.BOLD, 16)); // Set font to Arial, bold, size 16

        cont.add(imageLabel);
        cont.add(lblStart);
        cont.add(btnStart);

        btnStart.setActionCommand("firstQuiz");
        btnStart.addActionListener(this);

        setSize(550, 400);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }//end of method

    //method for handling button clicks
    public void actionPerformed(ActionEvent e)
    {
        if ("firstQuiz".equals(e.getActionCommand())) 
        {
            navigateFirstQuiz();
        }
    }//end of method

    //method of navigating to the First Quiz
    private void navigateFirstQuiz() 
    {
        FirstQuiz firstQuiz = new FirstQuiz();
        dispose();
    }//end of method

    //Driver method
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> new MainPage());
    }//end of driver method
}//End of class
