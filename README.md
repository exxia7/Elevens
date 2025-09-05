# Elevens Card Game - Sanrio Edition

A Java-based implementation of the classic Elevens solitaire card game featuring beautiful Sanrio-themed card graphics. The game combines traditional card game mechanics with adorable Sanrio character designs.

## 🎮 Game Overview

Elevens is a solitaire card game that uses a standard 52-card deck. The goal is to remove pairs of cards that add to eleven, or trios of any Jack, Queen, and King. The game features:

- **Sanrio-themed card graphics** with Hello Kitty, My Melody, Kuromi, and Badtz-Maru
- **Modern, clean UI** with custom styling and responsive design
- **Real-time game statistics** tracking wins and losses
- **Intuitive card selection** with visual feedback

## 🚀 How to Run

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

### Alternative: Using the JAR file
```bash
java -jar Elevens.jar
```
*Note: The JAR file may not include the latest UI improvements. Use `java Main` for the most up-to-date version.*

## 🎯 How to Play

1. **Objective:** Remove all cards from the board by forming valid combinations
2. **Valid Combinations:**
   - **Pairs:** Two cards that add up to 11 (e.g., 5 + 6, 4 + 7, 3 + 8, 2 + 9, A + 10)
   - **Trios:** Any Jack, Queen, and King combination
3. **Gameplay:**
   - Click on cards to select them (they will be highlighted)
   - Click "Replace" to remove selected valid combinations
   - Click "Restart" to start a new game
   - Win by removing all cards from the board

## 🛠️ Technical Details

### Architecture
- **Object-Oriented Design:** Clean separation of concerns with abstract classes and inheritance
- **GUI Framework:** Java Swing with custom styling and event handling
- **Card System:** Enum-based rank and suit system with point value calculations
- **Game Logic:** Modular board management with extensible design

### Key Classes
- `Main.java` - Entry point and game initialization
- `CardGameGUI.java` - User interface and event handling
- `Board.java` - Abstract base class for game board logic
- `ElevensBoard.java` - Concrete implementation of Elevens game rules
- `Card.java` - Card representation with rank and suit
- `Deck.java` - Deck management with shuffling algorithms
- `Rank.java` & `Suit.java` - Enum definitions for card properties

### Features Implemented
- ✅ **Sanrio-themed card graphics** with custom image loading
- ✅ **Modern UI styling** with custom colors and fonts
- ✅ **Responsive layout** that adapts to different screen sizes
- ✅ **Game statistics** tracking wins and losses
- ✅ **Input validation** for legal card combinations
- ✅ **Error handling** for invalid moves
- ✅ **Cross-platform compatibility** (Windows, macOS, Linux)

## 🎨 UI Improvements

The project includes several modern UI enhancements:
- **Custom button styling** with Sanrio-themed colors (pink for hearts/diamonds, dark for spades/clubs)
- **Clean typography** using Segoe UI font family
- **Optimized text positioning** to prevent cutoff and improve readability
- **Visual feedback** for card selection and game states
- **Professional layout** with proper spacing and alignment

## 📁 Project Structure

```
Elevens-1/
├── Main.java                 # Entry point
├── CardGameGUI.java          # GUI implementation
├── Board.java               # Abstract board class
├── ElevensBoard.java        # Game logic implementation
├── Card.java                # Card representation
├── Deck.java                # Deck management
├── Rank.java                # Card rank enum
├── Suit.java                # Card suit enum
├── Elevens.jar              # Executable JAR file
├── sanriocards/             # Card image assets
│   ├── *.gif                # Card images (mixed case extensions)
│   └── *.GIF                # Card images (uppercase extensions)
└── README.md                # This file
```

## 🐛 Troubleshooting

### Common Issues

1. **"Card image not found" error:**
   - Ensure the `sanriocards/` folder is in the same directory as the compiled classes
   - The code handles both `.gif` and `.GIF` file extensions automatically

2. **Game won't start:**
   - Make sure you've compiled all Java files with `javac *.java`
   - Check that you have Java 8 or higher installed

3. **UI looks different than expected:**
   - Use `java Main` instead of `java -jar Elevens.jar` for the latest UI improvements
   - Ensure you're running the latest compiled version

## 🎯 Resume Highlights

This project demonstrates:
- **Java Programming:** Object-oriented design, GUI development, file I/O
- **Software Engineering:** Clean code architecture, modular design, error handling
- **UI/UX Design:** Custom styling, responsive layout, user experience optimization
- **Problem Solving:** Game logic implementation, image loading, cross-platform compatibility
- **Version Control:** Git workflow, project documentation, code organization

## 📝 License

This project is open source and available under the MIT License.

## 🤝 Contributing

Feel free to fork this project and submit pull requests for improvements!

---

*Enjoy playing Elevens with your favorite Sanrio characters! 🎀*