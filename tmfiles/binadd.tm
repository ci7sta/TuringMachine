states 40
start
validateTape1
validateTape2
validateTape3
tapeValidated
check1
moveOver1Seen0
moveOver1Seen1
check2Seen1
check2Seen0
check2Seen0Empty
goEndCount0
goEndCount1
goEndCount2
goEnd1Count0
goEnd1Count1
goEnd1Count2
atEndWrite0
atEndWrite1
atEndWrite2
goBackToStart1
goToEnd
goToEnd1
moveToReWriteEnd
reWriteEnd
reWriteEndCarry
seenX
moveToCheckEq
moveToCheckEq1
moveToCheckResult1
moveToCheckResult0
checkBit
checkResult1
checkResult0
removeDelim
removeDelim0
removeDelim1
removeDelimBlank
accept +
reject -
alphabet 9 0 1 2 # $ * X x .
start 0 validateTape1 * L
start 1 validateTape1 $ L
start # validateTape2 . R

check1 0 moveOver1Seen0 X R
check1 * moveOver1Seen0 x R
check1 1 moveOver1Seen1 X R
check1 $ moveOver1Seen1 x R
check1 # check2Seen0 # R
check1 X check1 X R
check1 x check1 x R

validateTape1 0 validateTape1 0 R
validateTape1 * validateTape1 * R
validateTape1 $ validateTape1 $ R
validateTape1 1 validateTape1 1 R
validateTape1 # validateTape2 # R

validateTape2 0 validateTape2 0 R
validateTape2 1 validateTape2 1 R
validateTape2 # validateTape3 # R

validateTape3 # reject # R
validateTape3 0 validateTape3 0 R
validateTape3 1 validateTape3 1 R
validateTape3 _ tapeValidated _ L

tapeValidated # tapeValidated # L
tapeValidated 0 tapeValidated 0 L
tapeValidated 1 tapeValidated 1 L
tapeValidated $ check1 $ L
tapeValidated * check1 * L
tapeValidated . check2Seen0 . R

moveOver1Seen0 0 moveOver1Seen0 0 R
moveOver1Seen0 1 moveOver1Seen0 1 R
moveOver1Seen0 # check2Seen0 # R
moveOver1Seen0 X moveOver1Seen0 X R

moveOver1Seen1 0 moveOver1Seen1 0 R
moveOver1Seen1 1 moveOver1Seen1 1 R
moveOver1Seen1 # check2Seen1 # R
moveOver1Seen1 X moveOver1Seen1 X R

check2Seen0 X check2Seen0 X R
check2Seen0 0 goEndCount0 X R
check2Seen0 1 goEndCount1 X R
check2Seen0 # goEndCount0 # R

check2Seen0Empty X check2Seen0Empty X R
check2Seen0Empty 0 goEndCount0 X R
check2Seen0Empty 1 goEndCount1 X R
check2Seen0Empty # goToEnd # R

check2Seen1 X check2Seen1 X R
check2Seen1 0 goEndCount1 X R
check2Seen1 1 goEndCount2 X R
check2Seen1 # goEndCount1 # R

goEndCount0 X goEndCount0 X R
goEndCount0 1 goEndCount0 1 R
goEndCount0 0 goEndCount0 0 R
goEndCount0 # goEnd1Count0 # R
goEndCount0 _ atEndWrite0 # R

goEndCount1 X goEndCount1 X R
goEndCount1 1 goEndCount1 1 R
goEndCount1 0 goEndCount1 0 R
goEndCount1 # goEnd1Count1 # R
goEndCount1 _ atEndWrite1 # R

goEndCount2 X goEndCount2 X R
goEndCount2 1 goEndCount2 1 R
goEndCount2 0 goEndCount2 0 R
goEndCount2 # goEnd1Count2 # R
goEndCount2 _ atEndWrite0 # R

goEnd1Count0 X goEnd1Count0 X R
goEnd1Count0 1 goEnd1Count0 1 R
goEnd1Count0 2 goEnd1Count0 2 R
goEnd1Count0 0 goEnd1Count0 0 R
goEnd1Count0 # atEndWrite0 # R
goEnd1Count0 _ atEndWrite0 # R

goEnd1Count1 X goEnd1Count1 X R
goEnd1Count1 1 goEnd1Count1 1 R
goEnd1Count1 2 goEnd1Count1 2 R
goEnd1Count1 0 goEnd1Count1 0 R
goEnd1Count1 # atEndWrite1 # R
goEnd1Count1 _ atEndWrite1 # R

