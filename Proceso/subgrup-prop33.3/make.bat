@echo off
setlocal

set mainPath=src\main\java\edu\upc\prop\cluster33
set classPath=\edu\upc\prop\cluster33

:: Compiling Java files. Note: Windows doesn't support '**', you might need to list directories manually
javac --release 11 %mainPath%\**\*.java -Xlint:none

set classDir=..\EXE\CLASS%classPath%

:: Creating directories
if not exist "%classDir%\domini" mkdir "%classDir%\domini"
if not exist "%classDir%\drivers" mkdir "%classDir%\drivers"
if not exist "%classDir%\excepcions" mkdir "%classDir%\excepcions"
if not exist "%classDir%\presentacio" mkdir "%classDir%\presentacio"
if not exist "%classDir%\Stubs" mkdir "%classDir%\Stubs"
if not exist "%classDir%\texts" mkdir "%classDir%\texts"
if not exist "%classDir%\utils" mkdir "%classDir%\utils"

:: Moving and copying files
move "%mainPath%\domini\*.class" "%classDir%\domini\"
move "%mainPath%\drivers\*.class" "%classDir%\drivers\"
move "%mainPath%\excepcions\*.class" "%classDir%\excepcions\"
move "%mainPath%\presentacio\*.class" "%classDir%\presentacio\"
move "%mainPath%\Stubs\*.class" "%classDir%\Stubs\"
xcopy /I /Y "%mainPath%\texts\*" "%classDir%\texts\"
move "%mainPath%\utils\*.class" "%classDir%\utils\"

endlocal
