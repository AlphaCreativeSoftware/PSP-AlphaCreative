@echo off
set SERVER=UDP_ServidorOB
set CLIENTE=UDP_ClienteOB
set PERSONA=Persona

javac %SERVER%.java
javac %CLIENTE%.java
javac %SERVER%.java

start java %SERVER%
start java %CLIENTE%

REM Pausar la consola para ver la salida
pause