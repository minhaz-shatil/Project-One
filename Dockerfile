FROM tomcat:10.1-jdk17-temurin

# Remove default applications (optional)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your WAR
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]