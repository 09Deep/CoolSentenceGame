## Architecture

This project uses a 3-tier architecture. Presentation, Logic, and Data.

Presentation is covered primarily by *MainActivity* and *GameSetup*.

Logic is handled largely by *Game*.

Data is implemented in the *IDatabase* file. With the implementation being in *MockDatabase*.

Currently, everything is under the generic package of *com.coolsentencegame*. As the project expands this will most likely change to better separate the layers.


The architecture diagram is a separate image called "ARCHITECTURE.png"