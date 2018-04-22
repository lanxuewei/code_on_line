package com.lanxuewei.code_on_line.judger.test;

import com.lanxuewei.code_on_line.judger.CppSolution;
import com.lanxuewei.code_on_line.judger.JudgeStatus;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class CppSolutionTest extends TestCase {

    @Test
    public void testAccepted() {
        String source = "#include <stdio.h>\n"
                + "int main() {\n"
                + "  int a, b;\n"
                + "  scanf(\"%d%d\", &a, &b);\n"
                + "  printf(\"%d\", a + b);\n"
                + "  return 0;\n"
                + "}\n";
        CppSolution solution = new CppSolution(source, 1000, 1024, "1 2", "3");
        Assert.assertEquals(solution.judge(), JudgeStatus.ACCEPTED);
        solution.removeFiles();
    }

    @Test
    public void testWrongAnswer() {
        String source = "#include <stdio.h>\n"
                + "int main() {\n"
                + "  int a, b;\n"
                + "  scanf(\"%d%d\", &a, &b);\n"
                + "  printf(\"%d\", a + b);\n"
                + "  return 0;\n"
                + "}\n";
        CppSolution solution = new CppSolution(source, 1000, 1024, "1 2", "4");
        Assert.assertEquals(solution.judge(), JudgeStatus.WRONG_ANSWER);
    }


    @Test
    public void testCompileFailed() {
        String source = "#include <stdio.h>\n"
                + "int m() {\n"
                + "}\n";
        CppSolution solution = new CppSolution(source, 1000, 1024, "1 2", "3");
        Assert.assertEquals(solution.judge(), JudgeStatus.COMPILE_ERROR);
        System.out.println("compile failed: `" + solution.getCompileLog() + "`");
        Assert.assertTrue(solution.getCompileLog().length() > 0);
    }

    @Test
    public void testTimeLimitExceed() {
        String source = "#include <stdio.h>\n"
                + "int main() {\n"
                + " while(1);"
                + "}\n";
        CppSolution solution = new CppSolution(source, 5, 1024, "1 2", "3");
        Assert.assertEquals(solution.judge(), JudgeStatus.TIME_LIMIT_EXCEEDED);
    }

    @Test
    public void testOutputLimitExceeded() {
        String source = "#include <stdio.h>\n"
                + "int main() {\n"
                + "  for (int i = 0; i < 1024*1024+1; i++) {"
                + "    printf(\".\");\n"
                + "  }\n"
                + "  return 0;\n"
                + "}\n";
        CppSolution solution = new CppSolution(source, 3, 1024, "1 2", "3");
        Assert.assertEquals(solution.judge(), JudgeStatus.OUTPUT_LIMIT_EXCEEDED);
    }

}
