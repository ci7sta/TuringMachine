JFLAGS = -g
JC = javac
JVM= java
FILE=


.SUFFIXES: .java .class


.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	FileParser.java \
	runtm.java \
	State.java \
	Tape.java \
	Transition.java \
	TuringMachine.java

MAIN = runtm

default: classes


classes: $(CLASSES:.java=.class)
	cp ../runtm ./


run: $(MAIN).class
	$(JVM) $(MAIN)

clean:
	$(RM) *.class
