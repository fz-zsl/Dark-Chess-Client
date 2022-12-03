# Readme File for Database

## User Profile

- the suffix must be .user.txt
- the first line contains the password encrypted by MD5
- there follows four lines, including two non-negative integers each

> the two numbers in the first line mean the number of games that user wins|loses in level 0
> the same for the next three lines

## Game Profile

- the suffix must be .game.txt
- the first line contains the key of the game
- next line: P
- next 8 lines describes the board by object ID
- next line: O
- next line: n (number of operations)
- next n line(s): description of the operations
- next line: N
- next line: R or B, means the side which will perform the next step
- next line: A(unnecessary)
- next line: a number to describe the level of AI mode

**Note that only games in AI mode can be continued, any game of other modes will be continued in AI mode of level 2.**