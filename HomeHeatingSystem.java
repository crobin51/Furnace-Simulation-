
/**
 * Write a description of class GUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class HomeHeatingSystem extends JFrame 
{
    private JPanel newPanel;
    private JButton sim, exit;
    private JLabel initialTemp, outsideTemp, desiredTemp, type, capacity, efficiency, size, overheat, frequency, runTime, year;
    private JTextField initialTempIn, outsideTempIn, desiredTempIn, typeIn, capacityIn, efficiencyIn, sizeIn, overheatIn, frequencyIn, runTimeIn, yearIn;

    public HomeHeatingSystem() 
    {
        // creates window
        setSize(500, 400);
        setTitle("Home Heating System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // creates and adds panel to window
        createPanel();
        add(newPanel);

        setVisible(true);
    }

    public void createPanel()
    {
        // creates new panel and sets layout to grid
        newPanel = new JPanel();
        newPanel.setLayout(new GridLayout(12, 2));

        // adds buttons and listeners for the buttons
        sim = new JButton("Simulate");
        sim.addActionListener(new CalculateListener());

        exit = new JButton("Exit");
        exit.addActionListener(new ExitListener());

        // labels for text fields
        initialTemp = new JLabel("Initial Temperature", JLabel.RIGHT);
        outsideTemp = new JLabel("Outside Temperature", JLabel.RIGHT);
        desiredTemp = new JLabel("Desired Room Temperature", JLabel.RIGHT);
        year = new JLabel("Year House was Built", JLabel.RIGHT);
        type = new JLabel("Furnace Type", JLabel.RIGHT);
        capacity = new JLabel("Furnace Capacity", JLabel.RIGHT);
        efficiency = new JLabel("Furnace Efficiency", JLabel.RIGHT);
        size = new JLabel("Room Size", JLabel.RIGHT);
        overheat = new JLabel("Allowable Amount of Overheating", JLabel.RIGHT);
        frequency = new JLabel("Frequency of Output", JLabel.RIGHT);
        runTime = new JLabel("Run Time", JLabel.RIGHT);

        // fields to enter and display information
        initialTempIn = new JTextField("22.0", 8);
        outsideTempIn = new JTextField("10.0", 8);
        desiredTempIn = new JTextField("22.0", 8);
        yearIn = new JTextField("1990", 8);
        typeIn = new JTextField("Gas", 8);
        capacityIn = new JTextField("50000", 8);
        efficiencyIn = new JTextField("0.9", 8);
        sizeIn = new JTextField("2000.0", 8);
        overheatIn = new JTextField("2.0", 8);
        frequencyIn = new JTextField("5", 8);
        runTimeIn = new JTextField("7200", 8);

        // adds all buttons, labels, and textboxes
        newPanel.add(initialTemp);
        newPanel.add(initialTempIn);
        newPanel.add(outsideTemp);
        newPanel.add(outsideTempIn);
        newPanel.add(desiredTemp);
        newPanel.add(desiredTempIn);
        newPanel.add(year);
        newPanel.add(yearIn);
        newPanel.add(type);
        newPanel.add(typeIn);
        newPanel.add(capacity);
        newPanel.add(capacityIn);
        newPanel.add(efficiency);
        newPanel.add(efficiencyIn);
        newPanel.add(size);
        newPanel.add(sizeIn);
        newPanel.add(overheat);
        newPanel.add(overheatIn);
        newPanel.add(frequency);
        newPanel.add(frequencyIn);
        newPanel.add(runTime);
        newPanel.add(runTimeIn);
        newPanel.add(sim);
        newPanel.add(exit);

    }
    private class CalculateListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // checks all inputs upon pressing the calculate button
            // if there are any errors, the user is prompted for an input until it is valid
            Boolean error = true;

            String initialTempStr = initialTempIn.getText();
            while (error)
            {
                try
                {
                    double initial = Double.parseDouble(initialTempStr);
                    error = false;
                }
                catch(IllegalArgumentException f)
                {
                    initialTempStr = JOptionPane.showInputDialog(null, "Initial temperature input invalid.\nPlease try again. ");
                    initialTempIn.setText(initialTempStr);
                }
            }

            error = true;
            String outsideTempStr = outsideTempIn.getText();
            while (error)
            {
                try
                {
                    double outside = Double.parseDouble(outsideTempStr);
                    error = false;
                }
                catch(IllegalArgumentException f)
                {
                    outsideTempStr = JOptionPane.showInputDialog(null, "Outside temperature input invalid.\nPlease try again. ");
                    outsideTempIn.setText(outsideTempStr);
                }
            }

            error = true;
            String desiredTempStr = desiredTempIn.getText();
            while (error)
            {
                try
                {
                    double desired = Double.parseDouble(desiredTempStr);
                    error = false;
                }
                catch(IllegalArgumentException f)
                {
                    desiredTempStr = JOptionPane.showInputDialog(null, "Desired temperature input invalid.\nPlease try again. ");
                    desiredTempIn.setText(desiredTempStr);
                }
            }

            error = true;
            String yearStr = yearIn.getText();
            while (error)
            {
                try
                {
                    int yearCheck = Integer.parseInt(yearStr);
                    error = false;
                }
                catch(IllegalArgumentException f)
                {
                    yearStr = JOptionPane.showInputDialog(null, "Year input invalid.\nPlease try again. ");
                    yearIn.setText(yearStr);
                }
            }

            error = true;

            String typeStr = typeIn.getText();
            while (error)
            {
                 try
                {
                    if (typeStr.equalsIgnoreCase("Gas") || typeStr.equalsIgnoreCase("Electric"))
                    {error = false;}
                    else
                    {throw new InvalidType();}
                }
                catch(IllegalArgumentException f)
                {
                    typeStr = JOptionPane.showInputDialog(null, "Type input invalid.\nPlease try again. ");
                    typeIn.setText(typeStr);
                }
                catch(InvalidType f)
                {
                    typeStr = JOptionPane.showInputDialog(null, "Input must be either \"Gas\" or \"Electric\".\nPlease try again. ");
                    typeIn.setText(typeStr);
                }

            }

            error = true;
            String capacityStr = capacityIn.getText();
            while (error)
            {
                try
                {
                    int capacityCheck = Integer.parseInt(capacityStr);
                    error = false;
                }
                catch(IllegalArgumentException f)
                {
                    capacityStr = JOptionPane.showInputDialog(null, "Capacity input invalid.\nPlease try again. ");
                    capacityIn.setText(capacityStr);
                }
            }

            error = true;
            String efficiencyStr = efficiencyIn.getText();
            while (error)
            {
                try
                {
                    double efficiencyCheck = Double.parseDouble(efficiencyStr);
                    if (efficiencyCheck < 0 || efficiencyCheck > 1)
                    {
                        throw new InvalidEfficiency();
                    }
                    error = false;
                }
                catch(IllegalArgumentException f)
                {
                    efficiencyStr = JOptionPane.showInputDialog(null, "Efficiency input invalid.\nPlease try again. ");
                    efficiencyIn.setText(efficiencyStr);
                }
                catch(InvalidEfficiency f)
                {
                    efficiencyStr = JOptionPane.showInputDialog(null, "Efficiency input must be between 0 and 1.\nPlease try again. ");
                    efficiencyIn.setText(efficiencyStr);
                }
            }

            error = true;
            String sizeStr = sizeIn.getText();
            while (error)
            {
                try
                {
                    double sizeCheck = Double.parseDouble(sizeStr);
                    error = false;
                }
                catch(IllegalArgumentException f)
                {
                    sizeStr = JOptionPane.showInputDialog(null, "Size input invalid.\nPlease try again. ");
                    sizeIn.setText(sizeStr);
                }
            }

            error = true;
            String overheatStr = overheatIn.getText();
            while (error)
            {
                try
                {
                    double overheatCheck = Double.parseDouble(overheatStr);
                    error = false;
                }
                catch(IllegalArgumentException f)
                {
                    overheatStr = JOptionPane.showInputDialog(null, "Overheat temperature input invalid.\nPlease try again. ");
                    overheatIn.setText(overheatStr);
                }
            }

            error = true;
            String frequencyStr = frequencyIn.getText();
            while (error)
            {
                try
                {
                    double frequencyCheck = Double.parseDouble(frequencyStr);
                    if(frequencyCheck < 0)
                    {
                        throw new InvalidFreq();
                    }
                    error = false;
                }
                catch(IllegalArgumentException f)
                {
                    frequencyStr = JOptionPane.showInputDialog(null, "Frequency input invalid.\nPlease try again. ");
                    frequencyIn.setText(frequencyStr);
                }
                catch(InvalidFreq f)
                {
                    frequencyStr = JOptionPane.showInputDialog(null, "Frequency input invalid.\nPlease try again. ");
                    frequencyIn.setText(frequencyStr);
                }
            }

            error = true;
            String runtimeStr = runTimeIn.getText();
            while (error)
            {
                try
                {
                    int runtimeCheck = Integer.parseInt(runtimeStr);
                     if(runtimeCheck < 0)
                    {
                        throw new InvalidRun();
                    }
                    error = false;
                }
                catch(IllegalArgumentException f)
                {
                    runtimeStr = JOptionPane.showInputDialog(null, "Runtime input invalid.\nPlease try again. ");
                    runTimeIn.setText(runtimeStr);
                }
                catch(InvalidRun f)
                {
                    runtimeStr = JOptionPane.showInputDialog(null, "Runtime input invalid.\nPlease try again. ");
                    runTimeIn.setText(runtimeStr);
                }
            }

            // instantiates an Environment object and creates a room based on user inputs.
            Environment newPlace = new Environment(Double.parseDouble(outsideTempStr));
            newPlace.buildRoom(Double.parseDouble(sizeStr), Double.parseDouble(initialTempStr), typeStr, Integer.parseInt(capacityStr), Double.parseDouble(efficiencyStr), Integer.parseInt(yearStr), Double.parseDouble(overheatStr), Double.parseDouble(desiredTempStr), Double.parseDouble(frequencyStr), Integer.parseInt(runtimeStr), Double.parseDouble(outsideTempStr));
        }
    }

    private class ExitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // exits upon clicking the "Exit" button
            System.exit(0);
        }
    }
}