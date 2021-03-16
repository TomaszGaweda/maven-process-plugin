package com.bazaarvoice.maven.plugin.process;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.IOException;
import java.util.Stack;

@Mojo (name = "stop-all", defaultPhase = LifecyclePhase.POST_INTEGRATION_TEST, threadSafe = true)
public class ProcessStopMojo extends AbstractProcessMojo {

    @Override
    public void execute()
            throws MojoExecutionException, MojoFailureException {
        for(String arg : arguments) {
            getLog().info("arg: " + arg);
        }
        try {
            stopAllProcesses();
        } catch (Exception e) {
            getLog().error(e);
        }
    }

    private void stopAllProcesses() {
        if (waitForInterrupt) {
            getLog().info("Waiting for interrupt before stopping all processes ...");
            try {
                sleepUntilInterrupted();
            } catch (IOException e) {
                getLog().error(e);
            }
        }

        internalStopProcesses();
    }

}
