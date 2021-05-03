package functionalinterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class _Supplier {

    public static void main(String[] args) {
        System.out.println(getDBConnectionUrl());
        System.out.println(getDBConnectionSupplier.get());
        System.out.println(getDBListConnectionsSupplier.get());
    }

    static String getDBConnectionUrl() {
        return "jdbs://localhost:4080";
    }

    static Supplier<String> getDBConnectionSupplier = ()
            -> "jdbs://localhost:4080";


    static Supplier<List<String>> getDBListConnectionsSupplier = ()
            -> Arrays.asList("jdbs://localhost:4080", "jdbs://localhost:4081");


}
