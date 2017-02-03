package com.xie.mytomcat;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by xieyang on 17/1/27.
 */
public class FileUtils {

    public static String getFileContent(String path) {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            FileReader fr = new FileReader(path);
            br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
