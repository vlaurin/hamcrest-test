package eu.vlaurin.hamcrest.test.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Matches unknown type safe matchers.
 *
 * @since 0.1.0
 */
public final class IsUnknownTypeSafe extends TypeSafeMatcher<Matcher<?>> {
    private static final IsUnknownTypeSafe INSTANCE = new IsUnknownTypeSafe();

    private IsUnknownTypeSafe() {
    }

    @Override
    protected boolean matchesSafely(Matcher<?> matcher) {
        Boolean unknownTypeSafe = true;
        try {
            matcher.matches(new UnknownType());
        } catch (Exception e) {
            unknownTypeSafe = false;
        }
        return unknownTypeSafe;
    }

    @Override
    protected void describeMismatchSafely(Matcher<?> actual, Description mismatchDescription) {
        mismatchDescription.appendText("was not an unknown type safe matcher: ")
                           .appendValue(actual);
    }

    public void describeTo(Description description) {
        description.appendText("an unknown type safe matcher");
    }

    /**
     * Creates a matcher of {@link Matcher} that matches when the examined matcher is unknown type safe. For example:
     * <pre>
     * {@code
     *     assertThat(matcher, is(unknownTypeSafe()));
     * }
     * </pre>
     *
     * @return Instance of {@link IsUnknownTypeSafe} matcher
     */
    public static Matcher<Matcher<?>> unknownTypeSafe() {
        return INSTANCE;
    }

    private static class UnknownType {
    }
}
