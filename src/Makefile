all:
	javac application/*.java
	jar -cmf manifest.txt ../executable.jar application

run:
	javac application/*.java
	java application.Main teamList.txt
	
clean:
	\rm application/*.class
