package com.repeat.repeat.data;

import com.repeat.repeat.Category;

import java.io.*;
import java.util.List;

public final class SaveAndReadData {
    private static File file = new File("database.ser");

    public static void saveChanges() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream os = new ObjectOutputStream(fileOutputStream);

            os.writeObject(Database.getListOfAllCategory());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream is = new ObjectInputStream(fileInputStream);
            Database.setListOfAllCategory((List<Category>) is.readObject());
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
