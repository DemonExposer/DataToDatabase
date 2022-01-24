#!/bin/bash
cd src
javac *.java
jar cfe Seeder.jar DatabaseManager *.class
rm *.class
mv Seeder.jar ../
cd ..
java -jar Seeder.jar
