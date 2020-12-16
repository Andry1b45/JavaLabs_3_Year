package view;

import java.util.ArrayList;

public interface View {
    void print(String data);
    void printList(ArrayList<String> data);
    void error(String data);
    String getAnswer(String data);
}