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
https://user-images.githubusercontent.com/83840115/117697679-571b2f00-b188-11eb-9139-c5ca40c21889.png

https://user-images.githubusercontent.com/83840115/117697680-571b2f00-b188-11eb-9706-5fa3fb3daf62.png

https://user-images.githubusercontent.com/83840115/117697681-57b3c580-b188-11eb-9fde-9d28db2b8d57.png

https://user-images.githubusercontent.com/83840115/117697682-584c5c00-b188-11eb-9074-da8b2f934454.png

https://user-images.githubusercontent.com/83840115/117697684-584c5c00-b188-11eb-91b2-560c90dfd74b.png

https://user-images.githubusercontent.com/83840115/117697685-58e4f280-b188-11eb-9df8-acab61d33223.png

