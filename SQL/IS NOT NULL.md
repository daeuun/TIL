## NULL 이외의 값만 불러오기


### IS NOT NULL
A1컬럼의 값이 null이 아닌 경우
```sql
SELECT
  FROM TBL_NAME
 WHERE A1 IS NOT NULL
```

### != ' '
A1컬럼의 값이 빈값이 아닌 경우
```sql
SELECT
  FROM TBL_NALE
 WHERE A1 != ''
```
