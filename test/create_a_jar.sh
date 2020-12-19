#!/bin/bash
make release
cd build
jar -cfe puissance.jar Menu $(find . -name "*.class") #nom du fichier .jar #nom de la classe principale #liste des fichiers .class
cd ..
cp build/puissance.jar puissance.jar #nom du fichier.jar #nom du fichier .jar
