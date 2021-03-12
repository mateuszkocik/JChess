# JChess
JChess is a terminal application for Linux users to play single machine chess game.

## Game

To play JChess all you have to do is to specify moves in command line. 
Application verifies and shows error if move is invalid. 
Game status is shown above chessboard.

<img src="/images/chessboards.png">


## User Interface
JChess has colorful user interface. All color setting are saved uder root filesystem in file .JChess-color-settings.
You can change these settings in specific menu after launching application or edit file directly.
To have best experience in using that application please extend your terminal before running it and keep it in the same size.

Color settings menu

<img src="/images/color_settings_menu.png">

## Setup

```
git clone https://github.com/mateuszkocik/JChess.git
cd JChess
mvn clean install
cd target
java -jar Jchess-1.0-SNAPSHOT.jar

```

## Technologies
- Java 11
- JUnit
- Maven
