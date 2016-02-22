package eu.vlaurin.hamcrest.test.matcher;

import org.hamcrest.Matcher;
import org.junit.Test;

import static eu.vlaurin.hamcrest.test.TestMatchers.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @since 0.1.0
 */
public class IsNullSafeTest {

    @Test
    public void isNullSafe() {
        assertThat(nullSafe(), is(nullSafe()));
    }

    @Test
    public void isUnknownTypeSafe() {
        assertThat(nullSafe(), is(unknownTypeSafe()));
    }

    @Test
    public void testHasDescription() {
        assertThat(nullSafe(), hasDescription("a null safe matcher"));
    }

    @Test
    public void testHasMismatchDescription() {
        final Matcher<?> matcher = mock(Matcher.class);
        when(matcher.matches(null)).thenThrow(Exception.class);
        when(matcher.toString()).thenReturn("Mock for Matcher, hashCode: 967765295");

        assertThat(nullSafe(), hasMismatchDescription("was not a null safe matcher: <Mock for Matcher, hashCode: 967765295>", matcher));
    }

    @Test
    public void matchesNullSafe() {
        final Matcher<?> nullSafeMatcher = mock(Matcher.class);
        when(nullSafeMatcher.matches(null)).thenReturn(false);

        assertThat(nullSafeMatcher, is(nullSafe()));
    }

    @Test
    public void doesNotMatchNotNullSafe() {
        final Matcher<?> notNullSafeMatcher = mock(Matcher.class);
        when(notNullSafeMatcher.matches(null)).thenThrow(Exception.class);

        assertThat(notNullSafeMatcher, is(not(nullSafe())));
    }

}