package es.ulabox.deployment

import org.gradle.api.*
import org.gradle.api.tasks.Delete
import org.gradle.api.tasks.Exec

class DeployPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.task("composer", type: Exec) {
            commandLine = 'composer'
            args = ['install']
        }

        project.task("npm-install", type: Exec) {
            commandLine = 'npm'
            args = ['install']
        }

        project.task('npm-clean', type: Delete) {
            delete 'node_modules'
        }

        project.task('brunch', type: Exec) {
            commandLine = 'brunch'
            args = ['b', '--production']
        }
    }
}
