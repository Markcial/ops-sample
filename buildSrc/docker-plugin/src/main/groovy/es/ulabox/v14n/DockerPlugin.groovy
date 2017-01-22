package es.ulabox.v14n

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction



class DockerPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.ext.Docker = Docker
    }
}

class Docker extends DefaultTask {
    String image = 'hello-world'
    List<String> command
    def command(String ...cmd) {
        command = cmd
    }
    List<String> volumes = ["$project.projectDir:/application"]
    def volumes(String ...vol) {
        volumes = vol
    }
    String workDir = "/application"
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
        if (volumes) {
            def vols = volumes.collect { v -> ['-v', v] }.flatten()
            cmd.addAll(vols as Collection<? extends String>)
        }
        if (workDir) {
            cmd.addAll(['-w', workDir])
        }
        cmd.addAll([image])
        if (command) {
            cmd.addAll(command)
        }

        return cmd
    }

    @TaskAction
    def run() {
        _cmd()
                .execute()
                .waitForProcessOutput(System.out, System.err)
    }
}