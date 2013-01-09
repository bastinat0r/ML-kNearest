#k Nearest Neighbor

Solution for a Machine Learning exercise.

## Algorithm
The Algorithm itself and some utility function like testing and printing the confusion matrix are located in KNearest.java

The distance function is implemented as an Interface so you can use diffrent distance measures.
We use a very simple distance: The number of dissimilar features.

## Usage
Just compile the files and run KNearest.java. You will be promted to select a file, if you like to work with the car dataset just use 'car.data'.

The Mahalanobis distance is not fully implemented yet, you need JAMA http://math.nist.gov/javanumerics/jama/ to use it (and you propably can't compile the file without this libary)

Have fun classifying :)
