package com.ytying.compiler;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

/**
 * Created by kefan.wkf on 17/1/28.
 * JavaCompiler所需JavaFileObject
 */
public class JavaSourceFromString extends SimpleJavaFileObject {
    final String code;

    public JavaSourceFromString(String name, String code) {
        super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
        this.code = code;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
    }
}

