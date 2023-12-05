package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        MethodSignature methodSignature;
        List<String> delimiters = List.of(" ", "(", ")", ",");
        StringSplitter stringSplitter = new StringSplitter();
        List<String> methodSignatureList = stringSplitter.splitByDelimiters(signatureString, delimiters);
        String accessModifier = methodSignatureList.get(0);
        String returnType = methodSignatureList.get(1);
        String methodName = methodSignatureList.get(2);
        List<MethodSignature.Argument> argumentList = new ArrayList<>();
        if (accessModifier.equals("private") || accessModifier.equals("public")
                || accessModifier.equals("protected")) {
            for (int i = 3; i < methodSignatureList.size() - 1; i += 2) {
                MethodSignature.Argument argument =
                        new MethodSignature.Argument(methodSignatureList.get(i), methodSignatureList.get(i + 1));
                argumentList.add(argument);
            }
            methodSignature = new MethodSignature(methodName, argumentList);
            methodSignature.setAccessModifier(accessModifier);
            methodSignature.setReturnType(returnType);
        } else {
            methodName = methodSignatureList.get(1);
            methodSignature = new MethodSignature(methodName, argumentList);
            methodSignature.setAccessModifier(null);
            methodSignature.setMethodName(methodSignatureList.get(1));
            methodSignature.setReturnType(methodSignatureList.get(0));
            for (int i = 2; i < methodSignatureList.size() - 1; i += 2) {
                MethodSignature.Argument argument =
                        new MethodSignature.Argument(methodSignatureList.get(i), methodSignatureList.get(i + 1));
                argumentList.add(argument);
            }
        }
        return methodSignature;
    }
}
