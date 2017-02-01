package com.ytying.controller;

import com.ytying.compiler.CompilerClassLoader;
import com.ytying.constant.Dir;
import com.ytying.entity.UserCode;
import com.ytying.service.UserCodeService;
import com.ytying.sysenum.ReturnCode;
import com.ytying.compiler.CompilerPrintStream;
import com.ytying.compiler.JavaSourceFromString;
import com.ytying.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.tools.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by kefan.wkf on 17/1/23.
 */
@Controller
@RequestMapping(value = "/code")
public class CodeController extends BaseController {

    @Autowired
    private UserCodeService userCodeService;

    @RequestMapping(value = "/sourceCompiler")
    @ResponseBody
    public String compilerSource(@RequestParam String source,
                                 @RequestParam int uid) throws IOException {

        CompilerClassLoader compilerClassLoader = new CompilerClassLoader(Dir.classPathDir);

        String className = StringUtils.getClassNameFromSourceCode(source);

        //拼接代码,生成JavaFileObject
        StringWriter writer = new StringWriter();
        PrintWriter out = new PrintWriter(writer);
        out.println("package com.ytying.source;");
        out.print(source);
        out.close();
        JavaFileObject file = new JavaSourceFromString(className, writer.toString());

        //获取JavaCompiler
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        //TODO some problems
        String sOutputPath = Dir.classPathDir;

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

        StandardJavaFileManager oStandardJavaFileManager = compiler.getStandardFileManager(diagnostics, null, null);

        // 设置class文件生成到的路径
        JavaFileManager.Location oLocation = StandardLocation.CLASS_OUTPUT;
        oStandardJavaFileManager.setLocation(oLocation, Arrays.asList(new File[]{new File(sOutputPath)}));

        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);
        JavaCompiler.CompilationTask task = compiler.getTask(null, oStandardJavaFileManager, diagnostics, null, null, compilationUnits);

        boolean success = task.call();
        System.out.println("Success: " + success);

        if (success) {
            //保存用户代码到数据库
            UserCode userCode = new UserCode();
            userCode.setUid(uid);
            userCode.setSource_code(source);
            userCode.setTime(new Date().getTime());
            userCodeService.addUserCode(userCode);
            try {
                StringBuilder compileResult = new StringBuilder("");
                System.setOut(new CompilerPrintStream(System.out, compileResult));
                Class c = Class.forName("com.ytying.source." + className, true, compilerClassLoader);
                Method main = c.getDeclaredMethod("main", new Class[]{String[].class});
                main.setAccessible(true);
                main.invoke(null, new Object[]{null});
                return jsonResultNew(ReturnCode.RETURN_SUCCESS, compileResult.toString());
            } catch (ClassNotFoundException e) {
                return jsonResultNew(ReturnCode.RETURN_CodeMake_UNKNOW_ERROR, e);
            } catch (NoSuchMethodException e) {
                return jsonResultNew(ReturnCode.RETURN_CodeMake_UNKNOW_ERROR, e);
            } catch (IllegalAccessException e) {
                return jsonResultNew(ReturnCode.RETURN_CodeMake_UNKNOW_ERROR, e);
            } catch (InvocationTargetException e) {
                return jsonResultNew(ReturnCode.RETURN_CodeMake_UNKNOW_ERROR, e);
            } catch (NullPointerException e) {
                return jsonResultNew(ReturnCode.RETURN_CodeMake_UNKNOW_ERROR, e);
            }
        } else {
            StringBuilder errs = new StringBuilder("");
            for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
                errs.append(diagnostic.toString() + "\n");
            }
            System.out.println(errs.toString());
            return jsonResultNew(ReturnCode.RETURN_CodeMake_COMPILE_ERROR, errs.toString());
        }
    }

}