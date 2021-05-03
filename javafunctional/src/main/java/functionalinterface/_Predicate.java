package functionalinterface;

import java.util.function.Predicate;

public class _Predicate {
    public static void main(String[] args) {

        System.out.println("Without Predicate");
        System.out.println(isPhoneNumberValid("+3983737373"));
        System.out.println(isPhoneNumberValid("3983737373"));
        System.out.println(isPhoneNumberValid("33983737373"));

        System.out.println("With Predicate");
        System.out.println(isPhoneNumberValidPredicate.test("+3983737373"));
        System.out.println(isPhoneNumberValidPredicate.test("3983737373"));
        System.out.println(isPhoneNumberValidPredicate.test("33983737373"));

        System.out.println(isPhoneNumberValidPredicate.and(containsNumber3).test("+3983737373"));
    }

    static boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.startsWith("+") && phoneNumber.length() == 11;
    }

    static Predicate<String> isPhoneNumberValidPredicate = phoneNumber ->
            phoneNumber.startsWith("+") && phoneNumber.length() == 11;

    static Predicate<String> containsNumber3 = phoneNumber -> phoneNumber.contains("3");
}
