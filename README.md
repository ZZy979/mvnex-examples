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
