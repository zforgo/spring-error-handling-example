package io.github.zforgo.spring.exception.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Logged.List.class)
public @interface Logged {
	String msg();

	Class<? extends Throwable> cls();

	LogLevel level() default LogLevel.ERROR;

	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.METHOD})
	@interface List {
		Logged[] value();
	}
}
