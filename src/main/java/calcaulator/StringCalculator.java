package calcaulator;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    private static final int INDEX_OF_TEXT = 2;
    private static final int INDEX_OF_DELIMITER = 1;

    private static final int MINIMUM_NUMBER = 0;
    private static final int MINIMUM_LENGTH = 1;
    private static final int DEFAULT_VALUE_FOR_NULL_OR_EMPTY = 0;

    private static final String REGEX = ",|:";
    private static final Pattern pattern = Pattern.compile("//(.)\\\\n(.*)");

    public static int addString(String text) {
        if (isNullOrEmpty(text)) {
            return DEFAULT_VALUE_FOR_NULL_OR_EMPTY;
        }

        if (hasSingleNumber(text)) {
            return isMinusNumber(Integer.parseInt(text));
        }

        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return getResult(getStrings(matcher));
        }

        return getResult(text.split(REGEX));
    }

    private static boolean isNullOrEmpty(String text) {
        return Objects.isNull(text) || text.isEmpty();
    }

    private static boolean hasSingleNumber(String text) {
        return text.length() == MINIMUM_LENGTH;
    }

    private static int isMinusNumber(int number) {
        if (number < MINIMUM_NUMBER) {
            throw new RuntimeException("음수를 입력할 수 없습니다.");
        }

        return number;
    }

    private static String[] getStrings(Matcher matcher) {
        String delimiter = matcher.group(INDEX_OF_DELIMITER);
        return matcher.group(INDEX_OF_TEXT).split(delimiter);
    }

    private static int getResult(String[] numbersOfString) {
        return Arrays.stream(numbersOfString)
            .mapToInt(number -> isMinusNumber(Integer.parseInt(number)))
            .sum();
    }
}
