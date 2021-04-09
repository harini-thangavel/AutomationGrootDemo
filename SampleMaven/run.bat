set projectLocation=D:\Harini\Eclipse\SampleMaven
cd %projectLocation%
set classpath=%projectLocation%\src\test\java\TestCases; %projectLocation%\lib\*
java org.testng.TestNG %projectLocation%\testng.xml
