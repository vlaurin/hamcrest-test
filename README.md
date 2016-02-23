# hamcrest-test [![Build Status](https://travis-ci.org/vlaurin/hamcrest-test.svg?branch=master)](https://travis-ci.org/vlaurin/hamcrest-test)
Library of matchers for testing Hamcrest matchers, based on Java Hamcrest.

Exposes as Hamcrest matchers the tests defined in Hamcrest Core's [AbstractMatcherTest](https://github.com/hamcrest/JavaHamcrest/blob/master/hamcrest-core/src/test/java/org/hamcrest/AbstractMatcherTest.java).

## Installation

From Maven Central:
```xml
<dependency>
    <groupId>eu.vlaurin.hamcrest</groupId>
    <artifactId>hamcrest-test</artifactId>
    <version>0.1.0</version>
    <scope>test</scope>
</dependency>
```

## Matchers

### nullSafe()
Check that a Hamcrest matcher can safely handle a `null` actual value.
```java
IMatcher matcher = is(true);

assertThat(matcher, is(nullSafe()));
```

### unknownTypeSafe()
Check that a Hamcrest matcher can safely handle an actual value of an unexpected type.
```java
IMatcher matcher = is(true);

assertThat(matcher, is(unknownTypeSafe()));
```

### hasDescription(description)
Check that a Hamcrest matcher describes itself with the expected description.
```java
IMatcher matcher = is(true);

assertThat(matcher, hasDescription("is true"));
```

### hasMismatchDescription(expectedMismatch, actualArgument)
Check that a Hamcrest matcher describes its mismatch with an actual argument with the expected message.
```java
IMatcher matcher = is(true);

assertThat(matcher, hasMismatchDescription("was false", false));
```