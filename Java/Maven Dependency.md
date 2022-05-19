### **Maven**

빌드 툴이지만 라이브러리 의존성 관리 툴로 더 많이 사용된다.

스프링 프레임웍이나 전자정부 표준 프레임웍을 사용하게 되면 프로젝트의 기본 빌드 설정을 그대로 사용해서 별로 문제가 되는게 없으므로 손댈 것이 별로 없다.

대부분 필요한 라이브러리를 추가하거나 하기 위해서 **저장소(repository)**와 **의존성(dependency)** 부분을 많이 사용한다.

Maven은 **[http://maven.apache.org](http://maven.apache.org/)** 에서 다운로드 받아서 단독으로 설치해서 사용할 수도 있다. 

예전의 자바 웹 프로젝트에서는 필요한 라이브러리 파일이 **/WEB-INF/lib** 폴더와 **WAS**에 있게 된다. WAS에서 기본 제공하거나 여러 Context 에서 공통적으로 사용되는 것은 WAS가 가지고 있고, 현재 Context 에서만 사용될 것은 /WEB-INF/lib 폴더에 두게 된다.

여기서 발생되는 문제로는 프로젝트가 오래될 경우 WAS에 의존적인 라이브러리들이나 구 버전의 라이브러리들을 구할 수 없게되는 경우가 자주 발생한다.

Maven을 사용해서 라이브러리를 관리하면 이러한 경우에 좀 더 잘 대처할 수 있다.

Maven을 사용하는 프로젝트에는 설정 파일인 **pom.xml** 파일이 있다.

<br>

### POM (Project Object Model)

이 설정에서 실제 라이브러리 파일이 있는 저장소 서버의 위치를 지정하고, 또한 이 프로젝트에서 사용할 라이브러리가 무엇인지를 지정하기만 하면 된다.

Maven은 글로벌 저장소에서 필요한 라이브러리를 로컬 컴퓨터로 자동으로 다운로드하고, 로컬 저장소의 라이브러리들로 클래스 패스를 지정하여 프로젝트에서 사용하도록 한다.

프로젝트를 배포하기 위해서 Maven build를 하면 의존성 설정에 따라 필요한 라이브러리를 `/WEB-INF/lib` 폴더로 복사해서 배포가능한 폴더 구조로 파일들을 모아주고, war 파일도 작성한다.

Maven을 사용하여 프로젝트의 라이브러리 의존성을 관리하면 실제 .jar 파일들을 관리할 필요없이 pom.xml 파일만 잘 관리하면 된다.

<br>

### eclipse에 설치된 Maven의 버전을 확인하는 방법

메뉴 > **Window -> Preferences**를 선택

좌측 설정항목 트리에서 **Maven -> Installations**를 선택하면 현재 Maven버전을 확인할 수 있다. 

<br>

### pom.xml 파일의 구조

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="<http://maven.apache.org/POM/4.0.0>" xmlns:xsi="<http://www.w3.org/2001/XMLSchema-instance>"
	xsi:schemaLocation="<http://maven.apache.org/POM/4.0.0> <http://maven.apache.org/maven-v4_0_0.xsd>">

	<modelVersion>4.0.0</modelVersion>
	<groupId>com.tistory.pentode</groupId>
	<artifactId>spring_ajax</artifactId>
	<name>spring_ajax</name>
	<packaging>war</packaging>
	<version>1.0.0-BUILD-SNAPSHOT</version>

	<properties>
		<java-version>1.7</java-version>
		<org.springframework-version>4.3.4.RELEASE</org.springframework-version>
		<org.aspectj-version>1.6.10</org.aspectj-version>
		<org.slf4j-version>1.6.6</org.slf4j-version>
	</properties>

    <repositories>
        <repository>
            <id>oracle</id>
            <url><http://maven.jahia.org/maven2></url>
        </repository>
    </repositories>

	<dependencies>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
        <dependency>
            <groupId>com.oracle</groupId>
            <artifactId>ojdbc6</artifactId>
            <version>12.1.0.2</version>
        </dependency>
    <dependencies>

    <build>
    </build>
</project>

```

- **<groupId>** - 프로젝트를 구분하는 값으로 사용. 도메인을 주로 사용
- **<artifactId>** - 프로젝트를 구분하는 값으로 사용되며, 프로젝트 명을 사용
- **<properties>** - POM 내에서 공통적으로 사용되는 값들을 설정. pom.xml 파일의 다른 곳에서 ${org.springframework-version} 로 작성함
- **<repositories>** - 라이브러리를 받아올 저장소를 지정
- **<dependencies>** - 프로젝트에서 사용되는 라이브러리들을 지정
- **<build>** - 프로젝트의 빌드방법을 지정

  <br>
  
### 의존성 설정

이 부분이 실제 프로젝트에서 사용되는 라이브러리를 지정하는 부분

Maven은 이 라이브러리를 저장소에서 찾아서 사용하게 되는 것이다. 

```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.7</version>
    <scope>test</scope>
</dependency>

```

다른 부분은 그대로 사용하면 되는데 <scope> 부분은 사용자가 필요한 형태도 지정하여 사용하면 된다. 

junit는 테스트시에만 필요하고, 배포할 때는 빠져야 하는 라이브러리다. 

이럴경우 `<scope>test</scope>`로 지정을 하면 된다.

Maven 기본 글로벌 저장소에 필요한 라이브러리가 없을 경우에는 라이브러리가 존재하는 저장소를 pom.xml 파일의 `<repositories>` 에 추가하면 된다.

Maven 저장소로 라이브러리가 제공되지 않는 경우에는 .jar 파일을 다운 받아 의존성을 system 스코프로 관리하던가 아니면, 직접 `/WEB-INF/lib` 폴더에 넣고 마우스 오른쪽 키를 눌러 add build path 를 선택해서 이클립스 빌드패스에 등록하여 사용한다.

  <br>
  
### JAR(Java Archive, 자바 아카이브)

<aside>
💡 여러개의 자바 클래스 파일과, 클래스들이 이용하는 관련 리소스(텍스트, 그림 등) 및 메타데이터를 하나의 파일로 모아서 자바 플랫폼에 응용 소프트웨어나 라이브러리를 배포하기 위한 소프트웨어 패키지 파일 포맷

</aside>

프로젝트 내에 있는 특정 클래스를 다른 프로젝트의 클래스에서 라이브러리처럼 사용하고 싶을 때 Jar 파일로 압축하여 사용한다.
