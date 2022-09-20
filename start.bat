@echo off

echo Compiling Program...
javac -sourcepath ./src/ -d ./bin ./src/mtrx/Main.java

echo Running Program...
echo:
echo ============================
cd bin
java mtrx.Main
echo ============================