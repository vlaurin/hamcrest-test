package eu.vlaurin.hamcrest.test.matcher;

import eu.vlaurin.hamcrest.test.TestMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

import static eu.vlaurin.hamcrest.test.TestMatchers.nullSafe;
import static eu.vlaurin.hamcrest.test.TestMatchers.unknownTypeSafe;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @since 1.0.0
 */
public class IsUnknownTypeSafeTest {

    @Test
    public void isNullSafe() {
        assertThat(unknownTypeSafe(), is(nullSafe()));
    }

    @Test
    public void isUnknownTypeSafe() {
        assertThat(unknownTypeSafe(), is(unknownTypeSafe()));
    }

    @Test
    public void matchesUnknownTypeSafe() {
        final Matcher<?> typeSafeMatcher = mock(Matcher.class);
        when(typeSafeMatcher.matches(Mockito.any())).thenReturn(false);

        assertThat(typeSafeMatcher, is(unknownTypeSafe()));
    }

    @Test
    public void doesNotMatchNotUnknownTypeSafe() {
        final Matcher<?> typeSafeMatcher = mock(Matcher.class);
        when(typeSafeMatcher.matches(Mockito.any())).thenThrow(Exception.class);

        assertThat(typeSafeMatcher, is(not(unknownTypeSafe())));
    }
}