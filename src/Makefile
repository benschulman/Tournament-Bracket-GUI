all:
	javac application/*.java
	jar -cmf manifest.txt ../executable.jar application

run:
	javac application/*.java
	jar -cmf manifest.txt ../executable.jar application
	java -jar ../executable.jar teamList.txt
	
clean:
	\rm application/*.class
