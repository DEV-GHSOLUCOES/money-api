package com.gh.ghmoney.api.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GhMoneyExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String menssagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String menssagemDesenvolvedor = ex.getCause().toString();
		return handleExceptionInternal(ex, new Erro(menssagemUsuario, menssagemDesenvolvedor), headers,
				HttpStatus.BAD_REQUEST, request);
	}

	public static class Erro {

		private String menssagemUsuario;
		private String menssagemDesenvolvedor;

		public Erro(String menssagemUsuario, String menssagemDesenvolvedor) {

			this.menssagemUsuario = menssagemUsuario;
			this.menssagemDesenvolvedor = menssagemDesenvolvedor;
		}

		public String getMenssagemUsuario() {
			return menssagemUsuario;
		}

		public void setMenssagemUsuario(String menssagemUsuario) {
			this.menssagemUsuario = menssagemUsuario;
		}

		public String getMenssagemDesenvolvedor() {
			return menssagemDesenvolvedor;
		}

		public void setMenssagemDesenvolvedor(String menssagemDesenvolvedor) {
			this.menssagemDesenvolvedor = menssagemDesenvolvedor;
		}

	}

}
