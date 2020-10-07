# graphQL-spring-demo Project
This project is built with Maven, Spring, Hibernate and GraphQL

## 1. Prerequisites - Before running locally the project

### How to install Maven
Follow the steps from https://maven.apache.org/install.html

### Setting the database
1. Download & Install the Workbench IDE: https://dev.mysql.com/downloads/workbench/
2. Download and Install MySql Server: https://dev.mysql.com/downloads/mysql/5.6.html
> **Note:** Please remember to run the *mysqld.exe*

3. After pulling the code from Git, do the following:
> - Create a connection in Workbench with:
>> - ***username***: root
>> - ***password***: 

> -  Run the ***\utils\sql\00_Database Generation Script.sql*** in order to create the database and the table
> - Import the data from ***\utils\sql\Data Import.csv***, by following the next steps: https://dev.mysql.com/doc/workbench/en/wb-admin-export-import-table.html

## 2. Running the application

### How to start the application
Start the application, by running the script ***\utils\start.bat***

#### How to clean the cached build output
In Maven based project, many cached output existed in your “target” folder. When you want to build your project for deployment, you have to make sure clean all the cached output so that you are always get the latest for deployment.

To clean your project cached output, please run the script ***\utils\clean.bat***

## 3. Explanation of the code step by step
First, we need to add the dependencies to the project, so that we can use within the Java code.

### Maven Build - pom.xml
We need:

**graphql-java-tools:**
```
<dependency>
			<groupId>com.graphql-java</groupId>
			<artifactId>graphql-java-tools</artifactId>
			<version>5.2.4</version>
</dependency>
```
**graphql-spring-boot-starter:**
```
<dependency>
			<groupId>com.graphql-java</groupId>
			<artifactId>graphql-spring-boot-starter</artifactId>
			<version>5.0.2</version>
</dependency>
```
**mysql-connector-java:**
```
<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.14</version>
</dependency>
```

The whole pom.xml is:
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.demo.graphql</groupId>
    <artifactId>graphql-demo</artifactId>
    <version>1.0-SNAPSHOT</version>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.6.RELEASE</version>
        <relativePath />
    </parent>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>com.graphql-java</groupId>
            <artifactId>graphql-spring-boot-starter</artifactId>
            <version>5.0.2</version>
        </dependency>
        <dependency>
            <groupId>com.graphql-java</groupId>
            <artifactId>graphql-java-tools</artifactId>
            <version>5.2.4</version>
        </dependency>
        <dependency>
            <groupId>com.graphql-java</groupId>
            <artifactId>graphiql-spring-boot-starter</artifactId>
            <version>5.0.2</version>
        </dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.14</version>
	    </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

### Java code
**Create the Entity class - to link the entity to the database table through Hibernate**
```
package com.demo.graphql.dao.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vehicle implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "model_code", nullable = false)
	private String modelCode;

	@Column(name = "brand_name")
	private String brandName;

	@Column(name = "launch_date")
	private LocalDate launchDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public LocalDate getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(LocalDate launchDate) {
		this.launchDate = launchDate;
	}

}
```
**Create the Repository class - to extend the JpaRepository**
```
package com.demo.graphql.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.graphql.dao.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}
```

**Create the schema - vehicle.graphqls**
```
type Vehicle {
	id: ID!,
	type: String,
	modelCode: String,
	brandName: String,
	launchDate: String
}

type Query {
	vehicles(count: Int):[Vehicle]
	vehicle(id: ID):Vehicle
}

type Mutation {
	createVehicle(type: String!, modelCode: String!, brandName: String, launchDate: String):Vehicle
}

```


**Create the Mutation Resolver class - to extend the GraphQLMutationResolver**
```
package com.demo.graphql.mutation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.demo.graphql.dao.entity.Vehicle;
import com.demo.graphql.service.VehicleService;

@Component
public class VehicleMutation implements GraphQLMutationResolver {

    @Autowired
    private VehicleService vehicleService;

    public Vehicle createVehicle(final String type, final String modelCode, final String brandName, final String launchDate) {
        return this.vehicleService.createVehicle(type, modelCode, brandName, launchDate);
    }
}

```

**Create the Query Resolver class - to extend the GraphQLQueryResolver**
```
package com.demo.graphql.query;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.demo.graphql.dao.entity.Vehicle;
import com.demo.graphql.service.VehicleService;

@Component
public class VehicleQuery implements GraphQLQueryResolver {

    @Autowired
    private VehicleService vehicleService;

    public List<Vehicle> getVehicles(final int count) {
        return this.vehicleService.getAllVehicles(count);
    }

    public Optional<Vehicle> getVehicle(final int id) {
        return this.vehicleService.getVehicle(id);
    }
}

```

**Configure the application.properties to match your local environment**
```
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=none
spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect

# Enable spring data repos
spring.data.jpa.repositories.enabled=true

# Replace with your connection string
spring.datasource.url=jdbc:mysql://localhost:3306/demographql

# Replace with your credentials
spring.datasource.username=root
spring.datasource.password=

spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver

```