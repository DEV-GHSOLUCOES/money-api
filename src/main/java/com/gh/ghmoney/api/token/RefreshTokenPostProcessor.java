package com.gh.ghmoney.api.token;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.gh.ghmoney.api.config.property.GhMoneyApiProperty;

@SuppressWarnings("deprecation")
@ControllerAdvice
public class RefreshTokenPostProcessor  implements ResponseBodyAdvice<OAuth2AccessToken>{
	
	
	@Autowired
	private GhMoneyApiProperty ghMoneyApiProperty;

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {

		return returnType.getMethod().getName().equals("postAccessToken");
	}
	
	@Override
	public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken body, MethodParameter returnType,
			MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
			ServerHttpRequest request, ServerHttpResponse response) {
		
		HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
		HttpServletResponse res = ((ServletServerHttpResponse) response).getServletResponse();
		DefaultOAuth2AccessToken accessToken  = (DefaultOAuth2AccessToken) body;
		
		String refreshToken =  body.getRefreshToken().getValue();
		adicionarRefreshTokenNoCookie(refreshToken, req, res);
		removerRefreshTokenCookieDoBody(accessToken);
		
		return body;
	}

	private void removerRefreshTokenCookieDoBody(DefaultOAuth2AccessToken accessToken) {
		accessToken.setRefreshToken(null);
		
	}

	private void adicionarRefreshTokenNoCookie(String refreshToken, HttpServletRequest req, HttpServletResponse res) {
		Cookie refreshTokenCookie =  new Cookie("refreshToken", refreshToken);
		refreshTokenCookie.setHttpOnly(true);
		refreshTokenCookie.setSecure(ghMoneyApiProperty.getSeguranca().isEnableHttps()); 
		refreshTokenCookie.setPath(req.getContextPath() + "/oauth/token");
		refreshTokenCookie.setMaxAge(2592000);
		res.addCookie(refreshTokenCookie);
	}
}
