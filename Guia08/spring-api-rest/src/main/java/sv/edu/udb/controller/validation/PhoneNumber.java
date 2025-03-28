package sv.edu.udb.controller.validation;

import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface PhoneNumber {

    /* Error message.
            *
            * @return error message
    */
    String message() default "Invalid phone number";

    /**
     * REGEX pattern.
     *
     * @return REGEX pattern
     */
    String pattern() default "^\\+[1-9]\\d{1,14}$";
    /**
     * Classes group.
     *
     * @return groups
     */
    Class<?>[] groups() default {};
    /**
     * Payload method.
     *
     * @return payload defautl
     */
    Class<? extends Payload>[] payload() default {};
}
