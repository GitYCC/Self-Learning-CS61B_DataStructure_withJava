# Result  
  
```{bash}
> javac Date.java
> java Date

Testing constructors.
Date should be 1/1/1: 1/1/1
Date should be 2/4/2: 2/4/2
Date should be 2/29/2000: 2/29/2000
Date should be 2/29/1904: 2/29/1904
Date should be 12/31/1975: 12/31/1975
Date should be 1/1/1976: 1/1/1976
Date should be 1/2/1976: 1/2/1976

Testing before and after.
1/1/1976 after 12/31/1975 should be true: true
1/2/1976 after 1/1/1976 should be true: true
12/31/1975 after 12/31/1975 should be false: false
12/31/1975 after 1/1/1976 should be false: false
1/1/1976 after 1/2/1976 should be false: false
12/31/1975 before 1/1/1976 should be true: true
1/1/1976 before 1/2/1976 should be true: true
12/31/1975 before 12/31/1975 should be false: false
1/1/1976 before 12/31/1975 should be false: false
1/2/1976 before 1/1/1976 should be false: false

Testing difference.
12/31/1975 - 12/31/1975 should be 0: 0
1/1/1976 - 12/31/1975 should be 1: 1
1/2/1976 - 12/31/1975 should be 2: 2
1/2/1976 - 2/27/1977 should be -422: -422
8/31/2110 - 2/27/1977 should be 48762: 48762
```