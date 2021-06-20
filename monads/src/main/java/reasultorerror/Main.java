package reasultorerror;

public class Main {

    public static monads.reasultorerror.ResultOrError<String, monads.reasultorerror.SimpleError> trim(String string) {

        String r = string.trim();
        if (!r.isEmpty()) {
            return monads.reasultorerror.ResultOrError.createResult(r);
        } else {
            return monads.reasultorerror.ResultOrError.createError(new monads.reasultorerror.SimpleError("String must contain non-space characters."));
        }
    }

    public static monads.reasultorerror.ResultOrError<String, monads.reasultorerror.SimpleError> toUpperCase(String string) {

        if (!string.matches("[a-zA-Z ]+")) {
            return monads.reasultorerror.ResultOrError.createError(new monads.reasultorerror.SimpleError("String must contain only letters."));
        }

        return monads.reasultorerror.ResultOrError.createResult(string.toUpperCase());
    }

    public static monads.reasultorerror.ResultOrError<String, monads.reasultorerror.SimpleError> appendExclam(String string) {

        if (string.length() > 20) {
            return monads.reasultorerror.ResultOrError.createError(new monads.reasultorerror.SimpleError("String must not exceed 20 characters."));
        }

        return monads.reasultorerror.ResultOrError.createResult(string.concat("!"));
    }

    static monads.reasultorerror.ResultOrError<String, monads.reasultorerror.SimpleError> enthuse_1(String sentence) {

        monads.reasultorerror.ResultOrError<String, monads.reasultorerror.SimpleError> trimmed = trim(sentence);
        monads.reasultorerror.ResultOrError<String, monads.reasultorerror.SimpleError> upperCased = trimmed.bind(Main::toUpperCase);
        monads.reasultorerror.ResultOrError<String, monads.reasultorerror.SimpleError> result = upperCased.bind(Main::appendExclam);

        return result;
    }

    static monads.reasultorerror.ResultOrError<String, monads.reasultorerror.SimpleError> enthuse_2(String sentence) {

        monads.reasultorerror.ResultOrError<String, monads.reasultorerror.SimpleError> trimmed = trim(sentence);
        monads.reasultorerror.ResultOrError<String, monads.reasultorerror.SimpleError> upperCased = trimmed.bind(Main::toUpperCase);
        return upperCased.bind(Main::appendExclam);
    }

    static monads.reasultorerror.ResultOrError<String, monads.reasultorerror.SimpleError> enthuse_3(String sentence) {

        return trim(sentence).bind(Main::toUpperCase).bind(Main::appendExclam);
    }

    static monads.reasultorerror.ResultOrError<String, monads.reasultorerror.SimpleError> enthuse_4(String sentence) {

        return trim(sentence).bind(Main::toUpperCase).bind(Main::appendExclam);
    }

    private static void test(String sentence) {

        System.out.println(enthuse_1(sentence));
        System.out.println(enthuse_2(sentence));
        System.out.println(enthuse_3(sentence));
        System.out.println(enthuse_4(sentence));
    }

    public static void main(String[] args) {
        test("  Hello bob  ");
        test("   ");
        test("hello 123");
        test("Krungthepmahanakhon is the capital of Thailand");
    }
}
