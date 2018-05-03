all:
	javac application/*.java
	jar -cmf manifest.txt ../executable.jar application

run:
	javac application/*.java
	java -jar ../executable.jar teamList.txt
	
clean:
	\rm application/*.class
