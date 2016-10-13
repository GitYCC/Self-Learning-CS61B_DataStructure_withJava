# SimpleIO and WWWReader  
Reading text from keyboard in Java is not an easy thing. It must through a lot of object to get it. Why not just use simple function to get it? It invoke flexible about methods.  

Reading from keyboard, you must use three kind of objects:  
1. InputStream Object: read raw data from some source, but don't format the data  
```{java}
System.in
```
2. InputStreamReader Object: compose the raw data into charactors  
```{java}
new InputStreamReader(System.in)
```
3. BufferedReader Object: compoase the characters into entire lines of text  
```{java}
BufferedReader keybd = new BufferedReader(new InputStreamReader(System.in));
```
  
Why are the tasks divided among three different classes? So that any one task can be reimplemented without changing the other two.  

For example, I change input source from keyboard to web site. I just need to reimplement InputStream Object to fit this case.
```{java}
URL u = new URL("https://www.google.com.tw/");
InputStream ins = u.openStream();
```
Other two do not need to be changed.