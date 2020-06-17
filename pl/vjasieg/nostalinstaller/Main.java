package pl.vjasieg.nostalinstaller;

import java.awt.*;

public class Main {

    public static Frame fr;

    public static Frame getFr() {
        return fr;
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                fr = new Frame();
            }
        });
    }

}
