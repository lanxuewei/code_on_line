package com.lanxuewei.code_on_line.judger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {
    public static boolean createTextFile(File file, String content) {
        if (file == null || content == null) {
            System.out.println("file or content null");
            return false;
        }
        if (content.length() == 0) {
            System.out.println("empty content!");
        }
        FileOutputStream out = null;
        try {
            if (file.exists()) {
                file.delete();
            }
            if (!file.createNewFile()) {
                System.out.println("create file " + file + " failed!");
                return false;
            }
            out = new FileOutputStream(file);
            out.write(content.getBytes());
            out.flush();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("create text file: `" + file + "`, content: `" + content + "` failed!");
        return false;
    }

    public static String readTextFile(File file) {
        if (file == null) return null;
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(file.toURI()));
            return new String(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int compareTextFile(File a, File b) {
        String atxt = readTextFile(a);
        if (atxt == null) atxt = "";
        return atxt.compareTo(readTextFile(b));
    }

    public static int secondToMilliSecond(int second) {
        return second * 1000;
    }
}
