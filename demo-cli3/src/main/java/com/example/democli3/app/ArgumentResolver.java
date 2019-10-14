package com.example.democli3.app;

import java.io.InputStream;

public interface ArgumentResolver {
    Argument resolve(InputStream stream);
}
