package gui;

import model.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GUI {

    public GUI(){
        JFrame frame = new JFrame("ShoppingLister Application 1.0");
        JPanel buttonPanel = new JPanel();
        JPanel textPanel = new JPanel();
        JButton addButton = new JButton("AddItem");
        JButton removeButton = new JButton("RemoveItem");
        JButton openButton = new JButton("OpenExistingList");
        JButton saveButton = new JButton("Save");
        DefaultListModel<Item> i1 = retrieveDefaultListModel();
        JList<Item> itemList = new JList<>(i1);
        itemList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(itemList.getSelectedValue());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        addButton.addActionListener(e -> {
            System.out.println("AddButton pressed");
            System.out.println("Adding new item to JList");
            addNewItem(frame, i1);
        });
        removeButton.addActionListener(e -> {
            if(itemList.getSelectedValue() != null){
                System.out.println("RemoveButton pressed");
                System.out.println("Removing selected item from JList");
                removeSelectedItem(itemList, i1);
            }
        });
        openButton.addActionListener(e -> {
            System.out.println("openButton pressed");
        });
        saveButton.addActionListener(e -> {
            System.out.println("saveButton pressed");
            try{
                FileOutputStream fileOut = new FileOutputStream("shoppingList.ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(i1);
                out.close();
                fileOut.close();
                System.out.println("Serialized(saved) data saved in shoppingList.ser");
            } catch(IOException i){
                i.printStackTrace();
            }
        });
        JTextField outputField = new JTextField("Output text in this text field");
        outputField.setEditable(false);
        itemList.setBackground(Color.DARK_GRAY);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(Color.gray);//testing purposes change back to normal after testing is complete
        //textPanel.add(inputField);
        textPanel.add(outputField);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.gray);
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);
        frame.getContentPane().add(BorderLayout.CENTER, itemList);
        frame.getContentPane().add(BorderLayout.WEST, buttonPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, textPanel);
        frame.setSize(500, 500);
        frame.getContentPane().setBackground(Color.GRAY);
        frame.getDefaultCloseOperation();
        frame.setVisible(true);
    }

    /*
     * Generates a basic starter JList for the user.
     * Maybe change this to be set to null. Only if no saved JList data from the past was found.
     */
    public JList<Item> getGeneratedItemList(DefaultListModel<Item> i1){
        i1.addElement(new Item("Apple"));
        i1.addElement(new Item("Eggs"));
        i1.addElement(new Item("Water"));
        i1.addElement(new Item("Brats"));
       return new JList<>(i1);
    }

    /*
     * Retrieves the serialized default list model from the project's shoppingList.ser file
     * If no data exist for the default list model. Then simple a new one is created
     */
    public DefaultListModel<Item> retrieveDefaultListModel(){
        DefaultListModel<Item> defaultListModel = new DefaultListModel<>();
        try{
            System.out.println("Retrieving existing default list model");
            FileInputStream fileIn = new FileInputStream("shoppingList.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            defaultListModel = (DefaultListModel<Item>) in.readObject();
            System.out.println(defaultListModel);
            in.close();
            fileIn.close();
            return defaultListModel;
        } catch(IOException i){
            System.out.println("IOException");
            i.printStackTrace();
        } catch(ClassNotFoundException c){
            System.out.println("ClassNotFoundException");
            c.printStackTrace();
        }
        return new DefaultListModel<>();
    }

    /*
     * Add a new item to the JList
     */
    public void addNewItem(JFrame frame, DefaultListModel<Item> i1){
        Object newItem = JOptionPane.showInputDialog(frame, "Enter the name of the new item:");
        if(newItem != null) {//prevents any NullPointerExceptions from occurring
            i1.addElement(new Item(newItem.toString()));
        }
    }

    /*
     * Removes an item from the JList
     */
    public void removeSelectedItem(JList<Item> itemList, DefaultListModel<Item> i1){
        i1.removeElement(itemList.getSelectedValue());
    }
}

/*
 JTextField inputField = new JTextField("Enter text into this text field");
        inputField.addActionListener(e -> {
            System.out.println("text field was enter keyed");
            outputField.setText(inputField.getText());
        });
        inputField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Mouse clicked");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Mouse pressed");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("Mouse released");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("Mouse entered");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("Mouse exited");
            }
        });
 */
