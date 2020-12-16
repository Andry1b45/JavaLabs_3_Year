package view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ViewContext implements View {
    Scanner input;
    PrintStream output;

    public ViewContext(InputStream input, OutputStream output) {
        this.input = new Scanner(input);
        this.output = new PrintStream(output);
    }

    @Override
    public void print(String data) {
        System.out.println(data);
    }

    @Override
    public void printList(ArrayList<String> data) {
        for (int i=0; i<data.size(); i++){
            System.out.println(data.get(i));
        }
    }

    @Override
    public void error(String error) {
        System.err.println(error);
    }

    @Override
    public String getAnswer(String message) {
        output.println(message);
        return input.nextLine();
    }
}