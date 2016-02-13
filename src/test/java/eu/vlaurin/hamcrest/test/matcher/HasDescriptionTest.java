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
import static org.mockito.Mockito.*;

/**
 * @since 1.0.0
 */
public class HasDescriptionTest {

    @Test(expected = IllegalArgumentException.class)
    public void expectedDescriptionIsNotNull() {
        hasDescription(null);
    }

    @Test
    public void isSafe() {
        assertThat(hasDescription("irrelevant"), is(nullSafe()));
        assertThat(hasDescription("irrelevant"), is(unknownTypeSafe()));
    }

    @Test
    public void testHasDescription() {
        final Matcher<Matcher<?>> matcher = hasDescription("hello world");

        assertThat(matcher, hasDescription("has description: \"hello world\""));
    }

    @Test
    public void testHasMismatchDescription() {
        final Matcher<?> matcher = mock(Matcher.class);
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                final Description description = (Description) invocationOnMock.getArguments()[0];
                description.appendText("other description");
                return null;
            }
        }).when(matcher)
          .describeTo(Mockito.any(Description.class));
        when(matcher.toString()).thenReturn("Mock for Matcher, hashCode: 967765295");

        assertThat(hasDescription("some description"), hasMismatchDescription("had description: <other description>", matcher));
    }

    @Test
    public void matchesDescription() {
        final Matcher<?> matcher = Mockito.mock(Matcher.class);
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                final Description description = (Description) invocationOnMock.getArguments()[0];
                description.appendText("hello world");
                return null;
            }
        }).when(matcher)
          .describeTo(Mockito.any(Description.class));

        assertThat(matcher, hasDescription("hello world"));
    }

    @Test
    public void doesNotMatchWrongDescription() {
        final Matcher<?> matcher = Mockito.mock(Matcher.class);

        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                final Description description = (Description) invocationOnMock.getArguments()[0];
                description.appendText("hi world");
                return null;
            }
        }).when(matcher)
          .describeTo(Mockito.any(Description.class));

        assertThat(matcher, not(hasDescription("hello world")));
    }
}