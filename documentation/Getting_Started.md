# Nifty GUI

[Getting_Started](../documentation/Getting_Started.md) &nbsp; [Javadoc](../documentation/Getting_Started.md) &nbsp; [Examples](../documentation/Examples.md)

__To build the examples you need maven and java11(jdk) installed.__

Then just enter commandline in project folder.
- mvn clean compile install

Or to just compile a specific module:
<br>Example Lwjgl-examples:
- mvn install -pl nifty-examples-lwjgl -am

To start any example you can execute the specific runnable jar.
<br>Example LibGdx:
- java -jar nifty-examples-libgdx/desktop/target/nifty-examples-libgdx-desktop-1.5.0-SNAPSHOT-jar-with-dependencies.jar

# Nifty is available in the Maven central:

```XML
<groupId>com.github.nifty-gui</groupId>
<artifactId>nifty</artifactId>
<version>1.4.3</version>
```

<br>[Main](../README.md)
