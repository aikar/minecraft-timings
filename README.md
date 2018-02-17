# Minecraft Timings Library
This library lets Bukkit Plugin developers SAFELY add Timings support to their plugin.

There are 2 versions of Timings
  - V1: Used by Spigot. Developed by Aikar and labeled "Spigot Timings"
  - V2: Relabeled "Minecraft Timings", and supported by many various server software products.
  
Timings v2 added a proper API, however implementing it meant that your plugin would REQUIRE Paper,
or else things would blow up around Timings.

This library will safely analyze the current environment, and load an appropriate timings integration
according to what is available.

If using on CraftBukkit, or older versions of Spigot before Timings got added, then a "no op" Timings
handler will be used that does nothing.

On Spigot, Timings v1 will be used.

On everything else (Paper 1.8.8+), v2 will be used.

## Usage

Simply add my maven repo to your plugin and add v1.0.2 as a dependency, and shade/shadow it in.
### Maven
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <!-- Repo -->
    <repositories>
        <repository>
            <id>aikar</id>
            <url>http://repo.aikar.co/nexus/content/groups/aikar/</url>
        </repository>
    </repositories>
    
    <!-- Dep -->
    <dependencies>
        <dependency>
            <groupId>co.aikar</groupId>
            <artifactId>minecraft-timings</artifactId>
            <version>1.0.4</version>
        </dependency>
    </dependencies>
    
    <!-- Shade -->
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <version>2.4.1</version>
                <configuration>
                    <dependencyReducedPomLocation>${project.build.directory}/dependency-reduced-pom.xml</dependencyReducedPomLocation>
                    <relocations>
                        <relocation>
                            <pattern>co.aikar.timings.lib</pattern>
                            <shadedPattern>[YOUR PLUGIN PACKAGE].timingslib</shadedPattern>
                        </relocation>
                    </relocations>
                </configuration>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```
### Gradle

```gradle

buildscript {
    repositories {
        jcenter()
    }

    dependencies {
        classpath "com.github.jengelman.gradle.plugins:shadow:1.2.4"
    }
}

apply plugin: "com.github.johnrengelman.shadow"
apply plugin: 'java'

sourceCompatibility = '1.8'
targetCompatibility = '1.8'

repositories {
    maven { url = "http://repo.aikar.co/nexus/content/groups/aikar/" }
    maven { url = "https://hub.spigotmc.org/nexus/content/groups/public/" }
}

dependencies {
    compile "co.aikar:minecraft-timings:1.0.4"
}

shadowJar {
   relocate 'co.aikar.timings.lib', '[YOUR PLUGIN PACKAGE].timingslib'
}

```

### Code
In your plugin
```java
private static TimingManager timingManager;
public void onEnable() {
    timingManager = TimingManager.of(this);
}
public static MCTiming timing(String name) {
    return timingManager.of(name);
}
```
Then use `YourPlugin.timing("Foo")` or don't use static and just use a dependency injection approach.
