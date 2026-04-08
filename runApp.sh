#!/bin/bash
mkdir -p out
javac src/main/*.java -d out
java -cp out main.MainMenu