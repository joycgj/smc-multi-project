package com.google.common.joy;

import java.util.Iterator;

import static com.google.common.base.Preconditions.checkNotNull;

public class Joiner {
    public static Joiner on(String separator) {
        return new Joiner(separator);
    }

    public static Joiner on(char separator) {
        return new Joiner(String.valueOf(separator));
    }

    private final String separator;

    private Joiner(String separator) {
        this.separator = checkNotNull(separator);
    }

    private Joiner(Joiner prototype) {
        this.separator = prototype.separator;
    }

    public <A extends Appendable> A appendTo(A appendable, Iterator<?> parts) {
        checkNotNull(appendable);
        if (parts.hasNext()) {
            appendable.append()
        }
    }
}
