# A solution in Clojure

I made this solution to show that some languages make working with this functional style really easy. One popular example is clojure.

A functional Mars Rover Kata
============================

This is one of the katas we have done in Software Craftsmanship Madrid group. Feel free to fork this repository and commit your solution there.

We know probably you already made this a thousand times so let's have some fun today:

We will add one more constraint: changes in state are strictly forbidden. This means you have to avoid changes in state and data such as:

        rover.x_position = rover.x_position + 1

Yes, this means you can't alter an object after it is created, you can't change a list after it is created...

You can use assignments as long as they are used just as a mere aliases.

Just in case you don't remember/know the rules for Mars Rover Kata.

 - Develop an API that moves a rover around on a grid.
 - You are given the initial starting point (x,y) of a rover and the direction (N,S,E,W) it is facing.
 - The rover receives a character array of commands.
 - Implement commands that move the rover forward/backward (f,b).
 - Implement commands that turn the rover left/right (l,r).
 - Implement wrapping from one edge of the grid to another. (planets are spheres after all)
 - Implement obstacle detection before each move to a new square. If a given sequence of commands encounters an obstacle, the rover moves up to the last possible point and reports the obstacle.

Examples:

 - The rover is on a 100x100 grid at location (0, 0) and facing NORTH. The rover is given the commands "ffrff" and should end up at (2, 2)
