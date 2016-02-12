package eu.vlaurin.hamcrest.test.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.hamcrest.TypeSafeMatcher;

/**
 * Matches matchers with expected description.
 *
 * @since 1.0.0
 */
public final class HasDescription extends TypeSafeMatcher<Matcher<?>> {
    private final String expectedDescription;

    private HasDescription(String expectedDescription) {
        if (null == expectedDescription) {
            throw new IllegalArgumentException("Non-null value required by HasDescription");
        }
        this.expectedDescription = expectedDescription;
    }

    @Override
    protected boolean matchesSafely(Matcher<?> matcher) {
        final StringDescription description = new StringDescription();
        matcher.describeTo(description);
        final String actualDescription = description.toString();

        return expectedDescription.equals(actualDescription);
    }

    @Override
    protected void describeMismatchSafely(Matcher<?> item, Description mismatchDescription) {
        super.describeMismatchSafely(item, mismatchDescription);
    }

    public void describeTo(Description description) {
        description.appendText("has description: ")
                   .appendValue(expectedDescription);
    }

    /**
     * Creates a matcher of {@link Matcher} that matches when the examined matcher has the expected description.
     * For example:
     * <pre>
     *     final Matcher<Matcher<?>> matcher = hasDescription("hello world");
     *     assertThat(matcher, hasDescription("has description: \"hello world\""));
     * </pre>
     *
     * @param expectedDescription
     *         the description that must be provided by the examined matcher
     */
    public static final Matcher<Matcher<?>> hasDescription(String expectedDescription) {
        return new HasDescription(expectedDescription);
    }
}
