# Elevens Card Game

Elevens is a solitaire card game that uses a standard 52-card deck. The goal is to remove pairs of cards that add to eleven, or trios of any Jack, Queen, and King. Also Sanrio themed!

## How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Git (for cloning the repository)

### Quick Start

1. **Clone the repository:**
   ```bash
   git clone <your-repository-url>
   cd Elevens-1
   ```

2. **Compile the Java files:**
   ```bash
   javac *.java
   ```

3. **Run the game:**
   ```bash
   java Main
   ```

## How to Play

1. **Objective:** Remove all cards from the board by forming valid combinations
2. **Valid Combinations:**
   - **Pairs:** Two cards that add up to 11 (e.g., 5 + 6, 4 + 7, 3 + 8, 2 + 9, A + 10)
   - **Trios:** Any Jack, Queen, and King combination
3. **Gameplay:**
   - Click on cards to select them (they will be highlighted)
   - Click "Replace" to remove selected valid combinations
   - Click "Restart" to start a new game
   - Win by removing all cards from the board
