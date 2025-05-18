# mvnex-examples
《Maven by Example》书中示例代码

<https://books.sonatype.com/mvnex-book/reference/index.html>

## A Simple Maven Project
使用Archetype插件创建的简单Maven项目。

代码：[simple](simple)

构建

```shell
cd simple
mvn package
```

运行

```shell
$ java -cp target/simple-1.0-SNAPSHOT.jar org.sonatype.mavenbook.App
Hello World!
```

## Simple Weather Project
简单天气预报程序，输入邮政编码(zip code)，从[Yahoo天气RSS订阅](https://web.archive.org/web/20160728012058/https://developer.yahoo.com/weather/archive.html)检索数据，并将解析结果打印到标准输出。

代码：[simple-weather](simple-weather)

构建

```shell
cd simple-weather
mvn package
```

运行（注：Yahoo天气RSS订阅服务目前已不可用）

```shell
$ java -jar target/simple-weather-1.0-jar-with-dependencies.jar
0    INFO  YahooRetriever  - Retrieving Weather Data
134  INFO  YahooParser  - Creating XML Reader
333  INFO  YahooParser  - Parsing XML Response
420  INFO  WeatherFormatter  - Formatting Weather Data
*********************************
 Current Weather Conditions for:
  Evanston, IL, US

 Temperature: 45
   Condition: Cloudy
    Humidity: 76
  Wind Chill: 38
*********************************

$ java -jar target/simple-weather-1.0-jar-with-dependencies.jar 70112
0    INFO  YahooRetriever  - Retrieving Weather Data
134  INFO  YahooParser  - Creating XML Reader
333  INFO  YahooParser  - Parsing XML Response
420  INFO  WeatherFormatter  - Formatting Weather Data
*********************************
 Current Weather Conditions for:
  New Orleans, LA, US

 Temperature: 82
   Condition: Fair
    Humidity: 71
  Wind Chill: 82
*********************************
```

## Simple Web Application
简单Web应用。

代码：[simple-webapp](simple-webapp)

构建

```shell
cd simple-webapp
mvn package
```

运行

```shell
mvn jetty:run
```

```shell
$ curl -s http://localhost:8080/simple
SimpleServlet Executed
```

## Multi-Module Project
多模块项目，组合了simple-weather和simple-webapp两个示例。

代码：[multi-module](multi-module)
* [天气查询服务模块](multi-module/simple-weather-module)
* [Web应用模块](multi-module/simple-webapp-module)

构建（在项目根目录下执行）

```shell
mvn install
```

运行

```shell
cd multi-module/simple-webapp-module
mvn jetty:run
```

```shell
$ curl -s http://localhost:8080/weather?zip=10002
*********************************
 Current Weather Conditions for:
  New York, NY, US

 Temperature: 39
   Condition: Fair
    Humidity: 67
  Wind Chill: 39
*********************************
```

## Multi-Module Enterprise Project
多模块企业项目，使用Spring框架和Hibernate创建一个简单Web应用和一个命令行工具，用于从Yahoo天气RSS订阅服务读取数据，并将结果保存到数据库。

代码：[multi-module-spring](multi-module-spring)
* [对象模型模块](multi-module-spring/simple-model-module)
* [数据持久化模块](multi-module-spring/simple-persist-module)
* [天气查询服务模块](multi-module-spring/simple-weather-module-spring)
* [Web应用模块](multi-module-spring/simple-webapp-module-spring)
* [命令行工具模块](multi-module-spring/simple-command-module-spring)

### Web应用
构建（在项目根目录下执行）

```shell
mvn install
```

运行

```shell
cd multi-module-spring/simple-webapp-module-spring
mvn hibernate3:hbm2ddl
mvn jetty:run
```

注意：如果使用JDK 17+运行，需要设置以下环境变量，否则会报错 "module java.base does not "opens java.lang" to unnamed module" 。

```shell
export MAVEN_OPTS="--add-opens java.base/java.lang=ALL-UNNAMED"
```

```shell
$ curl -s http://localhost:8080/weather.x?zip=10002
<b>Current Weather Conditions for:
    New York, NY, US
</b>
<br/>

<ul>
    <li>Temperature: 39</li>
    <li>Condition: Fair</li>
    <li>Humidity: 67</li>
    <li>Wind Chill: 39</li>
    <li>Date: Sun May 18 14:08:46 CST 2025</li>
</ul>

$ curl -s http://localhost:8080/history.x?zip=10002
```

### 命令行工具
构建（在项目根目录下执行）

```shell
mvn install
```

运行

```shell
$ cd multi-module-spring/simple-command-module-spring
$ mvn hibernate3:hbm2ddl
$ java -jar target/simple-command-module-spring-jar-with-dependencies.jar 60202 weather
****************************************
Current Weather Conditions for:
  Evanston,
  IL,
  US
****************************************

 * Temperature: 75
 * Condition: Partly Cloudy
 * Humidity: 64
 * Wind Chill: 75
 * Date: Wed Aug 06 09:35:30 CDT 2008

$ java -jar target/simple-command-module-spring-jar-with-dependencies.jar 60202 history
Weather History for:
  Evanston,
  IL,
  US

****************************************
 * Temperature: 39
 * Condition: Heavy Rain
 * Humidity: 93
 * Wind Chill: 36
 * Date: 2007-12-02 13:45:27.187
****************************************
 * Temperature: 75
 * Condition: Partly Cloudy
 * Humidity: 64
 * Wind Chill: 75
 * Date: 2008-08-06 09:24:11.725
****************************************
 * Temperature: 75
 * Condition: Partly Cloudy
 * Humidity: 64
 * Wind Chill: 75
 * Date: 2008-08-06 09:27:28.475
```

注意：如果使用JDK 17+，需要添加JVM选项`--add-opens java.base/java.lang=ALL-UNNAMED`。
