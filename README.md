# Spring Boots

Try out Spring Boot in VSCode


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
