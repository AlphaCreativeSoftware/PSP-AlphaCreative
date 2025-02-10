@echo off
set COMPILED=ServidorTCP

javac %COMPILED%.java
java %COMPILED%

REM Pausar la consola para ver la salida
pause