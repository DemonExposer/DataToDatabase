#!/bin/bash
git clone https://github.com/MartMonster/DataToDatabase
cd DataToDatabase/src
javac *.java
jar cfe Seeder.jar DatabaseManager *.class
rm *.class
mv Seeder.jar ../
cd ..
java -jar Seeder.jar
