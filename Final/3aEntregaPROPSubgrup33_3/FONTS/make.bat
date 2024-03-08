@echo off

REM Script per construir i generar documentació per al projecte KEYBOARD DISTRIBUTION GENERATOR

REM Construint el projecte
echo Construint el projecte...
gradlew.bat build

REM Generant documentació
echo Generant documentació...
gradlew.bat javadoc

REM Creant un enllaç simbòlic a l'index.html de la documentació Javadoc
echo Creant un enllaç simbòlic per a la documentació Javadoc...
mklink javadocs build\docs\javadoc\index.html

echo Fet.
