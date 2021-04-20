package database;

import java.util.ArrayList;
import java.util.List;

public class FileParser {
    public String currentLoadedFile;
    public ArrayList<String[]> parsedLoadedFile;
    public String filePath;

    public void readFile(){}
    public void writeFile(){}
    public String getDataOnLine(int Linenr){throw new UnsupportedOperationException();}
    public String getDataOnCell(int xkoord, int ykoord){throw new UnsupportedOperationException();}
    public String getAllData(){throw new UnsupportedOperationException();}
    public void addLine(String Line){}
    public void removeLine(String Line){}
    public void updateLine(int Linenr, String update){}
    public void updateCell(int xkoord, int ykoord, String update){}

}
