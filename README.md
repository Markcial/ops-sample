# Ops sample
## Description
Example on how to run multi project builds with gradle.
This example builds a frontend and a backend application and combines them in a zip ready for release to the server.

## What would you need

 * Docker: [Installation](https://docs.docker.com/engine/installation/)
 * Gradle: [Installation](https://gradle.org/gradle-download/)
 
## How do i run this

Go to a terminal on the root folder and write `gradle release`

This starts the release task, this tasks builds the *frontend* and *backend* applications and merge them inside 
a zip package named `${project-name}-release-${timestamp}.zip` inside the *build/releases* folder.

```gradle
task release(type: Zip) {
    if (!buildDir.exists()) {
        buildDir.mkdirs()
    }
    if (!releasesDir.exists()) {
        releasesDir.mkdirs()
    }

    baseName releaseName
    destinationDir releasesDir
    from('frontend/public') {
        into('static')
    }
    from('backend') {
        into('.')
        exclude '*.gradle', '*.json', '*.lock'
    }
}
release.dependsOn 'backend:build'
release.dependsOn 'frontend:build'
```

This task checks for the folders, creates them if missing. Then adds files from the *backend* to the root of the zip 
and the files from the *frontend/public* to the *static* folder of the zip. This zip is placed on the *build/releases* folder.
The task depends on the build from both *frontend* and *backend* builds.

If you want to try the deployment run `gradle deploy` on a terminal. After the task
has completed open a browser on [http://127.0.0.1:8080](http://127.0.0.1:8080).

## The subprojects

Every sub project has its own tasks and can be run on its own folder.
For example, if you want to install the node modules you can simply switch to the frontend folder and run the task:

```shell
$ cd frontend
frontend$ gradle npmInstall
```

Or if you want to run composer on backend, switch to the backend folder and run the gradle task.

```shell
$ cd backend
backend$ gradle composer
```

You can run both task from the root project too

```shell
$ gradle backend:composer
$ gradle frontend:npmInstall
```