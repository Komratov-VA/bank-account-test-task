FROM openjdk:8
ADD target/bank-account-test-task.jar bank-account-test-task.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","bank-account-test-task.jar"]