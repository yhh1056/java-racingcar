package racingcar;

import java.util.Objects;

public class Name {
    private static final int MAXIMUM_LENGTH = 5;

    private final String name;

    public Name(String name) {
        name = removeWhiteSpaces(name);
        validateLength(name);
        this.name = name;
    }

    private void validateLength(String name) {
        if (name.length() > MAXIMUM_LENGTH) {
            throw new IllegalArgumentException(String.format("이름은 %d글자가 넘을 수 없습니다. :%d", MAXIMUM_LENGTH, name.length()));
        }
    }

    private String removeWhiteSpaces(String name) {
        return name.trim();
    }

    public static Name of(String name) {
        return new Name(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Name)) {
            return false;
        }
        Name name = (Name)o;
        return Objects.equals(this.name, name.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
