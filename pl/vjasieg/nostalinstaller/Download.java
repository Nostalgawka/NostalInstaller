package pl.vjasieg.nostalinstaller;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Download {

    static boolean clientStatus = false;
    static boolean resourcesStatus = false;
    public static void download(JProgressBar progressBar, File file, String url) {
        clientStatus = false;
        resourcesStatus = false;
        final Worker worker = new Worker(url, file);
        worker.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent pcEvt) {
                if ("progress".equals(pcEvt.getPropertyName())) {
                    progressBar.setValue((Integer) pcEvt.getNewValue());
                } else if (pcEvt.getNewValue() == SwingWorker.StateValue.DONE) {
                    try {
                        worker.get();
                        if(file.getName().equalsIgnoreCase("Nostalgawka.zip")) {
                            ProcessFiles.processFile(file.getPath(), Utils.unzipClientPath);
                            file.delete();
                            clientStatus = true;
                            if(clientStatus && resourcesStatus) {
                                JOptionPane.showMessageDialog(Main.getFr(), "Ukończono instalację! Wejdź do launchera i poszukaj profilu NostalClient.");
                            }
                        }else {
                            ProcessFiles.processFile(file.getPath(), Utils.unzipResourcesPath);
                            file.delete();
                            resourcesStatus = true;
                            if(clientStatus && resourcesStatus) {
                                JOptionPane.showMessageDialog(Main.getFr(), "Ukończono instalację! Wejdź do launchera i poszukaj profilu NostalClient.");
                            }
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        worker.execute();
    }
    public static void downloadCrystal(JProgressBar progressBar, File file, String url) {
        final Worker worker = new Worker(url, file);
        worker.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent pcEvt) {
                if ("progress".equals(pcEvt.getPropertyName())) {
                    progressBar.setValue((Integer) pcEvt.getNewValue());
                } else if (pcEvt.getNewValue() == SwingWorker.StateValue.DONE) {
                    try {
                        worker.get();
                        ProcessFiles.processFile(file.getPath(), Utils.crystalPath);
                        file.delete();
                        JOptionPane.showMessageDialog(Main.getFr(), "Ukończono instalację! Wejdź do Crystal Launcher i poszukaj NostalClienta w menu \"Przeglądaj paczki\"");
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        worker.execute();
    }
}
