package com.dmedeiros.meuorcamentosmvc.teste;

import java.util.Optional;
import java.util.stream.Stream;

public class Teste {

	public static void main(String[] args) {
//		boolean allNotNull = isAllNotNull("aaa", "adsfa");
//		boolean allNotNull = isAllNotNull("", "adsfa");
//		boolean allNotNull = isAllNotNull(null, "adsfa");
		isAllNotNull2(1, null, null);
		isAllNotNull2(2, null, "adsfa");
		isAllNotNull2(3, "ds", "adsfa");
		isAllNotNull2(4, "", "adsfa");
//		System.out.println(allNotNull);
	}
	

	public static void isAllNotNull2(int count, String... valor) {
		boolean x = Stream.of(valor).filter(v -> Optional.ofNullable(v).isPresent() && !v.isEmpty()).count() == valor.length;
		System.out.println("Cahamada " + count + " " + x);
				//.forEach(System.out::println);
	}
	public static boolean isAllNotNull(String... valor) {
		return 
		Stream.of(valor).filter(v -> Optional.ofNullable(v).isPresent()).count() == 0l;
	}

	
}
