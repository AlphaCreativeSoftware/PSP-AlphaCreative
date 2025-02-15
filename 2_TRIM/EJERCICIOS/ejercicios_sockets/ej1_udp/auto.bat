@echo off
set SERVER=ServidorUDP
set CLIENTE1=ClienteUDP1
set CLIENTE2=ClienteUDP2

javac %SERVER%.java
javac %CLIENTE1%.java
javac %CLIENTE2%.java

REM Ejecutar en ventanas nuevas, usando cmd /K para mantenerlas abiertas
start cmd /K java %SERVER%
start cmd /K java %CLIENTE1%
start cmd /K java %CLIENTE2%

pause