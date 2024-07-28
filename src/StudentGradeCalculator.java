import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculator extends JFrame
{
    private JTextField nameField;
    private JTextField subjectCountField;
    private JTextField[] marksFields;
    private JLabel averageLabel;
    private JLabel gradeLabel;
    private JPanel marksPanel;

    public StudentGradeCalculator()
    {
        setTitle("Student Grade Calculator");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1));

        // Student name input
        JPanel namePanel = new JPanel();
        namePanel.add(new JLabel("Enter student's name: "));
        nameField = new JTextField(20);
        namePanel.add(nameField);
        add(namePanel);

        // Number of subjects input
        JPanel subjectPanel = new JPanel();
        subjectPanel.add(new JLabel("Enter number of subjects: "));
        subjectCountField = new JTextField(5);
        subjectPanel.add(subjectCountField);
        add(subjectPanel);

        // Button to generate marks input fields
        JButton generateFieldsButton = new JButton("Generate Marks Fields");
        generateFieldsButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                generateMarksFields();
            }
        });
        add(generateFieldsButton);

        // Panel for marks input fields
        marksPanel = new JPanel();
        add(marksPanel);

        // Calculate button
        JButton calculateButton = new JButton("Calculate Grade");
        calculateButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                calculateGrade();
            }
        });
        add(calculateButton);

        // Results display
        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new GridLayout(2, 1));
        averageLabel = new JLabel("Average Marks: ");
        gradeLabel = new JLabel("Grade: ");
        resultsPanel.add(averageLabel);
        resultsPanel.add(gradeLabel);
        add(resultsPanel);
    }

    private void generateMarksFields() 
    {
        marksPanel.removeAll();
        int numberOfSubjects = Integer.parseInt(subjectCountField.getText());
        marksPanel.setLayout(new GridLayout(numberOfSubjects, 2));
        marksFields = new JTextField[numberOfSubjects];

        for (int i = 0; i < numberOfSubjects; i++)
        {
            marksPanel.add(new JLabel("Enter marks for subject " + (i + 1) + ": "));
            marksFields[i] = new JTextField(5);
            marksPanel.add(marksFields[i]);
        }

        marksPanel.revalidate();
        marksPanel.repaint();
    }

    private void calculateGrade() 
    {
        int numberOfSubjects = marksFields.length;
        double[] marks = new double[numberOfSubjects];
        for (int i = 0; i < numberOfSubjects; i++)
        {
            marks[i] = Double.parseDouble(marksFields[i].getText());
        }

        double averageMarks = calculateAverage(marks);
        char grade = determineGrade(averageMarks);

        averageLabel.setText("Average Marks: " + averageMarks);
        gradeLabel.setText("Grade: " + grade);
    }

    private double calculateAverage(double[] marks) 
    {
        double sum = 0;
        for (double mark : marks)
        {
            sum += mark;
        }
        return sum / marks.length;
    }

    private char determineGrade(double averageMarks)
    {
        if (averageMarks >= 90) 
        {
            return 'A';
        } else if (averageMarks >= 80) {
            return 'B';
        } else if (averageMarks >= 70) {
            return 'C';
        } else if (averageMarks >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            StudentGradeCalculator frame = new StudentGradeCalculator();
            frame.setVisible(true);
        });
    }
}
