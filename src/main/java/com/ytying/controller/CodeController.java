package com.ytying.controller;

import com.ytying.compiler.CompilerClassLoader;
import com.ytying.entity.UserCode;
import com.ytying.packaged.UserCodeDo;
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
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by kefan.wkf on 17/1/23.
 */
@Controller
@RequestMapping(value = "/code")
public class CodeController extends BaseController {

    @Autowired
    private UserCodeService userCodeService;

    // 项目Class文件所在绝对路径
    private String dir = this.getClass().getResource("/").getPath();


    @RequestMapping(value = "/sourceCompiler")
    @ResponseBody
    public String compilerSource(@RequestParam String source,
                                 @RequestParam int uid) throws IOException {

        CompilerClassLoader compilerClassLoader = new CompilerClassLoader(dir);

        String className = StringUtils.getClassNameFromSourceCode(source);

        //拼接代码,生成JavaFileObject
        StringWriter writer = new StringWriter();
        PrintWriter out = new PrintWriter(writer);
        out.print(source);
        out.close();
        JavaFileObject file = new JavaSourceFromString(className, writer.toString());

        //获取JavaCompiler
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        String sOutputPath = dir;

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

        StandardJavaFileManager oStandardJavaFileManager = compiler.getStandardFileManager(diagnostics, null, null);

        // 设置class文件生成到的路径
        JavaFileManager.Location oLocation = StandardLocation.CLASS_OUTPUT;
        oStandardJavaFileManager.setLocation(oLocation, Arrays.asList(new File[]{new File(sOutputPath)}));

        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);
        JavaCompiler.CompilationTask task = compiler.getTask(null, oStandardJavaFileManager, diagnostics, null, null, compilationUnits);

        boolean success = task.call();
        System.out.println("Success: " + success);

        // 保存用户代码到数据库
        if (uid != 1) {
            UserCode userCode = new UserCode();
            userCode.setUid(uid);
            userCode.setSource_code(source);
            userCode.setTime(new Date().getTime());
            if (success) {
                userCode.setStatus("success");
            } else {
                userCode.setStatus("danger");
            }
            userCodeService.addUserCode(userCode);
        }

        if (success) {
            try {

                StringBuilder compileResult = new StringBuilder("");
                System.setOut(new CompilerPrintStream(System.out, compileResult));

                // 启动一个线程来运行用户代码
                Callable<String> callable = new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        Class c = Class.forName(className, true, compilerClassLoader);
                        Method main = c.getDeclaredMethod("main", new Class[]{String[].class});
                        main.setAccessible(true);
                        main.invoke(null, new Object[]{null});
                        // TODO kefan.wkf Is this return useful?
                        return jsonResultNew(ReturnCode.RETURN_SUCCESS, compileResult.toString());
                    }
                };
                FutureTask<String> future = new FutureTask<String>(callable);
                new Thread(future).start();
                future.get(5000, TimeUnit.MILLISECONDS);    // 设置超时时间,以防用户提交死循环代码

                return jsonResultNew(ReturnCode.RETURN_SUCCESS, compileResult.toString());
            } catch (NullPointerException e) {
                return jsonResultNew(ReturnCode.RETURN_CodeMake_UNKNOW_ERROR, e);
            } catch (InterruptedException e) {
                return jsonResultNew(ReturnCode.RETURN_CodeMake_UNKNOW_ERROR, e);
            } catch (ExecutionException e) {
                return jsonResultNew(ReturnCode.RETURN_CodeMake_UNKNOW_ERROR, e);
            } catch (TimeoutException e) {
                return jsonResultNew(ReturnCode.RETURN_CodeMake_TIMEOUT_ERROR, e);
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

    @RequestMapping(value = "/userCodeListGet")
    @ResponseBody
    public String getUserCodeListByUid(@RequestParam int uid) {
        List<UserCode> list = userCodeService.getUserCodeList(uid);
        if (null == list) {
            return jsonResultNew(ReturnCode.RETURN_ERROR, null);
        }
        List<UserCodeDo> returnList = new ArrayList<>();
        for (UserCode userCode : list) {
            UserCodeDo userCodeDo = new UserCodeDo();
            userCodeDo.setUid(userCode.getUid());
            userCodeDo.setCode(userCode.getSource_code());
            userCodeDo.setId(userCode.getId());
            userCodeDo.setStatus(userCode.getStatus());
            long time = userCode.getTime();
            String format = "yyyy-MM-dd";
            String date = StringUtils.transferLongToDate(format, time);
            userCodeDo.setDate(date);
            returnList.add(userCodeDo);
        }
        return jsonResultNew(ReturnCode.RETURN_SUCCESS, returnList);
    }

    @RequestMapping(value = "/userCodeRemove")
    @ResponseBody
    public String removeUserCode(@RequestParam int id, int uid) {
        boolean success = userCodeService.removeUserCode(id, uid);
        if (success) {
            return jsonResultNew(ReturnCode.RETURN_SUCCESS, null);
        } else {
            return jsonResultNew(ReturnCode.RETURN_ERROR, null);
        }
    }
}