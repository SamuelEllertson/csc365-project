TARGET   = Main
JC       = javac
JCFLAGS  = -classpath "mysql-connector.jar" -Xlint:deprecation -Xlint:all
JVM      = java
JVMFLAGS = -classpath "mysql-connector.jar"
SOURCES  = *.java
OBJECTS  = *.class

all:$(OBJECTS)
	
$(OBJECTS):$(SOURCES)
	$(JC) $(JCFLAGS) $(SOURCES)

run:$(OBJECTS)
	$(JVM) $(JVMFLAGS) $(TARGET)

clean:
	rm -f $(OBJECTS)
