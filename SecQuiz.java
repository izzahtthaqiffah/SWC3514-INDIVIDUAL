import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SecQuiz extends JFrame {
    private int totalScore = 0; 
    private JTextField txtAnswer;
    private JLabel lblQuestion, lblFeedback, lblCorrection;
    private JButton btnSubmit, btnExit;

    //Member Inner Class
    class ScoreManager {
        int score = 0; 
        void applyScore(boolean isCorrect) {
            if (isCorrect) {
                this.score = 20; // Points for the level
                SecQuiz.this.totalScore += this.score; 
            }
        }
    }

    public SecQuiz(int startingScore) {
        super("Multiply - Level 2");
        this.totalScore = startingScore;
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        //North
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        northPanel.setBackground(Color.WHITE);
        northPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 20, 0));
        lblQuestion = new JLabel("Solve: 5 + 5 x 5 - 5");
        lblQuestion.setFont(new Font("Arial", Font.BOLD, 24));
        northPanel.add(lblQuestion);

        //Center
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JLabel lblPrompt = new JLabel("Enter your answer: ");
        lblPrompt.setFont(new Font("Arial", Font.PLAIN, 16));

        txtAnswer = new JTextField(10); 
        txtAnswer.setFont(new Font("Arial", Font.BOLD, 18));

        centerPanel.add(lblPrompt);
        centerPanel.add(txtAnswer);

        //South
        JPanel southPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        southPanel.setBackground(Color.WHITE);
        southPanel.setBorder(BorderFactory.createEmptyBorder(20, 200, 50, 200));

        btnSubmit = new JButton("Verify Answer");
        btnExit = new JButton("Finish Quiz");

        lblFeedback = new JLabel("", SwingConstants.CENTER);
        lblFeedback.setFont(new Font("Arial", Font.BOLD, 20));
        lblCorrection = new JLabel("", SwingConstants.CENTER);

        // Anonymous Class for Submit Button
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Local Class for Logic
                class Feedback {
                    void process() {
                        String input = txtAnswer.getText().trim();

                        if (input.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Please type an answer!");
                            return;
                        }

                        try {
                            int userValue = Integer.parseInt(input);
                            boolean isCorrect = (userValue == 25);

                            ScoreManager sm = new ScoreManager();
                            sm.applyScore(isCorrect);

                            if (isCorrect) {
                                lblFeedback.setText("Correct!");
                                lblFeedback.setForeground(Color.GREEN);
                                lblCorrection.setText("");
                            } else {
                                lblFeedback.setText("Incorrect!");
                                lblFeedback.setForeground(Color.RED);
                                lblCorrection.setText("Hint: Multiply before adding.");
                                txtAnswer.setText("");
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Please enter numbers only!");
                        }
                    }
                }
                new Feedback().process();
            }
        });

        btnExit.addActionListener(e -> {
            String input = txtAnswer.getText().trim();

            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "You haven't finished yet! Please enter an answer before exiting.", 
                    "Incomplete Answer", 
                    JOptionPane.WARNING_MESSAGE);
            } else {
                // Show the final score summary
                String summary = "Quiz Finished!\n\n" +
                                "Final Score: " + totalScore + " points\n" +
                                "Thank you for playing!";
                
                JOptionPane.showMessageDialog(this, summary, "Results", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        });

        southPanel.add(btnSubmit);
        southPanel.add(btnExit);
        southPanel.add(lblFeedback);
        southPanel.add(lblCorrection);

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        setSize(650, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Pass 0 as the default score for testing this level alone
        new SecQuiz(0);
    }
}