@echo off
set OBJECT=Factura
set SERVER=ServidorUDP
set CLIENTE=ClienteUDP

javac %OBJECT%.java
javac %SERVER%.java
javac %CLIENTE%.java

REM Ejecutar en ventanas nuevas, usando cmd /K para mantenerlas abiertas
start cmd /K java %SERVER%
start cmd /K java %CLIENTE%

exit