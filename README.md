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


</header>

<!--
  <<< Author notes: Step 1 >>>
  Choose 3-5 steps for your course.
  The first step is always the hardest, so pick something easy!
  Link to docs.github.com for further explanations.
  Encourage users to open new tabs for steps!
  TBD-step-1-notes.
-->

## Implementation:
<!--
_Welcome to "TBD-course-name"! :wave:_

TBD-step-1-information

**What is _TBD-term-1_**: TBD-definition-1

### :keyboard: Activity: TBD-step-1-name
-->
Program will allow only for legal moves to be made. It will highlight checks and detect the end of the game. It will take into account all the special moves that are in the official rules, such as castling, en passant or promotion.

Possible legal moves of a piece will be displayed by the program after clicking on that particular piece. After making a move, the squares where a given piece previously stood and where it stands now will be marked in color so that the other player knows what move was made.

The implementation will be based on the Game class, responsible for handling game logic and visualizing gameplay.

In addition, there will be a Board class responsible for drawing the board and storing 64 squares.
The Square class will contain information about coordinates of a given square and what piece is standing on it.

The abstract class Piece, will define the characteristics that each chess piece should contain, including:
- position on the chessboard
- a method that determines how to draw a given piece
- a method that determines all the possible moves of the piece given the current position on the chessboard

The six classes that will inherit from the Piece class are: King, Queen, Rook, Knight, Bishop and Pawn.

<footer>

<!--
  <<< Author notes: Footer >>>
  Add a link to get support, GitHub status page, code of conduct, license link.
-->

---

Get help: [TBD-support](TBD-support-link) &bull; [Review the GitHub status page](https://www.githubstatus.com/)

&copy; 2023 TBD-copyright-holder &bull; [Code of Conduct](https://www.contributor-covenant.org/version/2/1/code_of_conduct/code_of_conduct.md) &bull; [MIT License](https://gh.io/mit)

</footer>
