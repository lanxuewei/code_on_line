package com.lanxuewei.code_on_line.judger;

import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class CppSolution {

    public static final long OUTPUT_MAX_SIZE = 1024 * 1024;

    private String mSourceCode;

    private int mTimeLimit;

    private long mMemoryLimit;

    private Map<String, String> mTestCases;

    public CppSolution(String sourceCode, int timeLimit, long memoryLimit, Map<String, String> testCases) {
        mSourceCode = sourceCode;
        mTimeLimit = timeLimit;
        mMemoryLimit = memoryLimit;
        mTestCases = testCases;
    }

    private static Map<String, String> makeMap(String k, String v) {
        Map<String, String> map = new TreeMap<String, String>();
        map.put(k, v);
        return map;
    }

    public CppSolution(String sourceCode, int timeLimit, long memoryLimit, String input, String output) {
        this(sourceCode, timeLimit, memoryLimit, makeMap(input, output));
    }

    private static File sWorkDir = new File(getTempDir());

    private static String getTempDir() {
        String tmp = System.getProperty("java.io.tmpdir");
        if (tmp == null || tmp.length() == 0) {
            System.getenv("TMP");
        }
        if (tmp == null || tmp.length() == 0) {
            tmp = System.getenv("TEMP");
        }
        System.out.println("TMP: " + tmp);
        return tmp;
    }

    public static void setWorkDir(String workDir) {
        sWorkDir = new File(workDir);
        if (!sWorkDir.exists()) {
            sWorkDir.mkdirs();
        }
    }

    private static AtomicLong mCodeCounter = new AtomicLong(0);

    private File mCodeFile;

    private File mBinaryFile;

    private File mCompileLog;

    private Map<File, File> mCaseFiles = new TreeMap<File, File>();

    // create source code file and testcase input output files in work dir
    private void prepareFiles() {
        long codeNo = mCodeCounter.incrementAndGet();
        mCodeFile = new File(sWorkDir, codeNo + ".cpp");
        Utils.createTextFile(mCodeFile, mSourceCode);

        int caseNo = 1;
        for (Map.Entry<String, String> entry : mTestCases.entrySet()) {
            File in = new File(mCodeFile.getParent(), codeNo + "-" + caseNo + ".in");
            File out = new File(mCodeFile.getParent(),codeNo + "-" + caseNo + ".out");
            Utils.createTextFile(in, entry.getKey());
            Utils.createTextFile(out, entry.getValue());
            mCaseFiles.put(in, out);
            caseNo++;
        }
    }

    private boolean mCompiled = false;

    // compile source code file
    private boolean compile() {
        if (!mCodeFile.exists() || !new File(mCodeFile.getParent()).exists()) {
            return false;
        }

        Process process = null;
        try {
            mBinaryFile = new File(mCodeFile.getAbsolutePath() + ".bin");
            mCompileLog = new File(mCodeFile.getAbsolutePath() + ".log");
            process = new ProcessBuilder().directory(sWorkDir)
                    .command("g++", mCodeFile.getAbsolutePath(), "-o", mBinaryFile.getPath())
                    .redirectOutput(mCompileLog)
                    .redirectErrorStream(true)
                    .start();
            process.waitFor();
            return process.exitValue() == 0;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (process != null && process.isAlive()) {
                process.destroy();
            }
            mCompiled = true;
        }
        return false;
    }

    public String getCompileLog() {
        return mCompiled ? Utils.readTextFile(mCompileLog) : null;
    }

    private JudgeStatus run() {
        for (Map.Entry<File, File> entry: mCaseFiles.entrySet()) {
            File tin = entry.getKey();
            File tout = new File(tin.getAbsolutePath() + ".out");
            Process process = null;
            try {
                Signal.handle(new Signal("CHLD"), new SignalHandler() {
                    @Override
                    public void handle(Signal sig) {
                        System.out.println("sig: " + sig);
                    }
                });
                process = new ProcessBuilder().directory(sWorkDir)
                        .command(mBinaryFile.getAbsolutePath())
                        .redirectInput(tin)
                        .redirectOutput(tout)
                        .redirectErrorStream(true)
                        .start();
                process.waitFor(mTimeLimit, TimeUnit.SECONDS);
                if (tout.length() > OUTPUT_MAX_SIZE) {
                    return JudgeStatus.OUTPUT_LIMIT_EXCEEDED;
                }
                if (process.exitValue() != 0) {
                    return JudgeStatus.NON_ZERO_EXIT_CODE;
                }

                File eout = entry.getValue();  // expected output;
                if (Utils.compareTextFile(tout, eout) != 0) {
                    return JudgeStatus.WRONG_ANSWER;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                Signal.handle(new Signal("CHLD"), SignalHandler.SIG_DFL);
                if (tout.exists()) {
                    tout.delete();
                }
                if (process != null && process.isAlive()) {
                    process.destroy();
                    return JudgeStatus.TIME_LIMIT_EXCEEDED;
                }
            }
        }
        return JudgeStatus.ACCEPTED;
    }

    /**
     * clear temp file
     */
    public void removeFiles() {
        if (mCodeFile.exists() && mCodeFile.isFile()) {
            if (mCodeFile.delete()) {
                System.out.println("delete cpp file successfully!");
            }
        } else {
            System.out.println("delete cpp file failed!");
        }
        if (mBinaryFile.exists() && mBinaryFile.isFile()) {
            if (mBinaryFile.delete()) {
                System.out.println("delete binaryFile successfully!");
            }
        } else {
            System.out.println("delete binaryFile failed!");
        }
        if (mCompileLog.exists() && mCompileLog.isFile()) {
            if (mCompileLog.delete()) {
                System.out.println("delete compileLog successfully!");
            }
        } else {
            System.out.println("delete compileLog failed!");
        }
        for (Map.Entry<File, File> entry: mCaseFiles.entrySet()) {
            File tmpCaseIn = entry.getKey();
            File tmpCaseOut = entry.getValue();
            if (tmpCaseIn.exists() && tmpCaseIn.isFile()) {
                if (tmpCaseIn.delete()){
                    System.out.println("delete tmpCaseIn file successfully!");
                }
            } else {
                System.out.println("delete tmpCaseIn file failed!");
            }
            if (tmpCaseOut.exists() && tmpCaseOut.isFile()){
                if (tmpCaseOut.delete()){
                    System.out.println("delete tmpCaseOut file successfully!");
                }
            } else {
                System.out.println("delete tmpCaseOut file failed!");
            }
        }
    }

    public  JudgeStatus judge() {
        prepareFiles();
        if (!compile()) {
            return JudgeStatus.COMPILE_ERROR;
        }
        return run();
    }

}
