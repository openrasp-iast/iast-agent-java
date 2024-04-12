# RaspInstall 源代码

自动安装包错误代码请参考: [自动安装失败](https://rasp.baidu.com/doc/install/software.html#faq-1)

```shell
mvn install:install-file -Dfile=lib/tools.jar -DgroupId=jdk.tools  -DartifactId=jdk.tools -Dversion=1.8 -Dpackaging=jar
```