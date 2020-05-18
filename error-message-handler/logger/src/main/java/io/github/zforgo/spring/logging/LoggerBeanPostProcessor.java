package io.github.zforgo.spring.logging;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

@Component
class LoggerBeanPostProcessor implements BeanPostProcessor {
	private static final Predicate<Object> hasLoggedMethod = bean -> Stream.of(ReflectionUtils.getAllDeclaredMethods(bean.getClass()))
			.anyMatch(method -> !AnnotatedElementUtils.findMergedRepeatableAnnotations(method, Logged.class).isEmpty());

	private static final UnaryOperator<Object> proxy = bean -> {
		var factory = new ProxyFactory(bean);
		factory.addAdvice(new LoggerInterceptor());
		factory.setProxyTargetClass(true);
		return factory.getProxy();
	};

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return Optional.of(bean)
				.filter(hasLoggedMethod)
				.map(proxy)
				.orElse(bean);
	}
}
