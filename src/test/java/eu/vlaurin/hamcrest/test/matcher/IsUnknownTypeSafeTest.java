package eu.vlaurin.hamcrest.test.matcher;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.mockito.Mockito;

import static eu.vlaurin.hamcrest.test.TestMatchers.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
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
    public void testHasDescription() {
        assertThat(unknownTypeSafe(), hasDescription("an unknown type safe matcher"));
    }

    @Test
    public void testHasMismatchDescription() {
        final Matcher<?> matcher = mock(Matcher.class);
        when(matcher.matches(Mockito.any())).thenThrow(Exception.class);
        when(matcher.toString()).thenReturn("Mock for Matcher, hashCode: 967765295");

        assertThat(unknownTypeSafe(), hasMismatchDescription("was not an unknown type safe matcher: <Mock for Matcher, hashCode: 967765295>", matcher));
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