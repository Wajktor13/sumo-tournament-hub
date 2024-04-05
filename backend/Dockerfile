FROM eclipse-temurin:17-jdk-focal

# Set environment variables
ENV GRADLE_HOME /opt/gradle

# Create gradle user and group
RUN set -o errexit -o nounset \
    && echo "Adding gradle user and group" \
    && groupadd --system --gid 1000 gradle \
    && useradd --system --gid gradle --uid 1000 --shell /bin/bash --create-home gradle \
    && mkdir /home/gradle/.gradle \
    && chown --recursive gradle:gradle /home/gradle \
    && chmod --recursive o+rwx /home/gradle \
    \
    && echo "Symlinking root Gradle cache to gradle Gradle cache" \
    && ln --symbolic /home/gradle/.gradle /root/.gradle

# Set volume
VOLUME /home/gradle/.gradle

# Set working directory
WORKDIR /home/gradle

# Install necessary packages
RUN set -o errexit -o nounset \
    && apt-get update \
    && apt-get install --yes --no-install-recommends \
        unzip \
        wget \
        \
        bzr \
        git \
        git-lfs \
        mercurial \
        openssh-client \
        subversion \
    && rm --recursive --force /var/lib/apt/lists/* \
    \
    && echo "Testing VCSes" \
    && which bzr \
    && which git \
    && which git-lfs \
    && which hg \
    && which svn

# Set Gradle version
ENV GRADLE_VERSION 8.7
ARG GRADLE_DOWNLOAD_SHA256=544c35d6bd849ae8a5ed0bcea39ba677dc40f49df7d1835561582da2009b961d

# Download and install Gradle
RUN set -o errexit -o nounset \
    && echo "Downloading Gradle" \
    && wget --no-verbose --output-document=gradle.zip "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip" \
    \
    && echo "Checking Gradle download hash" \
    && echo "${GRADLE_DOWNLOAD_SHA256} *gradle.zip" | sha256sum --check - \
    \
    && echo "Installing Gradle" \
    && unzip gradle.zip \
    && rm gradle.zip \
    && mv "gradle-${GRADLE_VERSION}" "${GRADLE_HOME}/" \
    && ln --symbolic "${GRADLE_HOME}/bin/gradle" /usr/bin/gradle

# Switch to gradle user
USER gradle

# Test Gradle installation
RUN set -o errexit -o nounset \
    && echo "Testing Gradle installation" \
    && gradle --version

# Switch back to root user
USER root

# Copy Spring Boot application JAR file
COPY build/libs/sumotournamenthub-backend-0.0.1-SNAPSHOT.jar /home/gradle/sumotournamenthub-backend.jar

# Set command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/home/gradle/sumotournamenthub-backend.jar","-web -webAllowOthers -tcp -tcpAllowOthers -browser"]
EXPOSE 8080
