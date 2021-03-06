package io.github.zforgo.spring.exception.logging;

import org.slf4j.Logger;

import java.util.function.Function;

public enum LogLevel {

	TRACE(l -> l::trace, Logger::isTraceEnabled),
	DEBUG(l -> l::debug, Logger::isDebugEnabled),
	INFO(l -> l::info, Logger::isInfoEnabled),
	WARN(l -> l::warn, Logger::isWarnEnabled),
	ERROR(l -> l::error, Logger::isErrorEnabled),
	OFF(l -> LogLevel::noOp, l -> true);

	interface LogMethod {
		void log(String format, Throwable throwable);
	}

	private final Function<Logger, LogMethod> logMethod;
	private final Function<Logger, Boolean> isEnabledMethod;

	private static void noOp(String format, Throwable throwable) {

	}

	LogLevel(Function<Logger, LogMethod> logMethod, Function<Logger, Boolean> isEnabledMethod) {
		this.logMethod = logMethod;
		this.isEnabledMethod = isEnabledMethod;
	}

	public LogMethod prepare(Logger logger) {
		return logMethod.apply(logger);
	}

	public boolean isEnabled(Logger logger) {
		return isEnabledMethod.apply(logger);
	}
}
