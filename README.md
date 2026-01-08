# 2048 Game in Java

This repository contains my **Java-based implementation of the classic 2048 game**, developed as part of my coursework and personal practice during **Semester 2**.

The project recreates the full 2048 gameplay experience using **Java, Gradle, and the Processing library**, with a strong focus on **object-oriented design, clean game logic, and interactive graphics**.

---

## 📘 Project Overview

At a high level, this project implements the popular **2048 puzzle game**, where players slide numbered tiles across a grid to combine matching values and reach the **2048 tile**.

The game handles all core mechanics internally, including tile movement, merging rules, random tile generation, and game-over detection. Rendering and user input are managed through the **Processing framework**, allowing for smooth visuals and responsive controls.

This project demonstrates how **game logic, state management, and rendering** come together in a complete desktop application.

---

## 🌍 Project Motivation & Challenge

While 2048 appears simple on the surface, implementing it correctly involves careful handling of edge cases and rules.

The key challenges addressed in this project include:

* Ensuring tiles **merge correctly** (only once per move)
* Handling movement in all four directions consistently
* Detecting when **no further moves are possible**
* Managing game state independently of rendering
* Structuring the project using clean **object-oriented principles**

The project was an opportunity to strengthen foundational Java skills while building something interactive and visually engaging.

---

## 🧠 Game Logic & Design

The game is structured around a clear separation of responsibilities:

* The **Board** manages the grid, movement logic, merges, and game-over checks
* Each **Tile** represents a single numbered tile, including its value and visual appearance
* A **Direction** enum defines movement intent and keeps logic readable
* The main **App** class handles rendering, keyboard input, and game flow

On each move:

1. Tiles slide as far as possible in the chosen direction
2. Matching tiles merge according to 2048 rules
3. A new tile spawns in an empty cell
4. The game checks for win or game-over conditions

This modular design keeps the logic easy to reason about and extend.

---

## 🎮 Gameplay & Controls

* **Arrow Keys** → Move tiles (Up, Down, Left, Right)
* **Mouse Click** → Start the game (initial tile spawn)
* **R key** → Restart the game after a game-over

The game provides immediate visual feedback after every move and prevents invalid or redundant actions.

---

## 🧪 Implementation Details

Key implementation highlights include:

* Strict enforcement of **one merge per tile per move**
* Random tile generation in valid empty cells only
* Direction-agnostic movement logic (shared across all directions)
* Real-time rendering using Processing’s drawing loop
* Keyboard and mouse input handling through Processing events

The logic is entirely deterministic and independent of rendering, which mirrors best practices in game development.

---

## 📂 Repository Structure

```text
src/main/java/shapes/game/
├── App.java        # Main Processing application (rendering + input)
├── Board.java      # Game state, movement logic, merges, game-over detection
├── Tile.java       # Tile values and visual styling
└── Direction.java  # Enum defining movement directions
```

Gradle is used for dependency management and execution.

---

## 🧠 Key Learnings

* Applying object-oriented design to interactive applications
* Managing game state and logic cleanly in Java
* Handling edge cases in movement and merge rules
* Integrating third-party libraries (Processing) into Java projects
* Structuring larger programs for readability and maintainability

---

## 🛠 Tools & Technologies

* **Java**
* **Gradle**
* **Processing (core library)**
* **Object-Oriented Programming**
* **Game Logic & State Management**

---

## 📝 Significance

This project represents a step beyond basic programming exercises into **complete application development**.

It strengthened my confidence in:

* Designing systems with multiple interacting classes
* Translating game rules into robust logic
* Debugging non-trivial edge cases
* Building interactive desktop applications in Java

The project also reinforced the importance of clean structure and separation of concerns in software design.

---

## 🚀 Running the Project

From the project root directory:

### macOS / Linux

```bash
./gradlew run
```

### Windows

```bat
gradlew.bat run
```

The main entry point is `shapes.game.App`.

---

## 🎓 Course Information

* **University:** The University of Sydney
* **Unit:** INFO1113 – Object Oriented Programming
* **Assessment Type:** Multi-Part Programming Assignment
* **Semester:** Semester 2

