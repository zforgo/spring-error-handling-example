package io.github.zforgo.spring.exception.logging;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.util.Optional;

class LoggerInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		final var method = methodInvocation.getMethod();
		return !AnnotatedElementUtils.findMergedRepeatableAnnotations(method, Logged.class).isEmpty()
				? proceedInvocation(methodInvocation)
				: methodInvocation.proceed();
	}

	private Object proceedInvocation(MethodInvocation methodInvocation) throws Throwable {
//		TODO resolve params
//		TODO MDC support
		try {
			return methodInvocation.proceed();
		} catch (Throwable t) {
			final var logger = LoggerFactory.getLogger(methodInvocation.getMethod().getDeclaringClass());
			findAnnotation(methodInvocation, t)
					.ifPresent(ann -> logMessage(ann, logger, t));
			throw t;
		}
	}

	private void logMessage(Logged ann, Logger logger, Throwable t) {
		if (ann.level().isEnabled(logger)) {
			ann.level().prepare(logger).log(ann.msg(), t);
		}
	}

	private Optional<Logged> findAnnotation(MethodInvocation methodInvocation, Throwable t) {
		return AnnotatedElementUtils.findMergedRepeatableAnnotations(methodInvocation.getMethod(), Logged.class)
				.stream()
				.filter(ann -> ann.cls().isAssignableFrom(t.getClass()))
				.map(ann -> Pair.of(ann, calculateDistance(t.getClass(), ann.cls())))
				.reduce((curr, next) -> next.getValue() > curr.getValue() ? curr : next)
				.map(Pair::getKey);

	}

	private static int calculateDistance(Class<? extends Throwable> source, Class<? extends Throwable> target) {
		return calculateDistance(source, target, 0);
	}

	private static int calculateDistance(Class<? extends Throwable> source, Class<? extends Throwable> target, int distance) {
		if (source.equals(target)) {
			return distance;
		}
		if (source.equals(Throwable.class)) {
			return Integer.MAX_VALUE;
		}
		return calculateDistance(source.getSuperclass().asSubclass(Throwable.class), target, ++distance);
	}
}
/*
	.filter(c -> c.isAssignableFrom(ex.getClass()))
				.map(cls -> Pair.of(cls, calculateDistance(ex.getClass(), cls)))
				.reduce((curr, next) -> next.getValue() > curr.getValue() ? curr : next);
 */