compile :
	javac src/academic/model/*.java src/academic/driver/*.java -d bin

test_01 :
	java -cp "bin;./libs/*" academic.driver.Driver1
