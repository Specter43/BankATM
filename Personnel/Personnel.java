package Personnel;

import java.io.BufferedWriter;
import java.io.FileWriter;

public abstract class Personnel {
    private int ID;
    private String name;

    public Personnel() {
    }

    public Personnel(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public String getIDString(){
        return Integer.toString(ID);
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
