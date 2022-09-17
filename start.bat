@echo off

cd %CD%/src/

echo Compiling Program...
javac -d ../bin ./mtrx/*.java

echo Running Program...
echo:
echo ============================
cd ..
cd %CD%/bin
java mtrx.Main
echo --------------------------------
cd ..