@echo off

set MODULER=%1

echo %MODULER%

set "JAVA_OPT=-server -Xms256M -Xmx256M -Xss512k -XX:MetaspaceSize=64M -XX:MaxMetaspaceSize=128M -XX:+UseG1GC"

echo %JAVA_OPT%

START "%MODULER%" javaw.exe %JAVA_OPT% -Dspring.profiles.active=prod -jar lamp-%MODULER%.jar lamp-%MODULER%

