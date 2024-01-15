/**
 * @file HedgeYourBet.java
 * @brief This file contains the HedgeYourBet class.
 * @author Sergei Rogov U231N0051
 * @date 15.01.2024
 */
package hedge_your_bet;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.util.ArrayList;

/**
 * @class HedgeYourBet
 * @brief Represents the main page of the Hedge Your Bet game application.
 */
public class HedgeYourBet implements ActionListener {
	
	// Array with questions
	final private static ArrayList<String> QUESTIONS = new ArrayList<String>();
	// Array with answers
	final private static ArrayList<Integer> ANSWERS = new ArrayList<Integer>();
	// index of current question
	private int question_index = 0;
	// total score
	private int cumulative_score = 0;
	
	// Main frame
	JFrame frame = new JFrame("Hedge Your Bet");
	
	// welcoming labels
	JLabel titleLabel = new JLabel("Hedge Your Bet!");
	JLabel questionLabel = new JLabel();
	
	// Creating three checkBoxes
    JCheckBox checkBox1 = new JCheckBox("Italy");
    JCheckBox checkBox2 = new JCheckBox("France");
    JCheckBox checkBox3 = new JCheckBox("Sweden");
    
    // list of checkBoxes
    private ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
	
    // Submit button
	JButton submitButton = new JButton("Submit answer");
	
	// Result labels
	JLabel resultLabel = new JLabel("Your result:");
	JLabel scoreLabel = new JLabel();
	JLabel complementLabel = new JLabel();
	
	/**
	 * @method HedgeYourBet
     * @brief Constructs a new HedgeYourBet game.
     * 		  Instantiates all system-related objects and GUI.
     */
	public HedgeYourBet(){
		initializeQuestionsAndAnswers();
		initializeGUI();
	}
	
	/**
	 * @method initializeQuestionsAndAnswers
     * @brief Initializes all questions and answers.
     */
	private void initializeQuestionsAndAnswers() {
		
		QUESTIONS.add("Most visited country in the world?"); // France
		QUESTIONS.add("Where was IKEA founded?"); // Sweden
		QUESTIONS.add("Which of these countries have the largest area?"); // France
		QUESTIONS.add("Which of these countries have the smallest area?"); // Italy
		QUESTIONS.add("Which of these countries have the largest gdp?"); // France
		
		ANSWERS.add(1); // France
		ANSWERS.add(2); // Sweden
		ANSWERS.add(1); // France
		ANSWERS.add(0);	// Italy
		ANSWERS.add(1); // France
		
	}
	
	/**
	 * @method initializeGUI
     * @brief Initializes all necessary GUI elements. GUI setup logic.
     */
	private void initializeGUI() {
		
		// Add item listeners to checkboxes
        checkBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                handleCheckboxStateChanged();
            }
        });
        
        checkBox2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                handleCheckboxStateChanged();
            }
        });

        checkBox3.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                handleCheckboxStateChanged();
            }
        });

        // Add action listener to the submit button
        submitButton.addActionListener(this);
        
        // Set the initial state of the submit button
        submitButton.setEnabled(false);
        
        // Choosing parameters for location on the frame and fonts
        titleLabel.setBounds(185, 30, 600, 25);
        titleLabel.setFont(new Font(null, Font.BOLD, 16));
        
        // display question
        questionLabel.setText(QUESTIONS.get(0));
        questionLabel.setBounds(50, 100, 600, 25);
        questionLabel.setFont(new Font(null, Font.BOLD, 16));
        
        // Choosing parameters for location on the frame
        checkBox1.setBounds(100, 200, 600, 25);
        checkBox2.setBounds(100, 225, 600, 25);
        checkBox3.setBounds(100, 250, 600, 25);
    	
    	submitButton.setBounds(80, 300, 140, 25);
    	
		resultLabel.setBounds(270, 200, 90, 25);
		resultLabel.setVisible(false);
		
		scoreLabel.setBounds(360, 200, 50, 25);
		scoreLabel.setVisible(false);
		
		complementLabel.setBounds(295, 235, 600, 25);
		complementLabel.setVisible(false);
    	  
		// Add components to the frame
        frame.add(titleLabel);
        frame.add(questionLabel);
        frame.add(checkBox1);
        frame.add(checkBox2);
        frame.add(checkBox3);
        frame.add(submitButton);
        frame.add(resultLabel);
        frame.add(scoreLabel);
        frame.add(complementLabel);
        
        checkBoxes.add(checkBox1);
        checkBoxes.add(checkBox2);
        checkBoxes.add(checkBox3);
        
	    // Application will exit after user clicks close button
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500); // Dimensions of a frame
		frame.setLayout(null);
		frame.setVisible(true);

    }

	/**
	 * @method handleCheckboxStateChanged
     * @brief Handles checkBoxes state changes.
     */
    private void handleCheckboxStateChanged() {
        if (checkBox1.isSelected() || checkBox2.isSelected() || checkBox3.isSelected()) {
            // Enable the submit button if at least one checkBox is selected
            submitButton.setEnabled(true);
        } else {
            // Disable the submit button if no checkBoxes are selected
            submitButton.setEnabled(false);
        }
    }
    
    /**
	 * @method updateQuestionLabel
     * @brief Updates question label with current question.
     */
    private void updateQuestionLabel() {
        if (question_index < QUESTIONS.size()) {
           questionLabel.setText(QUESTIONS.get(question_index));
        }
    }
    
    /**
	 * @method showScoreInfo
     * @brief Shows results after the game.
     */
    private void showScoreInfo() {
    	String message;
    	
    	resultLabel.setVisible(true);
    	
    	scoreLabel.setText(cumulative_score + "");
    	scoreLabel.setVisible(true);
    	
    	// determine a message according to the total score
    	if (cumulative_score > 21) {
    		message = "Fantastic!";
    	} else if (cumulative_score <= 21 && cumulative_score > 15) {
    		message = "Very Good";
    	} else {
    		message = "OK";
    	}
    	
    	complementLabel.setText(message);
    	complementLabel.setVisible(true);
    	
    }
    
    /**
     * @method actionPerformed
     * @brief Handles action events.
     * @param e The ActionEvent representing the button click or action.
     */
	@Override
	public void actionPerformed(ActionEvent e) {

		// if the Submit button is clicked
		if(e.getSource()==submitButton) {
			
			// is correct answer selected?
			boolean correctAnswerSelected = checkBoxes.get(ANSWERS.get(question_index)).isSelected();
			int selectedCount = 0;
			
			// count number of selected checkBoxes (out of 3)
			for (JCheckBox checkBox : checkBoxes) {
				if (checkBox.isSelected()) selectedCount++;
			}
			
			if (!correctAnswerSelected) {
				// + zero points
			} else {
				if (selectedCount == 3) {
					cumulative_score++; // +1 point
				} else if (selectedCount == 2) {
					cumulative_score += 2; // +2 points
				} else {
					cumulative_score += 5; // +5 points
				}
			}
			// increment index => proceed to next question
			question_index++;
			
			if (question_index == QUESTIONS.size()) {
				// if it is the last question
				showScoreInfo();
			} else {
				updateQuestionLabel();
			}
			
			// Reset checkBoxes
			for (JCheckBox checkBox : checkBoxes) {
				checkBox.setSelected(false);
			}
		}
		
	}
	
	/**
     * @method main
     * @brief Kick-starts the application.
     */
	public static void main(String[] args) {
	
		new HedgeYourBet();

	}

}
