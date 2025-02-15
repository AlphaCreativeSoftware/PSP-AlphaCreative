@echo off
set SERVER=ServidorTCP
set CLIENTE=ClienteTCP

javac %SERVER%.java
javac %CLIENTE%.java

REM Ejecutar en ventanas nuevas, usando cmd /K para mantenerlas abiertas
start cmd /K java %SERVER%
start cmd /K java %CLIENTE%

exit