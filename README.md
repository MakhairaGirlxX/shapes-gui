# shapes-gui

Description:
A GUI that includes a button to change the shape to a circle, rectangle or triangle. After each button is clicked the user can draw the shape on the frame using the mouse. If the user presses the undo button, one shape will be removed until there are no shapes left. All of the shapes are visible even when the frame is resized or restored from an icon.

On the bottom of the frame are 4 textboxes. The first one sets the size. If the user inputs
the size, the dimensions of every shape drawn thereafter is multiplied by that input. This allows
for the user to control how large they want to paint their shapes.

The second, third and fourth textboxes control the colors in an RGB fashion. The user
can input 3 color values which will change the shapes colors so that every shape drawn will have
that color. I used a try/catch block to make sure the values are set to zero if an exception occurs
and that the user is inputting the correct range of values (between 0 and 255).

The last textbox returns the location of the currently drawn shape. This is recorded as an
x and y axis obtained from the MouseEvent e or e.getX() and e.getY().

Screenshots:


