# NVL

값이 null인 경우 지정값을 출력 : NVL("값", "지정값")

```sql
SELECT COMM
     , NVL(COMM, 0)  --커미션이 null 이면 "0"
  FROM EMP
```