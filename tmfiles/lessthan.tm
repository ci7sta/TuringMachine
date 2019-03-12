states 10
start
getFirstBit
moveOverFirst0
moveOverFirst1
checkSecondBit0
checkSecondBit1
moveBack0
moveBack1
accept +
reject -
alphabet 7 1 0 # $ * X x
start 0 moveOverFirst0 x L
start 1 moveOverFirst1 x L
start # moveOverFirst0 # L

moveOverFirst0 # checkSecondBit0 # R
moveOverFirst0 X moveOverFirst0 X R
moveOverFirst0 0 moveOverFirst0 0 R
moveOverFirst0 1 moveOverFirst0 1 R
moveOverFirst0 x moveOverFirst0 x R

moveOverFirst1 # checkSecondBit1 # R
moveOverFirst1 X moveOverFirst1 X R
moveOverFirst1 0 moveOverFirst1 0 R
moveOverFirst1 1 moveOverFirst1 1 R
moveOverFirst1 x moveOverFirst1 x R

checkSecondBit0 0 moveBack0 X L
checkSecondBit0 _ accept _ R
checkSecondBit0 1 reject 1 R
checkSecondBit0 X checkSecondBit0 X R

checkSecondBit1 1 moveBack0 X L
checkSecondBit1 0 accept X L
checkSecondBit1 _ accept _ R
checkSecondBit1 X checkSecondBit1 X R

moveBack0 X moveBack0 X L
moveBack0 0 moveBack0 0 L
moveBack0 1 moveBack0 1 L
moveBack0 # moveBack1 # L

moveBack1 0 moveBack1 0 L
moveBack1 1 moveBack1 1 L
moveBack1 X getFirstBit X L
moveBack1 x getFirstBit x L
moveBack1 # getFirstBit # L

getFirstBit X getFirstBit X R
getFirstBit x getFirstBit x R
getFirstBit 0 moveOverFirst0 X R
getFirstBit 1 moveOverFirst1 X R
getFirstBit # checkSecondBit0 # R