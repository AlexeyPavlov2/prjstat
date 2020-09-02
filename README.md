## prjstat-maven-plugin  
---  

Этот Maven-plugin предназначен для генерации статистики проекта - подсчитывает количество и размер файлов с заданными расширениями.    

Использование  
- клонируйте проект на свой компьютер
- соберите проект, артефакт инсталлируется в локальный maven-респозиторий
- включите в раздел <build> pom.xml целевого Java-проекта
- соберите целевой проект
- в окне Run IDE можно будет увидеть:

```
**********************************************
Project statistic <prjstat-maven-plugin>
GroupId: ru.petsproject.app
ArtifactId: simple-ad
Version: 2.1.0

Extension    Count     Size        
JAVA         1030      2 341 447   
XML          29        327 971     
PROPERTIES   6         15 490      
JSON         410       857 545     
SQL          228       520 194     
TXT          1         21 675      
HTML         1         444         
Total        1705      4 084 766   
**********************************************

```

Настройка плагина  
  
Плагин не имеет обязательных параметров, но может иметь следующие:  
- *isActive* - принимает значения true/false. Если укзать значение false, плагин запускаться не будет.
- *includeFileExtensions* - строка с перечисленными расширениями файлов проекта по которым будет вестись подсчеты.  
По умолчанию обрабатываются расширения *java, xml, properties, yml, json, md, sql, txt, html, css, js, jsp, jsf*.  
- *exludeDirectories* - строка с перечисленными наименованиями директорий. Если абсолютный путь будет содержать такое наименование, файлы из такой директории будут исключены из подсчетов.  
По умолчанию - *target, .git, .idea, .mvn*.

Пример конфигурации  

```
            <plugin>
                <groupId>com.apavlov2</groupId>
                <artifactId>prjstat-maven-plugin</artifactId>
                <version>1.0</version>
                <executions>
                    <execution>
                        <phase>install</phase>
                        <goals>
                            <goal>project-statistic</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <isActive>true</isActive>
                    <includeFileExtensions>java, xml, properties, yml, json, md, sql, txt, html, css, js, jsp, jsf, class</includeFileExtensions>
                    <exludeDirectories>target, .git, .idea, .mvn</exludeDirectories>
                </configuration>
            </plugin>
```  
  

