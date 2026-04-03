import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FirstQuiz extends JFrame implements ActionListener {
    private int score = 0; 
    private JLabel lblQuestion, lblFeedback, lblCorrection;
    private JRadioButton btn1, btn2, btn3, btn4;
    private ButtonGroup group;
    private JButton btnSubmit, btnNext;
    private JCheckBox chkHint; 
    private boolean submitted = false; 

    //Member Inner Class
    class ScoreManager {
        int score = 0; // Shadowed variable
        void applyScore(boolean isCorrect) {
            if (isCorrect && !submitted) { 
                this.score = 10; 
                FirstQuiz.this.score += this.score; 
            }
        }
    }

    public FirstQuiz() {
        super("Plus - Level 1");

        setLayout(new BorderLayout()); 
        getContentPane().setBackground(Color.WHITE);

        //North
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        northPanel.setBackground(Color.WHITE);
        northPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0)); 

        lblQuestion = new JLabel("What is 15 + 7?", SwingConstants.CENTER);
        lblQuestion.setFont(new Font("Arial", Font.BOLD, 22));
        northPanel.add(lblQuestion);

        //Center
        JPanel panelOptions = new JPanel(new GridLayout(2, 2, 20, 20));
        panelOptions.setBackground(Color.WHITE);
        panelOptions.setBorder(BorderFactory.createEmptyBorder(60, 180, 60, 180)); 

        btn1 = new JRadioButton("20");
        btn2 = new JRadioButton("21");
        btn3 = new JRadioButton("22"); 
        btn4 = new JRadioButton("23");

        Font btnFont = new Font("Arial", Font.BOLD, 18);
        JRadioButton[] btns = {btn1, btn2, btn3, btn4};
        group = new ButtonGroup();

        for (JRadioButton b : btns) {
            b.setFont(btnFont);
            b.setBackground(Color.WHITE);
            group.add(b);
            panelOptions.add(b);
        }

        //South
        JPanel southPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        southPanel.setBackground(Color.WHITE);
        southPanel.setBorder(BorderFactory.createEmptyBorder(0, 200, 50, 200)); 

        chkHint = new JCheckBox("Show calculation hint?");
        chkHint.setBackground(Color.WHITE);
        chkHint.setHorizontalAlignment(SwingConstants.CENTER);

        btnSubmit = new JButton("Submit Answer");
        btnSubmit.setFont(new Font("Arial", Font.BOLD, 16));

        btnNext = new JButton("Next Level"); 
        btnNext.setFont(new Font("Arial", Font.BOLD, 16));
        btnNext.setActionCommand("secQuiz");
        btnNext.addActionListener(this); 

        lblFeedback = new JLabel("", SwingConstants.CENTER);
        lblFeedback.setFont(new Font("Arial", Font.BOLD, 18));
        lblCorrection = new JLabel("", SwingConstants.CENTER);
        lblCorrection.setFont(new Font("Arial", Font.PLAIN, 14));

        //Anonymous Class
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (group.getSelection() == null) {
                    JOptionPane.showMessageDialog(null, "Please pick an answer first!");
                    return;
                }

                //Local Class
                class ResultChecker {
                    void validate() {
                        boolean correct = btn3.isSelected();
                        ScoreManager manager = new ScoreManager();
                        manager.applyScore(correct);

                        if (correct) {
                            lblFeedback.setText("Correct!");
                            lblFeedback.setForeground(Color.GREEN);
                            lblCorrection.setText("");
                        } else {
                            lblFeedback.setText("Incorrect");
                            lblFeedback.setForeground(Color.RED);
                            lblCorrection.setText("The correct answer is 22.");
                        }
                        submitted = true; // Mark as submitted
                        btnSubmit.setEnabled(false);
                    }
                }
                new ResultChecker().validate();
            }
        });

        chkHint.addActionListener(e -> {
            if(chkHint.isSelected()) lblCorrection.setText("Hint: Use your calculator");
            else lblCorrection.setText("");
        });

        southPanel.add(chkHint);
        southPanel.add(btnSubmit);
        southPanel.add(btnNext);
        southPanel.add(lblFeedback);
        southPanel.add(lblCorrection);

        add(northPanel, BorderLayout.NORTH);
        add(panelOptions, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        setSize(650, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if ("secQuiz".equals(e.getActionCommand())) {
            navigateSecQuiz();
        }
    }

    private void navigateSecQuiz() {
        if (!submitted) {
            JOptionPane.showMessageDialog(this, 
                "Please submit your answer before moving to the next level!", 
                "Action Required", JOptionPane.WARNING_MESSAGE);
        } else {
            //Pass the score to the next question
            new SecQuiz(this.score); 
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FirstQuiz());
    }
}