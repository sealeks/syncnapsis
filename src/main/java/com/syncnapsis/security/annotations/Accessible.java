package com.syncnapsis.security.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation indicating the accessibility of a Field or Method.<br>
 * Used for (De)-Serialization or Method Invocation there are different ways to annotate Elements:<br>
 * <br>
 * (De)-Serialization:<br>
 * <ul>
 * <li>Annotation on Getter --> Accessible defines Authorities for READ</li>
 * <li>Annotation on Setter --> Accessible defines Authorities for WRITE</li>
 * <li>Annotation on Field --> Accessible defines Authorities for READ AND WRITE</li>
 * <li>(If Getter is annotated Field will be ignored for Serialization)</li>
 * <li>(If Setter is annotated Field will be ignored for Deserialization)</li>
 * </ul>
 * <br>
 * Method Invocation:<br>
 * <ul>
 * <li>Annotation on Method --> General Accessibility defined by Annotation</li>
 * </ul>
 * 
 * @author ultimate
 */
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Accessible
{
	Authority[] accessible() default {};

	Authority[] notAccessible() default {};

	boolean defaultAccessible() default true;
}
