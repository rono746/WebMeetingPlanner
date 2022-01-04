package com.example.WebMeetingPlanner.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateOrderValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateOrderCheck {
	String startDateField();

	String endDateField();

	String message() default "Start Date is greater than End Date!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
