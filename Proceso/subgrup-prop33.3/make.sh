#!/bin/bash

mainPath="src/main/java/edu/upc/prop/cluster33"
classPath="/edu/upc/prop/cluster33"

javac --release 11 ${mainPath}/**/*.java -Xlint:none

classDir="../EXE/CLASS${classPath}"


mkdir -p ${classDir}/domini
mkdir -p ${classDir}/drivers
mkdir -p ${classDir}/excepcions
mkdir -p ${classDir}/presentacio
mkdir -p ${classDir}/Stubs
mkdir -p ${classDir}/texts
mkdir -p ${classDir}/utils


mv ${mainPath}/domini/*.class ${classDir}/domini/
mv ${mainPath}/drivers/*.class ${classDir}/drivers/
mv ${mainPath}/excepcions/*.class ${classDir}/excepcions/
mv ${mainPath}/presentacio/*.class ${classDir}/presentacio/
mv ${mainPath}/Stubs/*.class ${classDir}/Stubs/
cp ${mainPath}/texts/* ${classDir}/texts/
mv ${mainPath}/utils/*.class ${classDir}/utils/