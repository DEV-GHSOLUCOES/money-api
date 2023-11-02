package com.gh.ghmoney.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("ghmoney")
public class GhMoneyApiProperty {
	
	private final Seguranca seguranca = new Seguranca();
	
	private String originPermitida = "http://localhost:8000";
	
	public Seguranca getSeguranca() {
		return seguranca;
	}
	
	public static class Seguranca{
		
		private boolean enableHttps;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}
		
		
	}

	public String getOriginPermitida() {
		return originPermitida;
	}

	public void setOriginPermitida(String originPermitida) {
		this.originPermitida = originPermitida;
	}
	
	

}
