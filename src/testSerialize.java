import model.Item;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class testSerialize {
    private Item item;

    public testSerialize(Item item){
        this.item = item;
    }

    public Item getItem(){
        return this.item;
    }

    public void setItem(Item item){
        this.item = item;
    }

    public void serializeItem(){
        //saves item to ser file
        try{
            FileOutputStream fileOut = new FileOutputStream("testSerialize.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(item);
            out.close();
            fileOut.close();
            System.out.println("Serialized(saved) data saved in shoppingList.ser");
        } catch(IOException i){
            i.printStackTrace();
        }
    }

    public void deserializeItem(){
        //retrieves saved item from ser file
        try{
            FileInputStream fileIn = new FileInputStream("testSerialize.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.item = (Item) in.readObject();
            System.out.println(this.item);
            in.close();
            fileIn.close();
        } catch(IOException i){
            System.out.println("IOException");
            i.printStackTrace();
        } catch(ClassNotFoundException c){
            System.out.println("ClassNotFoundException");
            c.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testSerialize test1 = new testSerialize(new Item(""));
        Scanner input = new Scanner(System.in);
        String itemName;
        Item getItem = null;

        System.out.println("Create new item?");
        String createItem = input.nextLine();
        if(createItem.equals("y")){
            System.out.println("Enter name of item");
            itemName = input.nextLine();

            System.out.println("Serialize?");
            String serialize = input.nextLine();
            if(serialize.equals("y")){
                test1.getItem().setItemName(itemName);
                test1.serializeItem();
            }
        }

        System.out.println("Deserialize?");
        String deserialize = input.nextLine();
        if(deserialize.equals("y")){
            test1.deserializeItem();
            getItem = test1.getItem();
            System.out.println(getItem.toString());
        }
    }
}
