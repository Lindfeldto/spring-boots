# Spring Boots

* Try out Spring Boot in VSCode
* Minimal examples for starter code


## Setup VSCode Extensions

* when open some java file, VSCode will suggest Java extensions
  - Java Extension Pack by Microsoft is good to install
  - I did not install Spring Boot Extension Pack by Pivotal
    - (some extension in the pack conflicted to Maven Projects extension)
* the below is the list of extensions I use for Java and SQL
* install command: `code --install-extension <extension-id>`

```bash
$ code --list-extensions 
GabrielBB.vscode-lombok
juhahinkula.thymeleaf
mtxr.sqltools
Pivotal.vscode-spring-boot
redhat.java
redhat.vscode-xml
VisualStudioExptTeam.vscodeintellicode
vscjava.vscode-java-debug
vscjava.vscode-java-dependency
vscjava.vscode-java-pack
vscjava.vscode-java-test
vscjava.vscode-maven
vscjava.vscode-spring-boot-dashboard
vscjava.vscode-spring-initializr
```

* to quick create spring boot project
  - open Command Palette by Ctrl(or ⌘)+Shift+P
  - type `spring initializr`
* to run the spring boot project
  - open Spring-Boot Dashboard and right click and `Run` or...
  - open Maven Projects and find and click `spring-boot:run`


## CLI demos

* demo-cli (before)
  - @Configuration AppConfig class register @Bean
  - getBean from ApplicationContext
* demo-cli2 (middle)
  - @Configuration AppConfig class register @Bean
  - @Autowired bean (NO getBean)
* demo-cli3 (after)
  - @ComponentScan auto register @Component bean (NO @Import @Configuration)
  - @Autowired bean (NO getBean)

## DB demos

* demo-crm
  - using standard layer structure
* demo-jdbc
  - using NamedParameterJdbcTemplate
* demo-jpa
  - using JpaRepository


## Web demos

* demo-web-rest
  - rest api
* demo-web-thymeleaf
  - thymeleaf web pages


## VSCode Trouble Shooting

* The import of xxx cannot be resolved
  - Command + Shift + p -> "java clean" -> click to run
* Clean cache
  - rm all files in `$HOME/Library/Application\ Support/Code/User/workspaceStorage/`
* "Class path is incomplete" warning
  - okay to just close the window
* org.h2.jdbc.JdbcSQLException: Column not found
  - https://stackoverflow.com/questions/35646432


## Reference

* Spring Boot in Visual Studio Code
  - https://code.visualstudio.com/docs/java/java-spring-boot
* Visual Studio Code - Java - Import Errors and More
  - https://stackoverflow.com/questions/45743779
* 書籍「はじめてのSpring Boot」のサポートページ
  - https://github.com/making/hajiboot-samples
* @RequestMapping handler Method Arguments
  - https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-methods