goEnd1Count2 X goEnd1Count2 X R
goEnd1Count2 1 goEnd1Count2 1 R
goEnd1Count2 2 goEnd1Count2 2 R
goEnd1Count2 0 goEnd1Count2 0 R
goEnd1Count2 # atEndWrite2 # R
goEnd1Count2 _ atEndWrite2 # R

atEndWrite0 0 atEndWrite0 0 R
atEndWrite0 1 atEndWrite0 1 R
atEndWrite0 2 atEndWrite0 2 R
atEndWrite0 _ goBackToStart1 0 L

atEndWrite1 0 atEndWrite1 0 R
atEndWrite1 1 atEndWrite1 1 R
atEndWrite1 2 atEndWrite1 2 R
atEndWrite1 _ goBackToStart1 1 L

atEndWrite2 0 atEndWrite2 0 R
atEndWrite2 1 atEndWrite2 1 R
atEndWrite2 2 atEndWrite2 2 R
atEndWrite2 _ goBackToStart1 2 L

goBackToStart1 # goBackToStart1 # L
goBackToStart1 0 goBackToStart1 0 L
goBackToStart1 1 goBackToStart1 1 L
goBackToStart1 2 goBackToStart1 2 L
goBackToStart1 X goBackToStart1 X L
goBackToStart1 $ seenX $ R
goBackToStart1 * seenX * R
goBackToStart1 x seenX x R
goBackToStart1 . check2Seen0Empty . R

seenX # goToEnd # R
seenX X seenX X R
seenX 0 check1 0 L
seenX 1 check1 1 L
seenX * check1 * L
seenX $ check1 $ L

goToEnd X goToEnd X R
goToEnd # goToEnd1 # R
goToEnd 0 goEndCount0 X R
goToEnd 1 goEndCount1 X R

goToEnd1 0 moveToReWriteEnd 0 R
goToEnd1 1 moveToReWriteEnd 1 R
goToEnd1 # reWriteEnd # R

moveToReWriteEnd 0 moveToReWriteEnd 0 R
moveToReWriteEnd 1 moveToReWriteEnd 1 R
moveToReWriteEnd # reWriteEnd # R
moveToReWriteEnd _ moveToCheckEq _ L

reWriteEnd 0 reWriteEnd 0 R
reWriteEnd 1 reWriteEnd 1 R
reWriteEnd 2 reWriteEndCarry 0 R
reWriteEnd # removeDelim # R
reWriteEnd _ moveToCheckEq _ L

removeDelim 0 removeDelim0 # L
removeDelim 1 removeDelim1 # L
removeDelim _ removeDelimBlank _ L

removeDelim0 # reWriteEnd 0 R
removeDelim1 # reWriteEnd 1 R
removeDelimBlank # reWriteEnd _ L

reWriteEndCarry 0 reWriteEnd 1 R
reWriteEndCarry 1 reWriteEndCarry 0 R
reWriteEndCarry 2 reWriteEndCarry 1 R
reWriteEndCarry # reWriteEndCarry # R
reWriteEndCarry _ reWriteEnd 1 R

moveToCheckEq 0 moveToCheckEq 0 L
moveToCheckEq 1 moveToCheckEq 1 L
moveToCheckEq # moveToCheckEq1 # L
moveToCheckEq X moveToCheckEq X L

moveToCheckEq1 0 moveToCheckEq1 0 L
moveToCheckEq1 1 moveToCheckEq1 1 L
moveToCheckEq1 # checkBit # R
moveToCheckEq1 X checkBit X R

checkBit 1 moveToCheckResult1 X R
checkBit 0 moveToCheckResult0 X R
checkBit # checkResult0 # R

moveToCheckResult1 0 moveToCheckResult1 0 R
moveToCheckResult1 1 moveToCheckResult1 1 R
moveToCheckResult1 # checkResult1 # R

moveToCheckResult0 0 moveToCheckResult0 0 R
moveToCheckResult0 1 moveToCheckResult0 1 R
moveToCheckResult0 # checkResult0 # R

checkResult0 0 moveToCheckEq X L
checkResult0 1 reject X R
checkResult0 # checkResult0 # R
checkResult0 X checkResult0 X R
checkResult0 _ accept _ R

checkResult1 0 reject 0 R
checkResult1 X checkResult1 X R
checkResult1 1 moveToCheckEq X L
checkResult1 # checkResult1 # R
