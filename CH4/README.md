# Pass By Copy: Object? or Reference?  
In books, I learned **Pass By Copy** concern in Java function. For primitive variables, it is easy to understand, just copy it, but I am curious about how java pass objects. This is an important thing. In python, if you pass a dictionary into some function and do something with this dictionary, no matter this dictionary return out of the function or not, you directly change the dictionary outside the function by doing inside the function. The dictionary variables in python store just the linking address to dictionary objects, so you pass dictionary variables into some function, and this function copy their addresses as local variables. When you do something with this local variables, actually you directly operate with their linking objects.
```{python}
def func (dictionary):
	dictionary['c'] = 3 # dict be changed

dict = {'a':1,'b':2 }
func(dict)
```  
  
In Java, this principle is still work. I use S04_PassByCopy.java to test this principle and found it copies with linking addresses of object when objects input into function. So be careful to input objects into function, it might change your object outside of function.

## Run this code
```{bash}
> javac S04_PassByCode.java
> java S04_PassByCode
Before Function: 1
After Function: 2
Copy Reference in Object
```