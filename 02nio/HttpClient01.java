package com.chenxk.LearnClassLoader;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author chenxiaokang
 * @date 2021/5/16
 */
public class HttpClient01 {
    private static OkHttpClient client = new OkHttpClient();
    public static void main(String[] args) {
        String url ="http://localhost:8801/";
        try {
            System.out.println(httpGet(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *  使用http get访问url
     * @param url
     * @return 报文体文本
     * @throws IOException
     */
    private static String httpGet(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
