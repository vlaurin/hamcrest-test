package eu.vlaurin.hamcrest.test.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.hamcrest.TypeSafeMatcher;

/**
 * Matches matchers with expected mismatch description.
 *
 * @since 1.0.0
 */
public final class HasMismatchDescription extends TypeSafeMatcher<Matcher<?>> {
    private final String expectedMismatch;
    private final Object actualArgument;

    private HasMismatchDescription(String expectedMismatch, Object actualArgument) {
        if (null == expectedMismatch) {
            throw new IllegalArgumentException("Non-null expected mismatch required by HasMismatchDescription");
        }
        this.expectedMismatch = expectedMismatch;
        this.actualArgument = actualArgument;
    }

    @Override
    protected boolean matchesSafely(Matcher<?> actualMatcher) {
        Boolean hasMismatch = false;
        if (!actualMatcher.matches(actualArgument)) {
            final StringDescription description = new StringDescription();
            actualMatcher.describeMismatch(actualArgument, description);
            final String actualMismatch = description.toString();
            hasMismatch = expectedMismatch.equals(actualMismatch);
        }
        return hasMismatch;
    }

    @Override
    protected void describeMismatchSafely(Matcher<?> actualMatcher, Description mismatchDescription) {
        if (!actualMatcher.matches(actualArgument)) {
            final StringDescription description = new StringDescription();
            actualMatcher.describeMismatch(actualArgument, description);
            mismatchDescription.appendText("had mismatch description: ")
                               .appendValue(description);
        } else {
            mismatchDescription.appendText("actual argument doesn't cause matcher to fail: ")
                               .appendValue(actualArgument);
        }
    }

    public void describeTo(Description description) {
        description.appendText("has mismatch description: ")
                   .appendValue(expectedMismatch);
    }

    /**
     * Creates a matcher of {@link Matcher} that matches when the examined matcher has the expected mismatch description.
     * For example:
     * <pre>
     *     Matcher<?> matcher = ...;
     *     Object actualArg = ...;
     *     assertThat(matcher, hasMismatchDescription("mismatch: explanation", actualArg));
     * </pre>
     *
     * @param expectedMismatch
     *         the description expected upon mismatch
     * @param actualArgument
     *         the argument causing the matcher to mismatch
     */
    public static Matcher<Matcher<?>> hasMismatchDescription(String expectedMismatch, Object actualArgument) {
        return new HasMismatchDescription(expectedMismatch, actualArgument);
    }
}
