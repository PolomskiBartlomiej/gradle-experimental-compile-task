# gradle-experimental-compile-task
The main goal of this project is simple implementation of parametrized compilation task 

# experimental compile task assumptions
* Should be able to manage target and source parameters from java compile
  
  Compile parameters are defines in `gralde.properties`
  ```
  experimentalSourceCompatibility = 1.8
  experimentalTargetCompatibility = 1.8
  ```
  and they are used in experimental task:
  
  ```
  task compileExperimentalJava(type: JavaCompile) {
    ...
    targetCompatibility = experimentalTargetCompatibility
    sourceCompatibility = experimentalSourceCompatibility
    ...
    }
  ```
* Should compile java files to custom directory of build 
  
  definies classpath and source parameters of compile
  ```
  task compileExperimentalJava(type: JavaCompile) {
    ...
    classpath = sourceSets.main.runtimeClasspath + sourceSets.test.runtimeClasspath
    source = files(file("${rootDir}/src"))
   ...
   }
   ```
   
  set the destination dir where compiled files goes, decided to be under `${buildDir}/experimental`
   
  ```
  task compileExperimentalJava(type: JavaCompile) {
    ...
     destinationDir = file("${buildDir}/experimental")
  }
  ```

* Should be exclude from incremantal build in gradle
  ```
  task compileExperimentalJava(type: JavaCompile) {
   outputs.upToDateWhen {false}
   }
  ```


# assumptions of test experimental compile task

* Should be depedns on experimental compile
 ```
 task testExperimentCompile(type: Test, dependsOn: compileExperimentalJava) {
 ...
 }
 ```

* Should run test from experimental compile
 ```
task testExperimentCompile(type: Test, dependsOn: compileExperimentalJava) {
 ...
 classpath += files(file(compileExperimentalJava.destinationDir))
 }
 ```
 
  
