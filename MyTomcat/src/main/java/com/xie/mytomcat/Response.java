package com.xie.mytomcat;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by xieyang on 17/1/27.
 */
public class Response {
    static String rt = System.getProperty("line.separator", "\r\n");
    private OutputStream os;

    public Response(OutputStream os) {
        this.os = os;
    }

    public void write(String path) throws IOException {
        if (path == null) {
            os.write(("HTTP/1.1 404 OK" + rt).getBytes());
            os.write(("Content-Type:text/html;charset:utf-8" + rt).getBytes());
            os.write(rt.getBytes());
            os.write("地址错误".getBytes());
            return;
        }
        System.out.println("path:" + path);

        if (isStatic(path)) {
            String absolutPath = null;
            try {
                absolutPath = getClass().getResource(path).getPath();
            } catch (Exception e) {
                os.write(("HTTP/1.1 404 OK" + rt).getBytes());
                os.write(("Content-Type:text/html;charset:utf-8" + rt).getBytes());
                os.write(rt.getBytes());
                os.write("file not found 找不到资源路径....".getBytes());
                return;
            }

            System.out.println("absolutPaht:" + absolutPath);
            FileInputStream fos = new FileInputStream(absolutPath);
            os.write(("HTTP/1.1 200 OK" + rt).getBytes());
            os.write(("Content-Type:text/html;charset:utf-8" + rt).getBytes());
            os.write(rt.getBytes());

            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = fos.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
            fos.close();
            os.flush();
            os.close();
        } else {
            os.write(("HTTP/1.1 200 OK" + rt).getBytes());
            os.write(("Content-Type:text/html;charset:utf-8" + rt).getBytes());
            os.write(rt.getBytes());
            os.write("ok".getBytes());
        }

    }

    private boolean isStatic(String path) {

        String[] suffixs = {"html", "css", "js", "jpg", "JPG", "jpeg", "JPEG", "png", "PNG"};
        for (String suffix : suffixs) {
            if (path.endsWith(suffix)) {
                return true;
            }
        }
        return false;
    }

}
