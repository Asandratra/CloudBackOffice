package dao;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column{
    String name() default "";
    boolean primary() default false;
    boolean foreign() default false;
    boolean insert() default true;
    boolean considerate() default true;
    String setter();
    Class type();
    String getter();
}