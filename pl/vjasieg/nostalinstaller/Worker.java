package pl.vjasieg.nostalinstaller;

import javax.swing.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;

public class Worker extends SwingWorker<Void, Void> {

    private String site;
    private File file;

    public Worker(String site, File file) {
        this.site = site;
        this.file = file;
    }

    @Override
    protected Void doInBackground() throws Exception {
        URL url = new URL(site);
        HttpURLConnection connection = (HttpURLConnection) url
                .openConnection();
        long filesize = connection.getContentLength();
        long totalDataRead = 0;
        try (java.io.BufferedInputStream in = new java.io.BufferedInputStream(
                connection.getInputStream())) {
            java.io.FileOutputStream fos = new java.io.FileOutputStream(file);
            try (java.io.BufferedOutputStream bout = new BufferedOutputStream(
                    fos, 1024)) {
                byte[] data = new byte[1024];
                int i;
                while ((i = in.read(data, 0, 1024)) >= 0) {
                    totalDataRead = totalDataRead + i;
                    bout.write(data, 0, i);
                    int percent = (int) ((totalDataRead * 100) / filesize);
                    setProgress(percent);
                }
            }
        }
        return null;
    }
}
