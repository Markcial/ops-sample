package es.ulabox.v14n

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction

class DockerPlugin implements Plugin<Project> {
    void apply(Project project) {
        Docker.volumes = ["$project.projectDir:/application"]
        Docker.workDir = "/application"
    }
}

class Docker extends DefaultTask {
    String image = 'hello-world'
    List<String> command
    def command(String ...cmd) {
        command = cmd
    }
    static List<String> volumes
    def volumes(String ...vol) {
        volumes = vol
    }
    static String workDir
    Boolean daemon = false
    Boolean cleanup = true

    def _cmd() {
        def cmd = ['docker', 'run']
        if (daemon) {
            cmd.add('-d')
        } else {
            cmd.add('-i')
        }
        if (cleanup) {
            cmd.add('--rm')
        }
        if (!volumes.empty) {
            def vols = volumes.collect { v -> ['-v', v] }.flatten()
            cmd.addAll(vols as Collection<? extends String>)
        }
        if (!workDir.empty) {
            cmd.addAll(['-w', workDir])
        }
        cmd.addAll([image])
        if (!command.empty) {
            cmd.addAll(command)
        }

        return cmd
    }

    @TaskAction
    def run() {
        println _cmd()
        _cmd()
                .execute()
                .waitForProcessOutput(System.out, System.err)
    }
}