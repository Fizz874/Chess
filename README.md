[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/7p6FG8zC)
[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-718a45dd9cf7e7f842a935f5ebbe5719a5e09af4491e668f4dbf3b35d5cca122.svg)](https://classroom.github.com/online_ide?assignment_repo_id=12513280&assignment_repo_type=AssignmentRepo)
<header>

<!--
  <<< Author notes: Course header >>>
  Read <https://skills.github.com/quickstart> for more information about how to build courses using this template.
  Include a 1280×640 image, course name in sentence case, and a concise description in emphasis.
  In your repository settings: enable template repository, add your 1280×640 social image, auto delete head branches.
  Next to "About", add description & tags; disable releases, packages, & environments.
  Add your open source license, GitHub uses the MIT license.
-->

# Chess project

Program is intended to serve as a chessboard.
Created by: Filip Baranowski, 155828

</header>

<!--
  <<< Author notes: Step 1 >>>
  Choose 3-5 steps for your course.
  The first step is always the hardest, so pick something easy!
  Link to docs.github.com for further explanations.
  Encourage users to open new tabs for steps!
  TBD-step-1-notes.
-->

The program displays a chessboard with pieces set out on it. When you click on a given piece, all the squares to which it can be moved are highlighted. Program allows only for legal moves to be made.
It highlights checks and detects the end of the game (either mate or draw). It takes into the account all the special moves that are in the official rules, that is castling, enpassant and promotion.


## Implementation:
<!--
_Welcome to "TBD-course-name"! :wave:_

TBD-step-1-information

**What is _TBD-term-1_**: TBD-definition-1

### :keyboard: Activity: TBD-step-1-name
-->
### The most important classes:

**Game**: This class is responsible for the game logic. It creates a chessboard and sets out all the pieces. It manages turn changes, and listens for mouse clicks.
It has methods for manipulating the state of the board and the pieces, which for example, mark appropriate squares as highlighted or change the coordinates of the pieces.
It also checks for the end of the game after each move and displays a pop-up message if necessary.

**Board**: This class stores all 64 squares and is responsible for painting the chessboard with highlighted squares and placed pieces.

**Square**: This class holds the state of a given square. It contains the information about its coordinates, the way it shoud be displayed and which piece, if any, is placed on it.

**Frame**: Creates the game and a menu with an option to restart it.

**Piece**: An abstract class that contains all types of attributes and methods that are universal to each of its subclasses, such as the coordinates of a give piece, and also two abstract methods: *paint()* and *possibleMove()*, that are appropriately overridden by each of those classes.

In total, there are six derived classes: **Pawn**, **Knight**, **Bishop**, **Rook**, **Queen** and **King**. Each is responsible for determining the possible moves in a given situation and displaying the piece.

### Used Programming paradigms:

**Polymorthism** – seen in different implementations of methods *paint()* and *possibleMove()* in each of the classes derived from the class Piece.

**Inheritance** – for example, Piece class having a Pawn subclass  that inherits some of the logic.

**Encapsulation** – hiding the internal structure of a class, for example through the *setPlace()* method of the Piece class, which not only sets the value of the place attribute but also updates the coordinates of the piece accordingly.

**Data abstraction** – for example, an abstract class Piece which defines common characteristics of all pieces but without specifying the details of each one of them.

In the project, I used three types of access modifiers: *public*, *private* and *protected*. Access to private attributes, which were needed outside of their own object, was done through *getters* and *setters*.

<footer>

<!--
  <<< Author notes: Footer >>>
  Add a link to get support, GitHub status page, code of conduct, license link.
-->

---

Get help: [TBD-support](TBD-support-link) &bull; [Review the GitHub status page](https://www.githubstatus.com/)

&copy; 2023 TBD-copyright-holder &bull; [Code of Conduct](https://www.contributor-covenant.org/version/2/1/code_of_conduct/code_of_conduct.md) &bull; [MIT License](https://gh.io/mit)

</footer>
