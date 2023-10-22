<header>

<!--
  <<< Author notes: Course header >>>
  Read <https://skills.github.com/quickstart> for more information about how to build courses using this template.
  Include a 1280×640 image, course name in sentence case, and a concise description in emphasis.
  In your repository settings: enable template repository, add your 1280×640 social image, auto delete head branches.
  Next to "About", add description & tags; disable releases, packages, & environments.
  Add your open source license, GitHub uses the MIT license.
-->

# Aquarium Project

Java Based application to demonstrate how to use OOP principles, for example interface, inheritance.

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
For example, class <b>InsideContainer</b> is represent the Fish Container for the Aquarium. At its constructor we initialize the baseBackground for the water image, and initialize fishCollection variable to hold the fishes. <br />
InsideContainer, the paint method. In the paint method we override:
- Create a buffer image for Double Buffering (Drawing Image in memory first)
- Draw the background SeaScape
- Loop all fishCollection and draw the fish current image ( each fish will have two type of image, left or right).<br />
<p>For example, <b>Fish Abstract Class</b>. Fish is an abstract class, this class cannot be instantiate. We will need to inherits from Fish and make a new class, for example RedFish.
<p>The <b>RedFish class</b>, use static initializer to load images, for example, fish-red.png and fish-red-right.png You will see that RedFish will be forced to override two abstract methods from Fish class:<br />
- getLeftImage()<br />
- getRightImage()<br />
<p>But many methods its already inherited from Fish class:<br />
- moveUp()<br />
- moveDown()<br />
- moveLeft()<br />
- moveRight()<br />
- getCurrentImage()<br />
- run() - this is for Runnable thread
<p>Some of them is either protected or private.
<b>private method</b> in Fish class means the method is only visible inside the Fish class, and cannot be invoke from outside Fish class, for examples:
- randomStartPosition<br />
- randomMovementArea<br />
- randomHorizontalDirection<br />
- randomVerticalDirection<br />
- randomMinLeft<br />
- randomMaxRight<br />
- randomMinTop<br />
- randomMaxBottom<br />
<b>protected method</b> in Fish class means the method is only visible inside the Fish class and it subclasses, so RedFish as a subclass still can invoke it directly:<br />
- setCurrentImage(Image currentImage)<br /> By providing private modifiers, we hide the complexity of the Fish class, so when we create a new class, extending from Fish, we can only see the public/protected and can override the required method.
<p><b>Using FishFactory Class</b>
FishFactory is a class that can be used to instantiate various kinds of Fish, we are recommending to create a fish object using FishFactory.
The FishFactory will read the index file and load it to program.
<p><b>Easily adding new Fish</b><br />
- Create a new class for example YellowFish.java in package.<br />
- Implements the required method, make sure you use image, for example fish-yellow.png and fish-yellow-right.png<br />
- Put those images in src/main/resources.<br />
- Add some line the index file.<br />

## Example of UI:
<p align="center"><img align="center" src="https://github.com/akakiev/long-term-project-aquarium-java/blob/main/aqua1.jpg" height="300" width="400" /></p>
<p align="center"><img align="center" src="https://github.com/akakiev/long-term-project-aquarium-java/blob/main/aqua2.png" height="300" width="400" /></p>

<footer>

<!--
  <<< Author notes: Footer >>>
  Add a link to get support, GitHub status page, code of conduct, license link.
-->

---

Get help: [TBD-support](TBD-support-link) &bull; [Review the GitHub status page](https://www.githubstatus.com/)

&copy; 2023 TBD-copyright-holder &bull; [Code of Conduct](https://www.contributor-covenant.org/version/2/1/code_of_conduct/code_of_conduct.md) &bull; [MIT License](https://gh.io/mit)

</footer>
