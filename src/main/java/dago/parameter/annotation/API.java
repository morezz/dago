package dago.parameter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * API
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface API {


    enum AuthorizeType {
        ADMIN_ROLE,
        USER_ROLE,
        ANONYMOUS;
    }

    AuthorizeType authorizeType() default AuthorizeType.ANONYMOUS;

    APIParam[] params() default {};

    Class<?> entity() default Object.class;

}
