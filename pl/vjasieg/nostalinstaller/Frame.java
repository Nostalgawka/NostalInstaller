package pl.vjasieg.nostalinstaller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class Frame extends JFrame implements ActionListener {

    JPanel jPanel = (JPanel) this.getContentPane();
    ButtonGroup options = new ButtonGroup();

    JLabel title;
    JButton button;
    JProgressBar progressClient;
    JProgressBar progressResources;
    JRadioButton universal;
    JRadioButton crystal;
    JLabel installLabel;

    public JComponent addNewElement(JComponent component, int x, int y, int width, int height) {
        this.getContentPane().add(component);
        Dimension size = component.getPreferredSize();
        component.setBounds(x, y, size.width + width, size.height + height);
        return component;
    }

    public void registerElements() {
        //title
        addNewElement(title, 100, 1, 200, 34);
        title.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 30));
        //title

        //button
        addNewElement(button, 118, 240, 50, 20);
        button.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 15));
        //button

        //progressClient
        addNewElement(progressClient, 75, 165, 80, 10);
        addNewElement(progressResources, 75, 200, 80, 10);
        //progressClient

        //options
        options.add(universal);
        options.add(crystal);
        addNewElement(universal, 40, 90, 100, 10);
        addNewElement(crystal, 127, 120, 100, 10);
        addNewElement(installLabel, 120, 60, 100, 10);
        installLabel.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 20));
        universal.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 13));
        crystal.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 13));
        //options
    }

    public void initElements() {
        title = new JLabel("NostalInstaller");
        button = new JButton("Zainstaluj!");
        progressClient = new JProgressBar();
        progressResources = new JProgressBar();
        installLabel = new JLabel("Sposób instalacji:");
        universal = new JRadioButton("Uniwersalny (wrzuca do .minecraft/versions)");
        crystal = new JRadioButton("Crystal Launcher");
    }

    public Frame() {
        super("NostalInstaller");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jPanel.setLayout(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
        initElements();
        registerElements();
        this.setResizable(false);
        this.setSize(400, 340);
        button.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(universal.isSelected()) {
            Download.download(progressClient, new File(Utils.downloadPath + "\\Nostalgawka.zip"), "http://159.69.82.222/Nostalgawka.zip");
            Download.download(progressResources, new File(Utils.downloadPath + "\\resource.zip"), "http://159.69.82.222/resource.zip");
        }else if(crystal.isSelected()) {
            Download.downloadCrystal(progressClient, new File(Utils.crystalPath + "\\u.NostalClient.zip"), "http://159.69.82.222/u.NostalClient.zip");
            progressResources.setValue(100);
        }else {
            JOptionPane.showMessageDialog(Main.getFr(), "Zaznacz jedną z opcji.");
        }
    }
}
