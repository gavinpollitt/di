package uk.gav.game.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

/**
 * 
 * @author regen
 *
 * Annotation used to represent the number of sides the injected dice should have
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface Sides {

}
