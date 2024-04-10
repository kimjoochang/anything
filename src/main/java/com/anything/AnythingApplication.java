package com.anything;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class AnythingApplication {

	public static void main(String[] args) {
	 SpringApplication.run(AnythingApplication.class, args);
	}
/*
	public static void main(String[] args) {
		String sendApiUrl = "https://kapi.kakao.com/v2/user/me";
		// 로그아웃 전 토큰
		String accessToken = "GFep-CfCokGKKbJK6KqkSwAIi_qGKqYWigQKKiUNAAABjrkJoc2IenTzhLqDRQ";
		// 최신 토큰
		// String accessToken = "RzJU78yLclAqG2p-5O-rTzTwKWquNhRq01sKKwynAAABjqmltTaIenTzhLqDRQ";
		HttpHeaders header = new HttpHeaders();

		header.set("Authorization", "Bearer " + accessToken);
		header.set("Content-Type", "application/x-www-form-urlencoded");
		header.add("Accept", "application/json");

		HttpEntity<?> requestEntity = new HttpEntity<>(header);
		ResponseEntity<String> response = new RestTemplate().exchange(sendApiUrl, HttpMethod.GET, requestEntity,String.class);
		System.out.println(response.toString());
	}

 */
}
