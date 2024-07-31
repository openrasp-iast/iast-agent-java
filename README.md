# IAST-AGENT-JAVA

Java agent
> Releases 基于 JDK8 发布

## 模块注释

### agent-install

独立模块，没有依赖其他模块，也不被其他模块依赖

### openrasp-v8

独立模块，被 `rasp-engine` 模块依赖

### rasp-boot

独立模块，被 `rasp-engine` 模块依赖

### rasp-engine

引擎模块，依赖 `rasp-boot` 和 `openrasp-v8`

## 编译条件

[//]: # (git config --global https.proxy http://192.168.198.1:10809)

[//]: # (git config --global http.proxy http://192.168.198.1:10809)

* 为了保证最大兼容性，我们建议使用 JDK 6 进行编译
* root 权限编译
* 安装压缩工具
  * ubuntu: `sudo apt-get install zip`
  * centos: `sudo yum install zip`
* [Java SE Development Kit 6u45](http://www.oracle.com/technetwork/java/javase/downloads/java-archive-downloads-javase6-419409.html)
* [apache-maven 3.2.3](https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.2.3/apache-maven-3.2.3-bin.zip)

## 编译方式

### 自动编译打包

```shell
# 执行一键脚本
sudo bash build-java.sh

```

### 手动编译

```shell
# 1. 编译 agent-install
cd agent-install/java
mvn clean package
cd ../../

# 2. 编译 openrasp
cd agent
mvn clean package
cd ../

```

## 打包发布

### 自动流程

```shell
# 创建一个标签
git tag v1.3.7
# 推送标签到 GitHub
git push origin v1.3.7
# 列出所有本地标签：
#   git tag
# 删除指定标签
#   git tag -d [标签名]
```

### 材料清单

```shell
# rasp-boot
agent/boot/target/rasp.jar

# rasp-engine
agent/engine/target/rasp-engine.jar

# agent-install
agent-install/java/target/RaspInstall.jar

# conf
agent-install/java/src/main/resources/openrasp.yml

# plugins
plugins/iast/plugin.js
```

### 发包结构

```shell
rasp-YYYY-MM-DD/
├── rasp
│   ├── conf
│   │   └── openrasp.yml
│   ├── plugins
│   │   └── official.js
│   ├── rasp-engine.jar
│   └── rasp.jar
└── RaspInstall.jar
```

### 配置 mvn 仓库

#### `~/.m2/settings.xml`

```xml

<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      http://maven.apache.org/xsd/settings-1.0.0.xsd">
    <localRepository/>
    <interactiveMode/>
    <usePluginRegistry/>
    <offline/>
    <pluginGroups/>
    <servers/>
    <mirrors>
        <mirror>
            <id>aliyunmaven</id>
            <mirrorOf>central</mirrorOf>
            <name>阿里云公共仓库</name>
            <url>https://maven.aliyun.com/repository/central</url>
        </mirror>
        <mirror>
            <id>repo1</id>
            <mirrorOf>central</mirrorOf>
            <name>central repo</name>
            <url>http://repo1.maven.org/maven2/</url>
        </mirror>
        <mirror>
            <id>aliyunmaven</id>
            <mirrorOf>apache snapshots</mirrorOf>
            <name>阿里云阿帕奇仓库</name>
            <url>https://maven.aliyun.com/repository/apache-snapshots</url>
        </mirror>
    </mirrors>
    <proxies/>
    <activeProfiles/>
    <profiles>
        <profile>
            <repositories>
                <repository>
                    <id>aliyunmaven</id>
                    <name>aliyunmaven</name>
                    <url>https://maven.aliyun.com/repository/public</url>
                    <layout>default</layout>
                    <releases>
                        <enabled>true</enabled>
                    </releases>
                    <snapshots>
                        <enabled>true</enabled>
                    </snapshots>
                </repository>
                <repository>
                    <id>MavenCentral</id>
                    <url>http://repo1.maven.org/maven2/</url>
                </repository>
                <repository>
                    <id>aliyunmavenApache</id>
                    <url>https://maven.aliyun.com/repository/apache-snapshots</url>
                </repository>
            </repositories>
        </profile>
    </profiles>
</settings>
```
