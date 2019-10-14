package com.example.democli.app;

import java.io.InputStream;
import java.util.Scanner;

public class ScannerArgumentResolver implements ArgumentResolver {

    @Override
    public Argument resolve(InputStream stream) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter 2 numbers like 'a b'");
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        scanner.close();

        return new Argument(a, b);
    }

}
