This project is created for checking the issue related to tomcat and servicecomb combination.

# Steps to build
1. Befor build make sure the dependency is correct. Please download the cse dependency manually and add it.
   https://github.com/huawei-microservice-demo/huawei-microservice-demo
   or
   define the mirror like below in maven settings.xml

  <mirrors>
    <mirror>
      <id>mirrorId</id>
      <mirrorOf>*</mirrorOf>
      <name>Mirror of central repository.</name>
      <url>http://maven.huaweicse.com/nexus/content/groups/public</url>
    </mirror>
 </mirrors>

2. mvn clean install
it will generate the WAR file.

3. Local service center is required for this demo.
4. Please copy the WAR file to /tomcatpath/var/lib.. It will automatically deploy the application.
5. Please check about the API call.
   http://127.0.1.1:9093/hello?name=ram






