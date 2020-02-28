# rects
A program to compute the intersections of rectangles.

## Building

This project uses [gradle](https://gradle.org/) for building.
You can create an executable jar file by running ```gradle buildJar```.
The jar file will appear in 'rects/build/libs'.

## Running

You can run the program using ```java -jar rects.jar <input-file>``` where ```<input-file>``` is the path to a JSON file containing rectangle definitions.
The JSON should look like this:
```
{
  "rects": [
    { "x": 100, "y": 100, "w": 250, "h": 80 },
    { "x": 120, "y": 200, "w": 250, "h": 150 },
    { "x": 140, "y": 160, "w": 250, "h": 100 },
    ...
  ]
}
```
The program will then output all the rectangles and their intersections.
