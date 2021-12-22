## DECODE
+ A=B이면 X, A≠B이면 Y를 출력
```sql
SELECT DECODE(컬럼이름A, 비교대상값B, 출력값X, 출력값Y)
  FROM 테이블이름;
```


```sql
    SELECT B.X_NAME
         , SUM(DECODE(B.JOB, 'STUDENT', B.SCORE, 0)) AS SCORE1 -- JOB에서 STUDENT면 점수 SCORE1에 출력, 아니면 0
         , SUM(DECODE(B.JOB, 'TEACHER', B.SCORE, 0)) AS SCORE2 -- JOB에서 TEACHER면 점수 SCORE2에 출력, 아니면 0
      FROM 
      (
        SELECT B.USER_NM AS X_NAME  --조회구분별 컬럼
             , A.JOB
             , A.SCORE
          FROM TABLE_USER A
         WHERE 1=1
           AND A.YEAR = '2021'
           AND B.USER_ID = 'USR1234'
        ) B
     GROUP BY B.X_NAME
```
