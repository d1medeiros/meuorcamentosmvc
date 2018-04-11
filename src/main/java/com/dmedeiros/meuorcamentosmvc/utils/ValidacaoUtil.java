package com.dmedeiros.meuorcamentosmvc.utils;

import java.util.Optional;
import java.util.stream.Stream;

public class ValidacaoUtil {
	
	public static boolean isAllNotNull(String... valor) {
		return Stream.of(valor)
				.filter(v -> Optional.ofNullable(v).isPresent() && !v.isEmpty())
				.count() == valor.length;
	}

}
