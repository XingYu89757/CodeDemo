## 使用mybatis-plus逆向生成类

> 具体使用分为新旧两个版本

### 老版本

查看 CodeGenerator类
使用pom配置为

~~~
 <!-- Mybatis-Plus逆向工程生成 -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-generator</artifactId>
            <version>3.3.1</version>
        </dependency>
        <dependency>
            <groupId>org.apache.velocity</groupId>
            <artifactId>velocity-engine-core</artifactId>
            <version>2.1</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </dependency>

~~~

### 新版本

查看FastAutoGenerator类

~~~
    <!-- Mybatis-Plus逆向工程生成 -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-generator</artifactId>
            <version>3.5.3</version>
        </dependency>
        <!--导入模板引擎-->
        <dependency>
            <groupId>org.mybatis.scripting</groupId>
            <artifactId>mybatis-velocity</artifactId>
            <version>2.1.1</version>
        </dependency>
<!--        <dependency>-->
<!--            <groupId>org.mybatis.scripting</groupId>-->
<!--            <artifactId>mybatis-freemarker</artifactId>-->
<!--            <version>1.2.3</version>-->
<!--        </dependency>-->

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </dependency>
~~~
