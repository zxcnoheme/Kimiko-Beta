@echo off
cd /d "%~dp0"

set "JAVA_HOME=C:\Program Files\Java\jdk-25.0.3+9"
set "GRADLE_USER_HOME=G:\.gradle"

call gradlew.bat runClient -g "%GRADLE_USER_HOME%" --console=plain
