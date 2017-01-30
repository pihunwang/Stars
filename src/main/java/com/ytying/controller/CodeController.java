package com.ytying.controller;

import com.sun.org.apache.bcel.internal.classfile.Code;
import com.ytying.classloader.MyClassLoader;
import com.ytying.sysenum.ReturnCode;
import com.ytying.template.CommonTemplate;
import com.ytying.util.CompilerPrintStream;
import com.ytying.util.JavaSourceFromString;
import com.ytying.util.StringUtils;
import javaxtools.compiler.CharSequenceCompilerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.tools.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Random;

import static com.ytying.sysenum.ReturnCode.RETURN_CodeMake_COMPILE_ERROR;
import static com.ytying.sysenum.ReturnCode.RETURN_SUCCESS;

/**
 * Created by kefan.wkf on 17/1/23.
 */
@Controller
@RequestMapping(value = "/code")
public class CodeController extends BaseController {

    @RequestMapping(value = "/sourceCompiler")
    @ResponseBody
    public String compilerSource(@RequestParam String source) throws Exception {

        MyClassLoader myClassLoader = new MyClassLoader("/Users/UKfire/Desktop/WkfCode/Stars/target/classes");

        String className = "jiba" + new Random().nextInt(1000);

        StringWriter writer = new StringWriter();
        PrintWriter out = new PrintWriter(writer);
        out.println("package com.ytying.source;");
        out.println("public class " + className + "{");
        out.println(source);
        out.println("}");
        out.close();
        System.out.println(writer.toString());
        JavaFileObject file = new JavaSourceFromString(className, writer.toString());

        //获取JavaCompiler
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        //TODO some problems
        String sOutputPath = "/Users/UKfire/Desktop/WkfCode/Stars/target/classes/";

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

        StandardJavaFileManager oStandardJavaFileManager = compiler.getStandardFileManager(diagnostics, null, null);

        // 设置class文件生成到的路径
        JavaFileManager.Location oLocation = StandardLocation.CLASS_OUTPUT;
        oStandardJavaFileManager.setLocation(oLocation, Arrays.asList(new File[]{new File(sOutputPath)}));

        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);
        JavaCompiler.CompilationTask task = compiler.getTask(null, oStandardJavaFileManager, diagnostics, null, null, compilationUnits);


        boolean success = task.call();
        for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
            System.out.println(diagnostic.getCode());
            System.out.println(diagnostic.getKind());
            System.out.println(diagnostic.getPosition());
            System.out.println(diagnostic.getStartPosition());
            System.out.println(diagnostic.getEndPosition());
            System.out.println(diagnostic.getSource());
            System.out.println(diagnostic.getMessage(null));
        }
        System.out.println("Success: " + success);

        if (success) {
            try {
                StringBuilder compileResult = new StringBuilder("");
                System.setOut(new CompilerPrintStream(System.out, compileResult));
                Class.forName("com.ytying.source." + className, true, myClassLoader).getDeclaredMethod("main", new Class[]{String[].class})
                        .invoke(null, new Object[]{null});
                return jsonResultNew(ReturnCode.RETURN_SUCCESS, compileResult.toString());
            } catch (ClassNotFoundException e) {
                System.err.println("Class not found: " + e);
                return "Class not found: " + e;
            } catch (NoSuchMethodException e) {
                System.err.println("No such method: " + e);
            } catch (IllegalAccessException e) {
                System.err.println("Illegal access: " + e);
            } catch (InvocationTargetException e) {
                System.err.println("Invocation target: " + e);
            } catch (NullPointerException e) {
                System.err.println("Null Pointer: " + e);
            }
        }
        return jsonResultNew(ReturnCode.RETURN_ERROR, null);
    }

}