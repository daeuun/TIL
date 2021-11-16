숫자형 문자인 경우 문자길이를 똑같이 맞춰야하는 경우가 있다.  
LPAD는 왼쪽, RPAD는 오른쪽부터 총길이 만큼 지정한 문자를 채운다.

ex) 1, 10, 123 -> 00001, 00010, 00123


## LPAD

지정한 길이 만큼 왼쪽부터 특정문자로 채워준다.

**LPAD("값", "총 문자길이", "채울 문자")**

```sql
SELECT 
       EMPNO 
     , ENAME 
     , DEPTNO               -- //값:20
     , LPAD(DEPTNO, 5)      --    20
     , LPAD(DEPTNO, 5, ' ') --    20
     , LPAD(DEPTNO, 5, '0') -- 00020
     , LPAD(DEPTNO, 5, 'A') -- AAA20
  FROM EMP
```


## RPAD

지정한 길이 만큼 오른쪽부터 특정문자로 채워준다.

**RPAD("값", "총 문자길이", "채울 문자")**

```sql
SELECT 
       EMPNO 
     , ENAME 
     , DEPTNO               -- //값:20
     , RPAD(DEPTNO, 5)      -- 20
     , RPAD(DEPTNO, 5, ' ') -- 20
     , RPAD(DEPTNO, 5, '0') -- 20000
     , RPAD(DEPTNO, 5, 'A') -- 20AAA
  FROM EMP
```
