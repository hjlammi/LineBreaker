# Laki1 - LineBreaker

This is the first bigger program I've ever written. It was an assignment at a Java programming course in the fall of 2016. 
The program asks for a line width and a piece of text and wraps the text into the lines according to the line width.

## Examples
```
Hello! I break lines.
Enter area width:
6
Enter a line:
The quick, brown fox jumps over a lazy dog.
The   /
quick,/
brown /
fox   /
jumps /
over a/
lazy  /
dog.  /
Continue (y/n)?
n
See you soon.
```

The program notifies of an error if the provided text includes a word that is too long to fit on the line.

```
Hello! I break lines.
Enter area width:
4
Enter a line:
His many legs, pitifully thin compared with the size of the rest of him, waved about helplessly as he looked.
Error!
Enter a line:
the size of the rest of him,
the /
size/
of  /
the /
rest/
of  /
him,/
Continue (y/n)?
n
See you soon.
```

## Running

```
javac LineBreaker.java
java LineBreaker
```

## License

This project is licensed under the MIT License - see the [LICENSE.txt](LICENSE.txt) file for details

## Acknowledgments

In.java was provided by our teacher Jorma Laurikkala. It is a helper class that uses Scanner to read input from the user.
