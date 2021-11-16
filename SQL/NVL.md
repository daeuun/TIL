## NVL 뜻
**Null VaLue**의 약자

Null 값은 값은 존재하지만 어떤 값인지 모르는 상태를 의미한다.  
0도 아니고 아예 없는 것도 아닌 Unknown 상태!!
  

## NVL

값이 null인 경우 지정값을 출력 

**NVL("값", "지정값")**

```sql
SELECT COMM
     , NVL(COMM, 0)  --커미션이 null 이면 "0"
  FROM EMP
```


## NVL2

null이 아닌경우 지정값1을  출력하고, null인 경우 지정값2을 출력

**NVL2("값", "지정값1", "지정값2")**

```sql
SELECT MGR , COMM 
     , NVL2(MGR, 'Y', 'N') --매니저가 있으면 "Y", 없으면 "N" 
     , NVL2(COMM, 'Y', 'N') --커미션이 있으면 "Y", 없으면 "N" 
  FROM EMP
```

## DECODE 사용
NVL과 같은 결과를 만들 수는 있지만 조건이 더 필요하므로, 명시적으로 사용하는 목적에 따라 NVL을 사용하도록 한다.

```sql
DECODE(COMM, null, 0, COMM) --NVL 
DECODE(COMM, null, "N", "Y") --NVL2
```
