package eu.vlaurin.hamcrest.test.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static eu.vlaurin.hamcrest.test.TestMatchers.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * @since 1.0.0
 */
public class HasMismatchDescriptionTest {

    @Test(expected = IllegalArgumentException.class)
    public void expectedMismatchIsNotNull() {
        hasMismatchDescription(null, new Object());
    }

    @Test
    public void isSafe() {
        assertThat(hasMismatchDescription("irrelevant", null), is(nullSafe()));
        assertThat(hasMismatchDescription("irrelevant", null), is(unknownTypeSafe()));
    }

    @Test
    public void testHasDescription() {
        assertThat(hasMismatchDescription("hello world", null), hasDescription("has mismatch description: \"hello world\""));
    }

    @Test
    public void testHasMismatchDescription() {
        final Matcher<?> actualMatcher = mock(Matcher.class);
        when(actualMatcher.matches(null)).thenReturn(false);
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                final Description mismatchDescription = (Description) invocationOnMock.getArguments()[1];
                mismatchDescription.appendText("hello welt");
                return null;
            }
        }).when(actualMatcher)
          .describeMismatch(eq(null), Mockito.any(Description.class));

        assertThat(hasMismatchDescription("incorrect description", null), hasMismatchDescription("had mismatch description: <hello welt>", actualMatcher));
    }

    @Test
    public void testHasMismatchDescriptionWithIncorrectArgument() {
        final Matcher<?> actualMatcher = mock(Matcher.class);
        // Incorrect argument doesn't cause matcher to fail
        when(actualMatcher.matches(null)).thenReturn(true);

        assertThat(hasMismatchDescription("incorrect description", null), hasMismatchDescription("actual argument doesn't cause matcher to fail: null", actualMatcher));
    }

    @Test
    public void matchesMismatchDescription() {
        final Object actualArgument = "welt";
        final Matcher<?> matcher = mock(Matcher.class);
        when(matcher.matches(actualArgument)).thenReturn(false);
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                final Object item = invocationOnMock.getArguments()[0];
                final Description mismatchDescription = (Description) invocationOnMock.getArguments()[1];
                mismatchDescription.appendText("hello mismatch: ")
                                   .appendValue(item);
                return null;
            }
        }).when(matcher)
          .describeMismatch(Mockito.any(Object.class), Mockito.any(Description.class));

        assertThat(matcher, hasMismatchDescription("hello mismatch: \"welt\"", actualArgument));
    }

    @Test
    public void doesNotMatchWrongMismatchDescription() {
        final Object actualArgument = "welt";
        final Matcher<?> matcher = mock(Matcher.class);
        when(matcher.matches(actualArgument)).thenReturn(false);
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                final Description mismatchDescription = (Description) invocationOnMock.getArguments()[1];
                mismatchDescription.appendText("hello");
                return null;
            }
        }).when(matcher)
          .describeMismatch(Mockito.any(Object.class), Mockito.any(Description.class));

        assertThat(matcher, not(hasMismatchDescription("hello mismatch: \"welt\"", actualArgument)));
    }

    @Test
    public void doesNotMatchMatchingMatcher() {
        final Object actualArgument = "welt";
        final Matcher<?> matcher = mock(Matcher.class);
        when(matcher.matches(actualArgument)).thenReturn(true);
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                final Description mismatchDescription = (Description) invocationOnMock.getArguments()[1];
                mismatchDescription.appendText("irrelevant");
                return null;
            }
        }).when(matcher)
          .describeMismatch(Mockito.any(Object.class), Mockito.any(Description.class));

        assertThat(matcher, not(hasMismatchDescription("irrelevant", actualArgument)));
    }
}