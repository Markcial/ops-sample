package es.ulabox.deployment

import org.gradle.api.*

class DeployPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.task("hello") {
            println "hola"
        }
    }
}
