package com.acme.architecture.core.context;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

public class RequestContext {

	private static final ThreadLocal<RequestContext> CONTEXT = new ThreadLocal<>();

	@Setter
	@Getter
	private String token;

	@Getter
	@Setter
	private UUID uuid = null;

	public static RequestContext getContext() {

		RequestContext result = CONTEXT.get();

		if (result == null) {
			result = new RequestContext();
			CONTEXT.set(result);
		}

		return result;
	}

	public static void unload() {
		CONTEXT.remove();
	}

}