package com.xie.mytomcat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieyang on 17/1/27.
 */
public class Request {
    private String uri;
    private String requestMethod;
    private Map<String, String> requestParams = new HashMap<String, String>();

    public Request(InputStream is) throws IOException {

//        byte[] bytes = new byte[1024];
//        int len = 0;
//        while ((len = is.read(bytes)) !=-1) {
//            System.out.println(new String(bytes));
//        }

        BufferedReader bf = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = bf.readLine()) != null) {
            System.out.println(line);
            parserRequest(line);
        }
        System.out.println();
        System.out.println(requestMethod + "==" + uri);
        System.out.println();
    }

    private void parserRequest(String line) {
        String[] requestMethods = {"GET", "POST", "OPTIONS", "PUT", "DELETE", "LINK", "UNLINK", "VIEW"};
        for (String m : requestMethods) {
            if (line.startsWith(m)) {
                uri = line.substring(line.indexOf("/"), line.indexOf("HTTP/1.1") - 1);
                if (uri.contains("?")) {
                    uri = uri.substring(0, uri.indexOf("?"));
                }
                requestMethod = line.substring(0, line.indexOf("/") - 1);
                if ("GET".equals(requestMethod)) {

                }
                if ("POST".equals(requestMethod)) {

                }
            }
        }

    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public Map<String, String> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(Map<String, String> requestParams) {
        this.requestParams = requestParams;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
