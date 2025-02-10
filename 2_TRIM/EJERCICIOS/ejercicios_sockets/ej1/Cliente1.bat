@echo off
set COMPILED=Cliente1_TCP

javac %COMPILED%.java
java %COMPILED%

REM Pausar la consola para ver la salida
pause