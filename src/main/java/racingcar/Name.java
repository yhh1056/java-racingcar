package racingcar;

import java.util.Objects;

public class Name {
    private final String name;

    public Name(String name) {
        name = removeWhiteSpaces(name);
        this.name = name;
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
