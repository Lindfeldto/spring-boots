package com.example.democli2.app;

import java.io.InputStream;

public interface ArgumentResolver {
    Argument resolve(InputStream stream);
}
