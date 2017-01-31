package com.ytying.compiler;

import com.sun.org.apache.bcel.internal.classfile.Code;
import com.ytying.controller.CodeController;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by kefan.wkf on 17/1/27.
 * 标准输出重定向到StringBuilder中
 */
public class CompilerPrintStream extends PrintStream {

    private StringBuilder compileResult;

    public CompilerPrintStream(OutputStream out, StringBuilder compileResult) {
        super(out);
        this.compileResult = compileResult;
    }

    @Override
    public void write(byte[] buf, int off, int len) {
        final String message = new String(buf, off, len);
        compileResult.append(message);
    }
}
