# MunheEx3

# packman   (task_3)
This project is to make a map and let the packman to go and it all his fruits in the shorts time 


# Getting Started

There is a Test Junit class that can run test program, of course it can be adjusted if needed.
You can use your own map but you can use the arial map
You will also want to download to your own computer google earth.

# Running The Program

This packman program will start running after running the main class in the map package, there is an example on how to use it.
You will need to provide a map and 2 of its corner GPS locations, and then you can add your own packmans or fruits and even open an already built game from a csv file(we have example of those in the project), after positioning everything you can run it and see the paths and visualize the packmans move to all fruits, or you can send it to a KML file and watch it in Google Earth.
In order to help, we built the option of custom data such as speed or range of packman, we added a weight value to fruit for future adjustments, you can also have a custom accuracy level that if you raise it above 1 it will take longer to calculate but have a statistical higher rate. And obove all its importent you enjoy this.


# Built With

•eclipse - The Platform for Open Innovation and Collaboration

# Authors

•	Shilo Gilor - Initial work 
•	Amiel Liberman - Initial work 

# Acknowledgments

•	We used youtube to understand better what is a Jframe funcion.

• We used http://zetcode.com/tutorials/javaswingtutorial/menusandtoolbars/ to help us in JMenuBar .

• We used		https://stackoverflow.com/questions/13366793/how-do-you-make-menu-item-jmenuitem-shortcut to make   generic shurtcut on all platforms

• We  used https://developers.google.com/kml/documentation/kml_tut to make the KML file as neded.

• we uesd https://www.youtube.com/watch?v=BAejnwN4Ccw&list=PLRqwX-V7Uu6ZncE7FtTEn53sK-BjR2aLf to build the  TSP algorithm

# Description 
This program gets or builds a map with packmans and fruits and running the program will calculate a route for the packmans to go to eat all the fruits, we could add saving and loading fully ready maps, and the paths can also be viewed in a kml file(google earth).

We have 8 packages to make the packman game,we used Point3D to crate the GPS places of the game.

i will explain the main packages:

algorithms is the package where all calculations are done, hence many other classes use this package.

entities is a package where all the entities are defined(packman,fruit,map,path,game).

GUI is a package where all the graphics are done.

#funcions in the map toolbar

file:

• new = create new map

• run = calculate the path and move the packmans

• clean = clear the map

• Exit = exit the app 

game:

• fruit = click on the map will put a fruit from now in the click location

• packman = click on the map will put a packman from now in the click location

• custom fruit weight = from now the weight of fruit will be the number you put in

• custom fruit height = from now the height of fruit will be the number you put in

• custom packman speed = from now the speed of packman will be the number you put in

• custom packman range = from now the range of packman will be the number you put in

• custom packman height = from now the height of packman will be the number you put in

* in all these if you put an ilagical value it will go back to the default.

speed:

• slow down = packman will run slower by half

• fast forwards = packman will run faster (times 2)

import/export:

• open = open CSV file

• save = save the game as a CSV file

• make kml = save a game as KML file

accuracy:

• accuracy_level = default as 1, and the highter you go the better statistic result you would get but with more iterations so could take longer for a result.(recomended good result is 10)
