package reasultorerror;

public class SimpleError {

    private final String info;

    public SimpleError ( String info ) {
        this.info = info;
    }

    public String getInfo() { return info; }

    public String toString() { return info; }
}