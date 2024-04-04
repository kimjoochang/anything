package com.anything.login;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

@Service
@RequiredArgsConstructor
//@Transactional
public class LoginService implements ILoginService {
    // private final LoginRepository loginRepository;
    @Override
    public String getToken(String code) {
        String requestUrl = "https://kauth.kakao.com/oauth/token";
        String restApiKey = "9198d76291763c4e9833e8307e3449c6";
        String redirectUri = "http://localhost:8080/login/callback";

        String accessToken = "";
        String refreshToken = "";
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            conn.setRequestProperty("ContentType", "application/x-www-form-urlencoded;charset=utf-8");
            conn.setDoOutput(true);

            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=").append(restApiKey);
            sb.append("&redirect_uri=").append(redirectUri);
            sb.append("&code=").append(code);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            bw.write(sb.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();

            BufferedReader br;
            if (responseCode >= 200 && responseCode <= 300) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            try {
                String line = br.readLine();
                StringBuilder responseSb = new StringBuilder();
                if(line != null){
                    responseSb.append(line);
                }
                String response = responseSb.toString();

                JsonParser parser = new JsonParser();
                JsonElement element = parser.parse(response);
                accessToken = element.getAsJsonObject().get("access_token").getAsString();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
            finally {
                br.close();
                bw.close();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return accessToken;
    }

    @Override
    public void logout(String accessToken) {
        String logoutURL = "https://kapi.kakao.com/v1/user/logout";
        try {
            URL url = new URL(logoutURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("AUTHORIZATION", "Bearer" + accessToken);

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
