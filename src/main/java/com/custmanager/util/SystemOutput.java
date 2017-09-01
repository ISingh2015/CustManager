/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.util;

import java.awt.TextArea;
import java.io.PrintStream;
import java.util.Vector;
import javax.swing.JTextArea;

/**
 *
 * @author Inderjit
 */
public class SystemOutput extends PrintStream implements Runnable{

    private static PrintStream oldStdOut;
    private static PrintStream oldStdErr;
    private static SystemOutput stdErrCapture;
    private static SystemOutput stdOutCapture;
    private static boolean active;
    private static TextArea txtaOut;
    private static JTextArea jtxtaOut;
    private static boolean writeStdOut = false;
    private static Vector vecBuffered;

    private Thread thrdMain;

    /**
     *
     * private constructor for this object only
     *
     */
    private SystemOutput(PrintStream ps) {
        super(ps);
        thrdMain = new Thread(this);
        thrdMain.start();
    }

    public static void setTextArea(TextArea t) {
        txtaOut = t;
    }

    public static void setJTextArea(JTextArea jt) {
        jtxtaOut = jt;
    }

    public static synchronized void start(boolean ibolWriteStdoutFlag) {
        try {
            if ((!active) && (stdOutCapture == null) && (stdErrCapture == null)) {
                // Save old settings.
                oldStdOut = System.out;
                oldStdErr = System.err;
                stdOutCapture = new SystemOutput(System.out);
                stdErrCapture = new SystemOutput(System.err);            // Start redirecting the output.
                System.setOut(stdOutCapture);
                System.setErr(stdErrCapture);
                active = true;
                writeStdOut = active;
            }
        } catch (Exception e) {
            e.printStackTrace(oldStdErr);
            stop();
        }
    }

    /**
     *
     * stop capturing the system out and restore the system out to its original
     * information
     *
     */
    public static synchronized void stop() {
        if (oldStdOut != null) {
            System.setOut(oldStdOut);
            oldStdOut = null;
            if (stdOutCapture != null) {
                stdOutCapture.close();
                stdOutCapture = null;
            }
        }
        if (oldStdErr != null) {
            System.setErr(oldStdErr);
            oldStdErr = null;
            if (stdErrCapture != null) {
                stdErrCapture.close();
                stdErrCapture = null;
            }
        }

        active = false;
    }

    /**
     *
     * return the instance of TextAreaSystemOutputStream
     *
     */
    public static SystemOutput getStdOutCapture() {
        return stdOutCapture;
    }

    /**
     *
     * return the instance of TextAreaSystemOutputStream for standard output
     * stream
     *
     */
    public static SystemOutput getStdErrCapture() {
        return stdErrCapture;
    }

    /**
     *
     * check to see if this is active or not
     *
     */
    public static boolean isActive() {
        return active;
    }

    /**
     *
     * set active and for writing out flag
     *
     */
    public static void setActive(boolean active, boolean writeStdoutFlag) {
        if (active) {
            start(writeStdoutFlag);
        } else {
            stop();
        }
    }   // PrintStream override.

    /**
     *
     * write out to the text area
     *
     */
    public void write(int b) {
        try {
            // here goes whatever custom code you need for capturing         // Let's write to the standard stream, too
            if (writeStdOut) {
                super.write(b);
            }
        } catch (Exception e) {
            // Oops something's wrong
            super.write(b);
            e.printStackTrace(oldStdErr);
            setError();
        }
        if (txtaOut != null) {
            txtaOut.append((new Integer(b)).toString());
        } else {
            jtxtaOut.append((new Integer(b)).toString());
        }
    }   // PrintStream override.

    /**
     *
     * write out to the text area
     *
     */
    public synchronized void write(byte buf[], int off, int len) {
        try {
            // here goes whatever custom code you need for capturing         // Let's write to the standard stream, too
            if (writeStdOut) {
                super.write(buf, off, len);
            }
        } catch (Exception e) {
            // Oops something's wrong
            super.write(buf, off, len);
            e.printStackTrace(oldStdErr);
            setError();
        }

        this.getBuffer().add(new String(buf, off, len));
    }

    public static Vector getBuffer() {
        if (vecBuffered == null) {
            vecBuffered = new Vector();
        }

        return vecBuffered;
    }

    public void run() {
        try {
//            println("Message Display Thread Started..");
            while (this.isActive()) {
                Vector vecBuffer = this.getBuffer();
                String strTemp = "";

                while (vecBuffer.size() > 0) {
                    strTemp = (String) vecBuffer.remove(0);

                    if (txtaOut != null) {
                        txtaOut.append(strTemp);
                    } else {
                        jtxtaOut.append(strTemp);
                    }
                }

                if (txtaOut != null) {
                    txtaOut.setCaretPosition(txtaOut.getText().length());
                } else {
                    jtxtaOut.setCaretPosition(jtxtaOut.getText().length());
                }

                try {
                    this.thrdMain.sleep(1000);
                } catch (Exception e) {
                    println(e.getMessage());
                }

            }
        } catch (Exception e) {
            println(e.getMessage());
        }
    }
}
