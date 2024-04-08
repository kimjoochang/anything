package com.anything.login;

import lombok.Data;
/*
"access_token":"NhFpNBiXtkKY8UQStUqvaTNFix4sKTRCBK8KPXPsAAABjr4XHEOIenTzhLqDRQ",
"token_type":"bearer",
"refresh_token":"lD99PgssGS2jhzf6_kTM8XNnwY-ZYj7GRSgKPXPsAAABjr4XHD-IenTzhLqDRQ",
"id_token":"eyJraWQiOiI5ZjI1MmRhZGQ1ZjIzM2Y5M2QyZmE1MjhkMTJmZWEiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI5MTk4ZDc2MjkxNzYzYzRlOTgzM2U4MzA3ZTM0NDljNiIsInN1YiI6IjM0MTQwMDE0NzAiLCJhdXRoX3RpbWUiOjE3MTI1ODYxNjksImlzcyI6Imh0dHBzOi8va2F1dGgua2FrYW8uY29tIiwibmlja25hbWUiOiLquYDso7zssL0iLCJleHAiOjE3MTI2MDc3NjksImlhdCI6MTcxMjU4NjE2OSwiZW1haWwiOiJraW1qYzA3MDdAbmF2ZXIuY29tIn0.lWvQbjLkjEFO0MMb9tm8E3B1-mhw-tx6vPyjhbCgzJKuQ8wy15dJ4TJikE9BYZq7D8yxv-kJXi8Mb_RB6htQFsxBEcZ6aGE6WLMwfST6sLswaoy5Ocm7Pb162iR_ID2Dpw7mXFP4cy2AyM1ykXPf3h2N_Rarz7xuOlfYUaFtZaaB063LFCA1AQUcUu8OnVx-nu0Qhyu8JKIeDG-gnwCaAfyMuzWV8BFfboflzt1lDZFnAYvGVlddHTv0O4G1jlQd3qIB9wLa72zQJXpqzCKLiYWYBb7LsYu_YdIzYtFs4AmsVj_EuXSf2Nq1tFnyTqahcfwLLcfHxVSAr1_wf2bWRQ",
"expires_in":21599,
"scope":"account_email talk_message openid profile_nickname",
"refresh_token_expires_in":5183999},
[Date:"Mon, 08 Apr 2024 14:22:49 GMT", Content-Type:"application/json;charset=utf-8", Transfer-Encoding:"chunked", Connection:"keep-alive", Cache-Control:"no-cache, no-store, max-age=0, must-revalidate", Pragma:"no-cache", Expires:"0", X-XSS-Protection:"1; mode=block", X-Frame-Options:"DENY", X-Content-Type-Options:"nosniff", Kakao:"Talk", Access-Control-Allow-Origin:"*", Access-Control-Allow-Methods:"GET, POST, OPTIONS", Access-Control-Allow-Headers:"Authorization, KA, Origin, X-Requested-With, Content-Type, Accept"]>
* */
@Data
public class OauthToken {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private int refresh_token_expries_in;
}
