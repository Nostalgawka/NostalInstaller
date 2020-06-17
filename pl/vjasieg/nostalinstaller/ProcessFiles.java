package pl.vjasieg.nostalinstaller;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class ProcessFiles {

    public static void processFile(String file, String unzipUrl) {
        try {
            //new ZipFile(file).extractFile(zipFolderName + "/", unzipUrl);
            new ZipFile(file).extractAll(unzipUrl);
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }
}
