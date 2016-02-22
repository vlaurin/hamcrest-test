package eu.vlaurin.hamcrest.test.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Matches null-safe matchers.
 *
 * @since 0.1.0
 */
public final class IsNullSafe extends TypeSafeMatcher<Matcher<?>> {
    private static final IsNullSafe INSTANCE = new IsNullSafe();

    private IsNullSafe() {
    }

    @Override
    protected boolean matchesSafely(Matcher<?> matcher) {
        Boolean nullSafe = true;
        try {
            matcher.matches(null);
        } catch (Exception e) {
            nullSafe = false;
        }
        return nullSafe;
    }

    @Override
    protected void describeMismatchSafely(Matcher<?> actual, Description mismatchDescription) {
        mismatchDescription.appendText("was not a null safe matcher: ")
                           .appendValue(actual);
    }

    public void describeTo(Description description) {
        description.appendText("a null safe matcher");
    }

    /**
     * Creates a matcher of {@link Matcher} that matches when the examined matcher is null-safe. For example:
     * <pre>
     * {@code
     *     assertThat(matcher, is(nullSafe()));
     * }
     * </pre>
     *
     * @return Instance of {@link IsNullSafe} matcher
     */
    public static Matcher<Matcher<?>> nullSafe() {
        return INSTANCE;
    }
}
