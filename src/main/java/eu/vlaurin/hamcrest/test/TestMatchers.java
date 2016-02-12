package eu.vlaurin.hamcrest.test;

import eu.vlaurin.hamcrest.test.matcher.HasDescription;
import eu.vlaurin.hamcrest.test.matcher.IsNullSafe;
import eu.vlaurin.hamcrest.test.matcher.IsUnknownTypeSafe;
import org.hamcrest.Matcher;

/**
 * Provides easy access to all the matchers defined in <strong>hamcrest-test</strong>.
 *
 * @since 1.0.0
 */
public class TestMatchers {

    /**
     * Creates a matcher of {@link Matcher} that matches when the examined matcher is null-safe.
     * For example:
     * <pre>
     *     assertThat(matcher, is(nullSafe()));
     * </pre>
     */
    public static Matcher<Matcher<?>> nullSafe() {
        return IsNullSafe.nullSafe();
    }

    /**
     * Creates a matcher of {@link Matcher} that matches when the examined matcher is unknown type safe.
     * For example:
     * <pre>
     *     assertThat(matcher, is(unknownTypeSafe()));
     * </pre>
     */
    public static Matcher<Matcher<?>> unknownTypeSafe() {
        return IsUnknownTypeSafe.unknownTypeSafe();
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
    public static Matcher<Matcher<?>> hasDescription(String expectedDescription) {
        return HasDescription.hasDescription(expectedDescription);
    }
}
