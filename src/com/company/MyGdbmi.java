/**
 * This code has been tested on MacOSX Mojave.
 */
package com.company;

import java.io.*;

public class MyGdbmi {

    /**
     * Add breakpoint to main function
     *
     * @param process
     */
    public static void runCommandInGdb(Process process, String command, boolean shouldClosed) {
        // execute gdb command
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(process.getOutputStream())), true);
        out.println(command);
        out.flush();
        if (shouldClosed) {
            out.close();
        }
    }


    /**
     * read the result of command from the terminal
     */
    public static void displayResultinTerminal(Process gdbProcess) throws IOException {
        System.out.println("Getting the result from the terminal");
        BufferedReader in = new BufferedReader(new InputStreamReader(gdbProcess.getInputStream()));
        String line = "";
        while ((line = in.readLine()) != null) {
            System.out.println("\t" + line);
        }
    }

    public static void main(String[] args) {
        try {
            // Start gdb with mi interpreter
            String executableFile = new File("data/SimpleProject/swapflaw.out").getCanonicalPath();
            String gdbcommand = String.format("%s %s %s", PATH_GDB, executableFile, RUN_WITH_GDB_MI);
            System.out.println("Command: " + gdbcommand);
            Process gdbProcess = Runtime.getRuntime().exec(gdbcommand);

            if (gdbProcess != null) {
                runCommandInGdb(gdbProcess, "-break-insert main", false);
                runCommandInGdb(gdbProcess, "-break-list", false);
                runCommandInGdb(gdbProcess, "-gdb-exit", true);

                // A remaining bug: can not display information on the debugger during the execution!
                // In the current version, I have to put the displaying statement to the end of debugging exeuctions.
                displayResultinTerminal(gdbProcess);

                gdbProcess.destroy();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final String PATH_GDB = "/usr/local/bin/gdb";
    public static final String RUN_WITH_GDB_MI = "--interpreter=mi";
}