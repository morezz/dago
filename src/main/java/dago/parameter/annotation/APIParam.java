package dago.parameter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * APIParam
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface APIParam {
    enum FROM {
        PATH, QUERY, HEADER, BODY, NULL
    }

    String name() default "";

    FROM from() default FROM.NULL;

    boolean nullable() default false;


}
