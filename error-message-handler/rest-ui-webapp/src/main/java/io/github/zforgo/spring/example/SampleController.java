package io.github.zforgo.spring.example;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@Validated
public class SampleController {
	private final SampleService service;

	public SampleController(SampleService service) {
		this.service = service;
	}

	//	MissingServletRequestParameterException (unhandled)
	@GetMapping
	public String requestParam(@RequestParam(name = "param") String param) {
		return "bar";
	}

	//	ConstraintViolationException (handled)
	@GetMapping("/requiredString")
	public String requiredString(@RequestParam(name = "param", required = false) @NotNull String param) {
		return "bar";
	}

	//	ConstraintViolationException (handled)
	@GetMapping("/intInterval")
	public String intInterval(@RequestParam(name = "param", required = false) @NotNull @Min(10) @Max(20) Integer param) {
		return "bar";
	}

	//	ConstraintViolationException (handled)
	@GetMapping("/intService")
	public String intService(@RequestParam(name = "param", required = false) Integer param) {
		service.handleInteger(param);
		return "bar";
	}

//	MethodArgumentNotValidException (unhandled)
	@PostMapping("/child")
	public String child(@Valid @RequestBody Child child) {
		return "bar";
	}

	@PostMapping("/parent")
	public String parent(@Valid @RequestBody Parent parent) {
		return "bar";
	}

	@GetMapping("/service")
	public String callService() {
		service.logged();
		return "bar";
	}
}
