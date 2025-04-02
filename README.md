# Gallows

This application simulate game in gallows.

![img.png](resources/img.png)

#### Rules:
- You can start the game or close the application;
    - Your must enter: "Yes"/"No";
- You get a word with the number of letters between 6 and 24;
- You must write one Russian letter;
- Game will be over if:
    - Your number of possible mistakes is equal 6;
    - You open all letters of the word;
- After the end of the game session, you may choose to start the next game or close the application

#### Instruction to compile
```bash
javac -d classes src/*
java -cp classes/:resources/ Main
```
