# 使用一个基础的Java镜像作为构建环境
#FROM openjdk:11-jdk AS build
FROM maven:3.6.3-jdk-11-slim
# 设置工作目录
WORKDIR /app
# 将应用程序的依赖项复制到容器中
COPY pom.xml .
COPY src ./src
COPY *.txt  ./
RUN mvn dependency:go-offline
# 构建应用程序
RUN mvn package -DskipTests
## 使用一个轻量级的Java镜像作为最终镜像
#FROM openjdk:11-jre-slim
# 设置工作目录
WORKDIR /app
# 从构建阶段复制应用程序
COPY --from=0 /app/target/image-0.0.1-SNAPSHOT.jar .

# 设置环境变量，用于传递commit hash
ARG COMMIT_HASH
ENV COMMIT_HASH=${COMMIT_HASH}
# 构建镜像时指定commit hash作为tag
LABEL commit_hash=${COMMIT_HASH}
ENV JVM_XMS="256m" \
    JVM_XMX="256m" \
    JVM_OPTS="-Xmx256m -Xms256m" \
    TZ=Asia/Shanghai
# 暴露应用程序的端口（如果需要）
EXPOSE 7777
# 启动应用程序
CMD ["java", "-jar", "image-0.0.1-SNAPSHOT.jar"]
#docker build -t study996/imageapi:tag 1.0.0 .