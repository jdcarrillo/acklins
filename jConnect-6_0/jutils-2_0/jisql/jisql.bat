@echo off
set VMCLASSPATH=;jisql.jar;%CLASSPATH%

rem build the command line arguments
set jisqlParams=%1
:getparam
shift
if "%1" == "" goto doneWithParams
set jisqlParams=%jisqlParams% %1
goto getparam
:doneWithParams

:chkjava1
REM  check for Java in the current path
java -classpath "%VMCLASSPATH%" com.sybase.jisql.util.CheckEnvironment noopt
if not errorlevel 1 goto gotjava2
goto chkjava2
:gotjava2
set JAVAVM=java -classpath
goto chkswing

:chkjava2
REM  check for Java from the JAVA_HOME environment variable
if "%JAVA_HOME%" == "" goto chkjava3
if not exist "%JAVA_HOME%\bin\java.exe" goto chkjava3
if not exist "%JAVA_HOME%\lib\classes.zip" goto runjava2
set VMCLASSPATH=%VMCLASSPATH%;%JAVA_HOME%\lib\classes.zip
:runjava2
"%JAVA_HOME%\bin\java.exe" -classpath ".;%VMCLASSPATH%" com.sybase.jisql.util.CheckEnvironment noopt 
if not errorlevel 1 goto gotjava2
goto chkjava3
:gotjava2
set JAVAVM="%JAVA_HOME%\bin\java.exe" -classpath
goto chkswing

:chkjava3
REM  check for jview
if not exist "%SYSTEMROOT%\jview.exe" goto chkjava4
"%SYSTEMROOT%\jview.exe" /cp ".;%VMCLASSPATH%" com.sybase.jisql.util.CheckEnvironment noopt
if not errorlevel 1 goto gotjava3
goto chkjava4
:gotjava3
set JAVAVM="%SYSTEMROOT%\jview.exe" /cp
goto chkswing

:chkjava4
REM  check for jview
if not exist "%windir%\jview.exe" goto no_java
"%windir%\jview.exe" /cp ".;%VMCLASSPATH%" com.sybase.jisql.util.CheckEnvironment noopt
if not errorlevel 1 goto gotjava4
goto no_java
:gotjava4
set JAVAVM="%windir%\jview.exe" /cp
goto chkswing

:chkswing
%JAVAVM% "%VMCLASSPATH%" com.sybase.jisql.util.CheckEnvironment swing 
if not errorlevel 1 goto chkjconn
if "%SWING_HOME%" == "" goto noswing
set VMCLASSPATH=%VMCLASSPATH%;%SWING_HOME%\swingall.jar
REM re-run with swing in classpath
%JAVAVM% "%VMCLASSPATH%" com.sybase.jisql.util.CheckEnvironment swing 
if not errorlevel 1 goto chkjconn
goto noswing

:chkjconn
REM first, see if we have a jConnect already in our CLASSPATH or PATH
%JAVAVM% "%VMCLASSPATH%" com.sybase.jisql.util.CheckEnvironment jconnect 
if not errorlevel 1 goto runjisql
if "%JDBC_HOME%" == "" goto nojconn
REM secondly, try with jConnect 4.x in classpath
set VMCLASSPATH=%VMCLASSPATH%;%JDBC_HOME%\classes
%JAVAVM% "%VMCLASSPATH%" com.sybase.jisql.util.CheckEnvironment jconnect
if not errorlevel 1 goto runjisql
REM finally, try with jConnect 5.x in classpath
set VMCLASSPATH=%VMCLASSPATH%;%JDBC_HOME%\classes\jconn2.jar
%JAVAVM% "%VMCLASSPATH%" com.sybase.jisql.util.CheckEnvironment jconnect
if not errorlevel 1 goto runjisql
goto nojconn

:runjisql
%JAVAVM% "%VMCLASSPATH%" com.sybase.jisql.Jisql %jisqlParams%
goto end

:nojconn
    echo If you use jConnect 4.x, set JDBC_HOME to the directory where jConnect 4.x is installed. If you use jConnect 5.x, set JDBC_HOME to the directory where jConnect 5.x is installed and set your classpath to include %%JDBC_HOME%%\classes\jconn2.jar.
    goto end
goto end

:noswing
    echo You need to set SWING_HOME to the directory where swingall.jar is located.
    goto end
goto end

:no_java
    echo You need to install one of the following Java VMs:
    echo - the JDK, and set JAVA_HOME to the directory where java.exe is    
    echo - the JRE, and set JAVA_HOME to the directory where jre.exe is
    echo - the SDK, and be sure jview.exe is installed
    goto end
goto end

:end
set VMCLASSPATH=
set JAVAVM=
set jisqlParams=

