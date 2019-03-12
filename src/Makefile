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
	TuringMachine.java \
	Experimenter.java

MAIN = runtm

default: $(CLASSES:.java=.class)	
	cp ../runtm ./

clean:
	$(RM) *.class
