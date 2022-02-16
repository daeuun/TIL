```SQL
CASE 컬럼 
WHEN 조건1 THEN 값1 
WHEN 조건2 THEN 값2 
ELSE 값3 
END 
```

컬럼이 조건1 일때는 값1 
조건2일때는 값2를 반환 
조건에 맞지 않는 경우에는 값3 을 반환 


+ CASE 문을 사용하는 경우? 

1. 약어나 코드를 읽기 쉬운 값으로 바꿔 줄 때 
2. 데이터를 범주화 하는것 

```sql
CASE WHEN USER_ID = #{USER1} 
THEN 1 
ELSE 0 
END 
AS USER_CNT
```
