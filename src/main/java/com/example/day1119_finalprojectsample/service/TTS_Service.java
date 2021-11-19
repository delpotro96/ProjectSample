package com.example.day1119_finalprojectsample.service;

//네이버 음성합성 Open API 예제

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class TTS_Service {

    private Object oldName;

    public String tts(String text) {
        String clientId = "5eu50cwdvd";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "g42Rfm6qnHA2A8E2Ypjmeo4A7Hq8c8eVXYlAh3Un";//애플리케이션 클라이언트 시크릿값";
        try {
            text = URLEncoder.encode(text, "UTF-8"); // 13자
            String apiURL = "https://naveropenapi.apigw.ntruss.com/tts-premium/v1/tts";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            // post request
            String postParams = "speaker=dara_ang&volume=0&speed=0&pitch=0&format=mp3&text=" + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) { // 정상 호출
                InputStream is = con.getInputStream();
                int read = 0;
                byte[] bytes = new byte[1024];
                // 랜덤한 이름으로 mp3 파일 생성

                if (oldName != null) {
                    File oldFile = new File("src\\main\\webapp\\media\\" + oldName + ".mp3");
                    if (oldFile.exists()) {
                        System.gc();
                        oldFile.delete();
                    }
                }
                String tempname = Long.valueOf(new Date().getTime()).toString();
                File f = new File("src\\main\\webapp\\media\\" + tempname + ".mp3");
                f.createNewFile();
                oldName = tempname;
                OutputStream outputStream = new FileOutputStream(f);
                while ((read = is.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                is.close();
                return tempname;
            } else {  // 오류 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
