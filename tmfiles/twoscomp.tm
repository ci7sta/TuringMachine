states 19
start
checkAllZero
checkAllZeroFinal
returnToStart1
returnToStart2
returnToStart3
returnForNextBit1
returnForNextBit2
checkBit
goToCheck1
goToCheck0
checkFor0
checkFor1
scanRight
carryBit
flipBits
finalCheck
accept +
reject -
alphabet 7 1 0 $ * # X x
start 0 checkAllZero * R
start 1 flipBits $ L

checkAllZero 0 checkAllZero 0 R
checkAllZero 1 returnToStart1 1 L
checkAllZero # checkAllZeroFinal # R

checkAllZeroFinal 0 checkAllZeroFinal 0 R
checkAllZeroFinal _ accept _ R

flipBits 0 flipBits 1 R
flipBits 1 flipBits 0 R
flipBits * flipBits $ R
flipBits $ flipBits * R
flipBits # returnToStart3 # L

returnToStart1 0 returnToStart1 0 L
returnToStart1 1 returnToStart1 1 L
returnToStart1 * flipBits * L
returnToStart1 $ flipBits $ L

returnToStart2 0 returnToStart2 0 L
returnToStart2 1 returnToStart2 1 L
returnToStart2 * checkBit * L
returnToStart2 $ checkBit $ L

checkBit 0 goToCheck0 X R
checkBit X checkBit X R
checkBit 1 goToCheck1 X R
checkBit * goToCheck0 x R
checkBit $ goToCheck1 x R
checkBit # finalCheck # R

finalCheck X finalCheck X R
finalCheck _ accept _ R

goToCheck0 0 goToCheck0 0 R
goToCheck0 X goToCheck0 X R
goToCheck0 1 goToCheck0 1 R
goToCheck0 # checkFor0 # R

goToCheck1 0 goToCheck1 0 R
goToCheck1 X goToCheck1 X R
goToCheck1 1 goToCheck1 1 R
goToCheck1 # checkFor1 # R

checkFor0 0 returnForNextBit1 X L
checkFor0 X checkFor0 X R
checkFor0 1 reject 1 R
checkFor0 _ reject _ R

checkFor1 1 returnForNextBit1 X L
checkFor1 X checkFor1 X R
checkFor1 0 reject 0 R
checkFor1 _ reject _ R

returnForNextBit1 # returnForNextBit2 # L
returnForNextBit1 0 returnForNextBit1 0 L
returnForNextBit1 1 returnForNextBit1 1 L
returnForNextBit1 X returnForNextBit1 X L

returnForNextBit2 0 returnForNextBit2 0 L
returnForNextBit2 1 returnForNextBit2 1 L
returnForNextBit2 x checkBit x R
returnForNextBit2 X checkBit X R

returnToStart3 0 returnToStart3 0 L
returnToStart3 1 returnToStart3 1 L
returnToStart3 * scanRight * L
returnToStart3 $ scanRight $ L

scanRight 1 scanRight 1 R
scanRight 0 scanRight 0 R
scanRight # carryBit # L
scanRight $ scanRight $ R
scanRight * scanRight * R

carryBit 1 carryBit 0 L
carryBit 0 returnToStart2 1 L
carryBit * returnToStart2 $ L
carryBit $ returnToStart2 * L
