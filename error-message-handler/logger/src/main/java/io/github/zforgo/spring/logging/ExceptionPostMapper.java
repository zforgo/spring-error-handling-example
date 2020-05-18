package io.github.zforgo.spring.logging;

public interface ExceptionPostMapper<T extends Throwable> {

	default Throwable remap(T throwable) {
		return throwable;
	}
}
