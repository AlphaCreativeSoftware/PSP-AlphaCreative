@echo off
set SERVER=ServidorTCP
set CLIENTE=ClienteTCP
set OUT_DIR=compiled

REM Crear la carpeta compiled si no existe
if not exist %OUT_DIR% mkdir %OUT_DIR%

REM Compilar los archivos Java y guardarlos en la carpeta compiled
javac -d %OUT_DIR% %SERVER%.java
javac -d %OUT_DIR% %CLIENTE%.java

REM Ejecutar en ventanas nuevas, usando cmd /K para mantenerlas abiertas
start cmd /K java -cp %OUT_DIR% %SERVER%
start cmd /K java -cp %OUT_DIR% %CLIENTE%
start cmd /K java -cp %OUT_DIR% %CLIENTE%
start cmd /K java -cp %OUT_DIR% %CLIENTE%
start cmd /K java -cp %OUT_DIR% %CLIENTE%

exit