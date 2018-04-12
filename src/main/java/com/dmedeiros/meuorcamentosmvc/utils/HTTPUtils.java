
package com.dmedeiros.meuorcamentosmvc.utils;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class HTTPUtils {

	public static Response generateResponse(boolean validar) {
		if(validar) {
			return Response.ok().build();
		}else {
			return Response.status(Status.FORBIDDEN).build();
		}
	}

	public static Response generateResponse(boolean validar, String response) {
		if(validar) {
			return Response.ok(response).build();
		}else {
			return Response.status(Status.FORBIDDEN).build();
		}
	}

}
